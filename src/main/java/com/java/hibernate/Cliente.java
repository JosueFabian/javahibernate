package com.java.hibernate;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@SequenceGenerator(initialValue=1, name="idgen", sequenceName="clientes_cliente_id_seq")
@Table(name="Clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cliente_id;
	
	@Column(length=50)
	private String nombre;

	@Column(length=50)
	private String apellido;

	@Column(length=50)
	private String correo;

	@Column(length=8)
	private String telefono;

	@Column(length=8)
	private String dui;

	public Cliente(Integer cliente_id, String nombre, String apellido, String correo, String telefono, String dui) {
		this.cliente_id = cliente_id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.dui = dui;
	}

	public Cliente(String nombre, String apellido, String correo, String telefono, String dui) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.dui = dui;
	}

	public Cliente() {}

	public Integer getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(Integer cliente_id) {
		this.cliente_id = cliente_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTel() {
		return telefono;
	}
	public void setTel(String telefono) {
		this.telefono = telefono;
	}
	public String getDui() {
		return dui;
	}
	public void setDui(String dui) {
		this.dui = dui;
	}

	@Override
	public String toString() {
		return "Id: " + cliente_id + ", nombre: " + nombre + ", apellido: " + apellido + ", correo: " + correo
				+ ", telefono: " + telefono + ", dui" + dui;
	}
}
