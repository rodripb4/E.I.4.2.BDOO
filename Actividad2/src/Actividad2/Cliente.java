package Actividad2;

import java.util.ArrayList;


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
	public float obtenerTotalImporte(ArrayList<Venta> ventas) {
		float total=0;
		for (Venta venta : ventas) {
			if(venta.getNumcli().getNumcli()==this.numcli) {
				total+=venta.getUniven()*venta.getCodarti().getPvp();
			}
		}
		return total;
	}
	
	//Metodo para obtener el numero de ventas de los clientes
	public int obtenerNVentas(ArrayList<Venta> ventas) {
		int total=0;
		for (Venta venta : ventas) {
			if(venta.getNumcli().getNumcli()==this.numcli) {
				total++;
			}
		}
		return total;
	}
	
	//Metodo para obtener los clientes con HasMap
	public ArrayList<Cliente> obtenerClientesArrayList(){
		ArrayList<Cliente> clientes= new ArrayList<Cliente>();
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
	public void obtenerDatosClientes(ArrayList<Cliente> clientesArray, ArrayList<Venta> ventasArray) {
		System.out.println("NUMCLI - NOMBRE - POBLACION - TOTAL_IMPORTE - NUM_VENTAS");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		for (Cliente c : clientesArray) {
			System.out.println(c.getNumcli()+" - "+
					c.getNombre()+" - "+
					c.getPobla()+" - "+
					c.obtenerTotalImporte(ventasArray)+" - "+
					c.obtenerNVentas(ventasArray)
					);
		}
	}
	
	//Mostrar el cliente que mas ha gastado 
	public void ClienteMasQueMasGasto(ArrayList<Cliente> clientesArray, ArrayList<Venta> ventasArray) {
		System.out.println("\n\nCLIENTE QUE MAS HA GASTADO: ");
		float maxGasto=0;
		for (Cliente c : clientesArray) {
			if(c.obtenerTotalImporte(ventasArray)>maxGasto) {
				maxGasto=c.obtenerTotalImporte(ventasArray);
			}
		}
		
		for (Cliente c : clientesArray) {
			if(c.obtenerTotalImporte(ventasArray)==maxGasto) {
				System.out.println(c.getNumcli()+"-"+c.getNombre()+"   Gasto: "+c.obtenerTotalImporte(ventasArray));
			}
		}
	}
	//Mostrar el cliente que mas a Comprado 
	public void clienteQueMasCompro(ArrayList<Cliente> clientesArray, ArrayList<Venta> ventasArray) {
		System.out.println("\n\nCLIENTE CON MAS VENTAS: ");
		int maxVentas=0;
		for (Cliente c : clientesArray) {
			if(c.obtenerNVentas(ventasArray)>maxVentas) {
				maxVentas=c.obtenerNVentas(ventasArray);
			}
		}
		
		for (Cliente c : clientesArray) {
			if(c.obtenerNVentas(ventasArray)==maxVentas) {
				System.out.println(c.getNumcli()+"-"+c.getNombre()+"   N de ventas: "+c.obtenerNVentas(ventasArray));
			}
		}
	}
	
	
	
	
}
