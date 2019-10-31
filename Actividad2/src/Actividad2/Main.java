package Actividad2;

import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		Articulo a = new Articulo();
		Cliente c = new Cliente();
		Venta v = new Venta();
		ArrayList<Articulo> articulosArray=new ArrayList<Articulo>();
		ArrayList<Cliente> clientesArray=new ArrayList<Cliente>();
		ArrayList<Venta> ventasArray=new ArrayList<Venta>();
		
		//Iniciamos la Base de datos 
		//almacenarObjetos();
	
		//EJERCICIO 1
				System.out.println("EJERCICIO 1");
				System.out.println("****************************************");
				
				ventasArray=v.obtenerVentasArray();
			
				articulosArray=a.obtenerArticulosArray();
				
				a.obtenerAllDatosA(ventasArray, articulosArray);
				
			
				
				
				//EJERCICIO 2
				System.out.println("\n\n\n****************************************");
				System.out.println("EJERCICIO 2");
				System.out.println("****************************************");
						
				ventasArray=v.obtenerVentasArray();
				
				articulosArray=a.obtenerArticulosArray();
						
					
				v.obtenerDatosVentas(ventasArray);
			
							
				//EJERCICIO 3
				System.out.println("\n\n\n****************************************");
				System.out.println("EJERCICIO 3");
				System.out.println("****************************************");
							
                ventasArray=v.obtenerVentasArray();
				
				articulosArray=a.obtenerArticulosArray();
				
				a.obtenerAllDatosA(ventasArray, articulosArray);
			
				
						
				//EJERCICIO 4
				System.out.println("\n\n\n****************************************");
				System.out.println("EJERCICIO 4");
				System.out.println("****************************************");
							
				ventasArray=v.obtenerVentasArray();
				articulosArray=a.obtenerArticulosArray();
				clientesArray=c.obtenerClientesArrayList();
				
				c.obtenerDatosClientes(clientesArray, ventasArray);
							
						
				//EJERCICIO 5
				System.out.println("\n\n\n****************************************");
				System.out.println("EJERCICIO 5");
				System.out.println("****************************************");
						
				ventasArray=v.obtenerVentasArray();
				articulosArray=a.obtenerArticulosArray();
				clientesArray=c.obtenerClientesArrayList();
					
				a.ArticuloMasVendido(ventasArray, articulosArray);
				a.mediaPorArticulo(ventasArray, articulosArray);
				c.ClienteMasQueMasGasto(clientesArray, ventasArray);
				c.clienteQueMasCompro(clientesArray, ventasArray);
		
		
	}
	
	
	
	
	

	public static void almacenarObjetos(){
		Articulo articulo=new Articulo();
		Cliente cliente=new Cliente();
		Venta venta=new Venta();
		
		ArrayList<Articulo> articulos=new ArrayList<Articulo>();
		Articulo ar1 = new Articulo(1,"Mesas", 30, (float)100.5);
		Articulo ar2 = new Articulo(2,"Pupitres", 10, (float)150.7);
		Articulo ar3 = new Articulo(6,"Cuadernos", 100, (float)4.5);
		Articulo ar4 = new Articulo(8,"Tabletas", 10, (float)175.4);
		Articulo ar5 = new Articulo(9,"Boligrafos", 100, (float)3.5);
		Articulo ar6 = new Articulo(10,"Lapiceros", 300, (float)2.5);
		Articulo ar7 = new Articulo(14,"Sillas", 30, (float)120.5);
		Articulo ar8 = new Articulo(16,"Portatil", 25, (float)400.5);
		Articulo ar11 = new Articulo(17,"Espejo bano", 20, (float)100.5);
		Articulo ar21 = new Articulo(18,"Reloj cocina", 10, (float)20.7);
		Articulo ar31 = new Articulo(20,"Tarjetero", 50, (float)14.5);
		Articulo ar41 = new Articulo(22,"Estuches", 110, (float)20.4);
		Articulo ar51 = new Articulo(23,"Libro BD", 10, (float)23.5);
		Articulo ar61 = new Articulo(24,"Tijeras", 30, (float)5.0);
		Articulo ar71 = new Articulo(25,"Cubiertos", 130, (float)10.5);
		Articulo ar81 = new Articulo(26,"Teclado", 25, (float)40.5);
		articulos.add(ar1);
		articulos.add(ar2);
		articulos.add(ar3);
		articulos.add(ar4);
		articulos.add(ar5);
		articulos.add(ar6);
		articulos.add(ar7);
		articulos.add(ar8);
		articulos.add(ar11);
		articulos.add(ar21);
		articulos.add(ar31);
		articulos.add(ar41);
		articulos.add(ar51);
		articulos.add(ar61);
		articulos.add(ar71);
		articulos.add(ar81);
		
		ArrayList<Cliente> clientes=new ArrayList<Cliente>();
		Cliente cli1 = new Cliente(1,"Antonio Ruiz","Talavera");
		Cliente cli2 = new Cliente(2,"La Alameda S.L.","Talavera");
		Cliente cli3 = new Cliente(7,"Los molinos CB","Madrid");
		Cliente cli4 = new Cliente(8,"Pedro Moran S.L.","Talavera");
		Cliente cli5 = new Cliente(12,"Azulejos Martin S.L.","Talavera");
		Cliente cli6 = new Cliente(15,"Bar Girasol","Oropesa");
		Cliente cli7 = new Cliente(9,"Escuela Mayores","Talavera");
		Cliente cli8 = new Cliente(17,"Galeria Madrid S.L.","Madrid");
		Cliente cli9 = new Cliente(19,"El corte Chino","Talavera");
		Cliente cli10 = new Cliente(20,"UNICAS S.A.","Oropesa");
		Cliente cli11 = new Cliente(21,"Deportivo SAS","Talavera");
		Cliente cli12 = new Cliente(22,"Academia Padel","Madrid");
		clientes.add(cli1);
		clientes.add(cli2);
		clientes.add(cli3);
		clientes.add(cli4);
		clientes.add(cli5);
		clientes.add(cli6);
		clientes.add(cli7);
		clientes.add(cli8);
		clientes.add(cli9);
		clientes.add(cli10);
		clientes.add(cli11);
		clientes.add(cli12);
		
		ArrayList<Venta> ventas=new ArrayList<Venta>();
		Venta v1 = new Venta (1,ar1,cli1,5,"05/06/2014");
		Venta v2 = new Venta (2,ar1,cli2,4,"15/06/2014");
		Venta v3 = new Venta (3,ar1,cli6,3,"25/06/2014");
		Venta v4 = new Venta (4,ar2,cli6,5,"03/07/2014");
		Venta v5 = new Venta (5,ar2,cli7,4,"11/08/2014");
		Venta v6 = new Venta (6,ar3,cli1,3,"25/04/2014");
		ventas.add(v1);
		ventas.add(v2);
		ventas.add(v3);
		ventas.add(v4);
		ventas.add(v5);
		ventas.add(v6);
		
		for (Articulo arti : articulos) {
			articulo.insertarArticulo(arti);
		}
		
		for (Cliente cli : clientes) {
			cliente.insertarCliente(cli);
		}
		
		for (Venta vent : ventas) {
			venta.insertarVenta(vent);
		}
	}

}
