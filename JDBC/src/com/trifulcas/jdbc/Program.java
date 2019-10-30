package com.trifulcas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.math.BigDecimal;

public class Program {
	public static void main(String[] args) {
		String cadConexion = "jdbc:mysql://localhost:3306/";
		String bd = "empresa";
		String usuario = "root";
		String pass = "";
		MenuRegistros menuRegistros = new MenuRegistros();
		try {
			Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
			Connection con = DriverManager.getConnection(cadConexion + bd + "?useSSL=false", usuario, pass);
			Statement stmt;
			PreparedStatement pstmt;
			ResultSet rs;
			Scanner sc = new Scanner(System.in);
			int res1, res2, res3, id;
			String nombre;
			BigDecimal precio;
			do {
				System.out.println("Escoja la tabla que se desea manipular:");
				System.out.println("1. PRODUCTOS");
				System.out.println("2. PROVEEDORES");
				System.out.println("0. Salir");
				res1 = Integer.parseInt(sc.nextLine());
				if (res1 == 1) {	// PRODUCTO
					do {
					System.out.println("Escoja opción de la tabla PRODUCTOS:");
					menuRegistros.mostrarMenuRegistros();
					res2 = Integer.parseInt(sc.nextLine());
						switch (res2) {
						case 1: // Ver registros
							stmt = con.createStatement();
							rs = stmt.executeQuery("select * from producto");																					
							while (rs.next())
								System.out.println(rs.getInt("IDProducto") + " | " + rs.getString("ProdNombre") + " | " +  rs.getBigDecimal("ProdPrecioUd"));
							break;
						
						case 2: // Añadir registro
							System.out.print("Introduzca el nombre del nuevo producto: ");
							nombre = sc.nextLine();
							System.out.print("Introduzca el precio nuevo producto: ");
							precio = new BigDecimal(Double.parseDouble(sc.nextLine()));
							pstmt = con.prepareStatement("insert into producto (ProdNombre, ProdPrecioUd) values(?,?)");
							pstmt.setString(1, nombre);
							pstmt.setBigDecimal(2, precio);
							pstmt.executeUpdate();
							System.out.println("Producto añadido");
							break;
						
						case 3: // Modificar registro
							System.out.print("Introduzca ID del producto que se quiere modificar: ");
							id = Integer.parseInt(sc.nextLine());
							System.out.print("Introduzca el nuevo nombre del producto: ");
							nombre = sc.nextLine();
							System.out.print("Introduzca el nuevo precio del producto: ");
							precio = new BigDecimal(Double.parseDouble(sc.nextLine()));
							pstmt = con.prepareStatement("update producto set ProdNombre=?, ProdPrecioUd=? where IDProducto=?");
							pstmt.setString(1, nombre);
							pstmt.setBigDecimal(2, precio);
							pstmt.setInt(3, id);
							pstmt.execute();
							System.out.println("Producto modificado");
							break;
						
						case 4: // Eliminar registro
							System.out.print("Introduzca ID del producto que se quiere eliminar: ");
							id = Integer.parseInt(sc.nextLine());
							pstmt = con.prepareStatement("delete from producto where IDProducto=?");
							pstmt.setInt(1, id);
							pstmt.execute();
							System.out.println("Producto eliminado.");
							break;
						
						case 0: // Salir
							break;
						}
					} while (res2 != 0);
				}
				
				if (res1 == 2) {	// PROVEEDORES
					String NIF;
					String direccion;
					do {
					System.out.println("Escoja opción de la tabla PROVEEDORES:");
					menuRegistros.mostrarMenuRegistros();
					res3 = Integer.parseInt(sc.nextLine());
					
						switch (res3) {
						case 1: // Ver registros
							stmt = con.createStatement();
							rs = stmt.executeQuery("select * from proveedor");
							while (rs.next())
								System.out.println(rs.getInt("IDProveedor") + " | " + rs.getString("ProvNIF") + " | " + rs.getString("ProvNombre") + " | " + rs.getString("ProvDireccion"));
							break;
						case 2: // Añadir registro
							System.out.print("Introduzca el nombre del nuevo proveedor: ");
							nombre = sc.nextLine();
							System.out.print("Introduzca el NIF nuevo proveedor: ");
							NIF = sc.nextLine();
							System.out.print("Introduzca la dirección del nuevo proveedor: ");
							direccion = sc.nextLine();
							pstmt = con.prepareStatement("insert into proveedor (ProvNIF, ProvNombre, ProvDireccion) values(?,?,?)");
							pstmt.setString(1, NIF);
							pstmt.setString(2, nombre);
							pstmt.setString(3, direccion);
							pstmt.executeUpdate();
							System.out.println("Proveedor añadido");
							break;
							
						case 3: // Modificar registro
							System.out.print("Introduzca ID del proveedor que se quiere modificar: ");
							id = Integer.parseInt(sc.nextLine());
							System.out.print("Introduzca el nuevo NIF del proveedor: ");
							NIF = sc.nextLine();
							System.out.print("Introduzca el nuevo nombre del proveedor: ");
							nombre = sc.nextLine();
							System.out.print("Introduzca la nueva dirección del proveedor: ");
							direccion = sc.nextLine();
							pstmt = con.prepareStatement("update proveedor set ProvNIF=?, ProvNombre=?, ProvDireccion=? where IDProveedor=?");
							pstmt.setString(1, NIF);
							pstmt.setString(2, nombre);
							pstmt.setString(3, direccion);
							pstmt.setInt(4, id);
							pstmt.execute();
							System.out.println("Proveedor modificado");
							break;
						case 4: // Eliminar registro
							System.out.print("Introduzca ID del proveedor que se quiere eliminar: ");
							id = Integer.parseInt(sc.nextLine());
							pstmt = con.prepareStatement("delete from proveedor where IDProveedor=?");
							pstmt.setInt(1, id);
							pstmt.execute();
							System.out.println("Proveedor eliminado.");
							break;
						
						case 0:
							break;
						}
					} while (res3 != 0);
				}
				if (res1 == 0)
					break;	
		} while (res1 != 0);
		con.close();
		sc.close();
		} catch (Exception e) {
			System.out.println(e);
			}
	}
}
