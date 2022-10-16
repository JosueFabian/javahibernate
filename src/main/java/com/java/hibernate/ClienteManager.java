package com.java.hibernate;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class ClienteManager {

	private Session session;	
	
	public ClienteManager() {
		AnnotationConfiguration config = new AnnotationConfiguration();
		SessionFactory sessionFactory = config.configure().buildSessionFactory();
		this.session = sessionFactory.openSession();
	}
	
	public void close_session() {
		this.session.close();
	}
	
	public List<Cliente> get_all() {
		final List<Cliente> clientes = new LinkedList<>();
		Query q = this.session.createQuery("select _s from Cliente _s");
		for (final Object cliente: q.list()) {
			clientes.add((Cliente) cliente);
		}
		return clientes;
	}
	
	public boolean create(Cliente cliente) {
		try {
			this.session.beginTransaction();
			this.session.save(cliente);
			this.session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
	}
	
	public boolean update(Cliente cliente) {
		try {
			this.session.beginTransaction();
			this.session.update(cliente);
			this.session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Cliente get_by_id(Integer id) {
		try {
			return (Cliente)this.session.get(Cliente.class, id);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean delete(Integer id) {
		try {
			Cliente cliente = this.get_by_id(id);
			if (cliente == null) {
				return false;
			}
			this.session.beginTransaction();
			this.session.delete(cliente);
			this.session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
