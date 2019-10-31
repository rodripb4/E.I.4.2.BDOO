package Actividad2;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


public class Venta {
	
	private int codventa;
	private Articulo codarti;
	private Cliente numcli ;
	private int univen;
	private String fecha;

	
	public Venta() {
		
	}
	
	public Venta(int codventa, Articulo codarti, Cliente numcli, int univen, String fecha) {
		this.codventa=codventa;
		this.codarti=codarti;
		this.numcli=numcli;
		this.univen=univen;
		this.fecha=fecha;
	}

	public int getCodventa() {
		return codventa;
	}

	public void setCodventa(int codventa) {
		this.codventa = codventa;
	}

	public Articulo getCodarti() {
		return codarti;
	}

	public void setCodarti(Articulo codarti) {
		this.codarti = codarti;
	}

	public Cliente getNumcli() {
		return numcli;
	}

	public void setNumcli(Cliente numcli) {
		this.numcli = numcli;
	}

	public int getUniven() {
		return univen;
	}

	public void setUniven(int univen) {
		this.univen = univen;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return codventa+" - "+codarti.getCodarti()+" - "+numcli.getNumcli()+" - "+univen+" - "+fecha;
	}
	//Metodo para insertar las ventas en la bd
	public void insertarVenta(Venta ventas) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"gestor.db4o");
				try{
						System.out.println(ventas.getCodventa()+" Almacenado");
						bd.store(ventas);
					}
				finally{
					bd.close();
				}
	}
	
	
	public ArrayList<Venta> obtenerVentasArray(){
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		Venta venta;
		
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"gestor.db4o");
		try{
			ObjectSet res=bd.queryByExample(new Venta(0, null, null, 0, null));
			while(res.hasNext()){
				ventas.add((Venta)res.next());
			
				}
			}
				finally{
					bd.close();
				}
			
		return ventas;
	}
	
	
	public void obtenerDatosVentas(ArrayList<Venta> ventasArray) {
		System.out.println("CODVENTA – CODARTI – DENOMINACION – NUMCLI – NOMBRE – FECHA – UNIVEN – IMPORTE");
		
		for (Venta v : ventasArray) {
			System.out.println(v.getCodventa()+" - "+
		v.getCodarti()+" - "+
		v.getCodarti().getDenom()+" - "+
		v.getNumcli().getNumcli()+" - "+
		v.getNumcli().getNombre()+" - "+
		v.getFecha()+" - "+
		v.getCodarti().obtenerComprasId(ventasArray, v.getCodventa())+" - "+
		v.getCodarti().obtenerComprasId(ventasArray, v.getCodventa())*v.getCodarti().getPvp());
			};
	}
	
}
