package com.java.hibernate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void get_all(ClienteManager sm) {
		System.out.println("\n\tClientes");
		List<Cliente> clientes = sm.get_all();
		for (Cliente cliente : clientes) {
			System.out.print(cliente);
		}
	}

	public static void get_by_id(ClienteManager sm, Scanner scanner) {
		System.out.println("\n\tBuscar cliente por id");
		System.out.print("Ingrese un id: ");
		Integer id = scanner.nextInt();
		Cliente cliente = sm.get_by_id(id);
		if(cliente == null) {
			System.out.println("Cliente no encontrado");
			return;
		}
		System.out.println(cliente);
	}
	
	public static void delete(ClienteManager sm, Scanner scanner, BufferedReader buffer) throws IOException {
		System.out.println("\n\tBorrar cliente");
		System.out.print("Id del cliente a eliminar: ");
		Integer id = scanner.nextInt();
		System.out.print("seguro de eliminar el siguiente cliente "+ id.toString() + "? (s/n) ");
		String answerd = buffer.readLine();
		if(answerd.equalsIgnoreCase("s")) {
			boolean deleted = sm.delete(id);
			if(deleted) {
				System.out.println("Cliente eliminado");
			} else {
				System.out.println("error al intentar eliminar el Cliente");
			}
		}
	}

	public static void create(ClienteManager sm, BufferedReader buffer) throws IOException {
		System.out.println("\n\tCrear cliente");
		System.out.print("Nombre: ");
		String nombre = buffer.readLine();
		System.out.print("Apellido: ");
		String apellido = buffer.readLine();
		System.out.print("Dui: ");
		String dui = buffer.readLine();
		System.out.print("Correo: ");
		String correo = buffer.readLine();
		System.out.print("Telefono: ");
		String telefono = buffer.readLine();
		Cliente cliente = new Cliente(nombre, apellido, correo, telefono, dui);
		boolean created = sm.create(cliente);
		if(created) {
			System.out.println("Cliente guardado");
		} else {
			System.out.println("error al crear nuevo cliente");
		}
	}
	
	public static void update(ClienteManager sm, Scanner scanner, BufferedReader buffer) throws IOException {
		System.out.println("\n\tActualizar cliente");
		System.out.print("Ingrese un id: ");
		Integer id = scanner.nextInt();
		Cliente cliente = sm.get_by_id(id);
		if(cliente == null) {
			System.out.println("Cliente no encontrado");
			return;
		}
		System.out.println("para omitir algún cambio presione enter");
		System.out.print("Nuevo nombre(" + cliente.getNombre() + "): ");
		String nombre = buffer.readLine();
		if(nombre != "") {
			cliente.setNombre(nombre);
		}
		System.out.print("New apellido(" + cliente.getApellido() + "): ");
		String apellido = buffer.readLine();
		if(apellido != "") {
			cliente.setApellido(apellido);
		}
		System.out.print("New correo(" + cliente.getCorreo() + "): ");
		String correo = buffer.readLine();
		if(correo != "") {
			cliente.setCorreo(correo);
		}
		System.out.print("New telefono(" + cliente.getTel() + "): ");
		String telefono = buffer.readLine();
		if(telefono != "") {
			cliente.setTel(telefono);
		}
		boolean updated = sm.update(cliente);
		if(updated) {
			System.out.println("Cliente actualizado");
		} else {
			System.out.println("error al actualizar cliente");
		}
	}
	
	public static void main(String[] args) throws IOException {
		ClienteManager sm = new ClienteManager();
		Scanner scanner = new Scanner(System.in);
		int opt = 0;
		while(opt != 5) {
			System.out.println("\n\tOPCIONES");
			System.out.println("1. Buscar cliente por id");
			System.out.println("2. Crear un nuevo cliente");
			System.out.println("3. Actualizar información de un cliente");
			System.out.println("4. Eliminar un cliente");
			System.out.println("5. Salir");
			System.out.print("Elige una opción: ");
			opt = scanner.nextInt();
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader buffer = new BufferedReader(in);
			switch(opt) {
				case 1:
					Main.get_by_id(sm, scanner);
					break;
				case 2:
					Main.create(sm, buffer);
					break;
				case 3:
					Main.update(sm, scanner, buffer);
					break;
				case 4:
					Main.delete(sm, scanner, buffer);
					break;
			}
			System.in.read();
		}
		scanner.close();
	}
}
