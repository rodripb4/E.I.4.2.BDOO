package Actividad2V2;

import java.util.ArrayList;
import java.util.HashSet;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


public class Cliente {
	
	private int numcli;
	private String nombre;
	private String pobla;
	
	public Cliente() {
		
	}

	
	public Cliente(int numcli, String nombre, String pobla) {
		this.numcli=numcli;
		this.nombre=nombre;
		this.pobla=pobla;
	}


	public int getNumcli() {
		return numcli;
	}


	public void setNumcli(int numcli) {
		this.numcli = numcli;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPobla() {
		return pobla;
	}


	public void setPobla(String pobla) {
		this.pobla = pobla;
	}
	
	@Override
	public String toString() {
		return numcli+" - "+nombre+" - "+pobla;
	}
	
	
	
	//Metodo para insertar los clientes en la bd
	public void insertarCliente(Cliente cliente) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"gestor.db4o");
				try{
						System.out.println(cliente.getNombre()+" Almacenado");
						bd.store(cliente);
					}
				finally{
					bd.close();
				}
	}
	
	//Metodo para obtener el importe total de las ventas segun el cliente 
	public float obtenerTotalImporte(HashSet<Venta> ventas) {
		float total=0;
		for (Venta venta : ventas) {
			if(venta.getNumcli().getNumcli()==this.numcli) {
				total+=venta.getUniven()*venta.getCodarti().getPvp();
			}
		}
		return total;
	}
	
	//Metodo para obtener el numero de ventas de los clientes
	public int obtenerNVentas(HashSet<Venta>ventas) {
		int total=0;
		for (Venta venta : ventas) {
			if(venta.getNumcli().getNumcli()==this.numcli) {
				total++;
			}
		}
		return total;
	}
	
	//Metodo para obtener los clientes con HasMap
	public HashSet<Cliente> obtenerClientesHashSet(){
		HashSet<Cliente> clientes= new HashSet<Cliente>();
		Cliente cliente;
		
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"gestor.db4o");
		try{
			ObjectSet res=bd.queryByExample(new Cliente(0, null, null));
			while(res.hasNext()){
				clientes.add((Cliente)res.next());
				
				}
			}
				finally{
					bd.close();
				}
			
		return clientes;
	}
	
	//Mostrar los datos que piden de los clientes 
	public void obtenerDatosClientes(HashSet<Cliente> clientes, HashSet<Venta> ventas) {
		System.out.println("NUMCLI - NOMBRE - POBLACION - TOTAL_IMPORTE - NUM_VENTAS");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		for (Cliente c : clientes) {
			System.out.println(c.getNumcli()+" - "+
					c.getNombre()+" - "+
					c.getPobla()+" - "+
					c.obtenerTotalImporte(ventas)+" - "+
					c.obtenerNVentas(ventas)
					);
		}
	}
	
	//Mostrar el cliente que mas ha gastado 
	public void ClienteMasQueMasGasto(HashSet<Cliente> clientes, HashSet<Venta> ventas) {
		System.out.println("\n\nCLIENTE QUE MAS HA GASTADO: ");
		float maxGasto=0;
		for (Cliente c : clientes) {
			if(c.obtenerTotalImporte(ventas)>maxGasto) {
				maxGasto=c.obtenerTotalImporte(ventas);
			}
		}
		
		for (Cliente c : clientes) {
			if(c.obtenerTotalImporte(ventas)==maxGasto) {
				System.out.println(c.getNumcli()+"-"+c.getNombre()+"   Gasto: "+c.obtenerTotalImporte(ventas));
			}
		}
	}
	//Mostrar el cliente que mas a Comprado 
	public void clienteQueMasCompro(HashSet<Cliente> clientes, HashSet<Venta> ventas) {
		System.out.println("\n\nCLIENTE CON MAS VENTAS: ");
		int maxVentas=0;
		for (Cliente c : clientes) {
			if(c.obtenerNVentas(ventas)>maxVentas) {
				maxVentas=c.obtenerNVentas(ventas);
			}
		}
		
		for (Cliente c : clientes) {
			if(c.obtenerNVentas(ventas)==maxVentas) {
				System.out.println(c.getNumcli()+"-"+c.getNombre()+"   N de ventas: "+c.obtenerNVentas(ventas));
			}
		}
	}
	
	
	
	
}
