package Actividad2V2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;




public class Articulo {
	
	private int codarti;
	private String denom;
	private int stock;
	private float pvp;
	private Set<Venta> compras;
	
	public Articulo() {
		
	}
	
    public Articulo(int codarti, String denom, int stock, float pvp) {
		this.codarti=codarti;
		this.denom=denom;
		this.stock=stock;
		this.pvp=pvp;
		
		
	}
	
	
	
	public int getCodarti() {
		return codarti;
	}
	public void setCodarti(int codarti) {
		this.codarti = codarti;
	}
	public String getDenom() {
		return denom;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public float getPvp() {
		return pvp;
	}
	public void setPvp(float pvp) {
		this.pvp = pvp;
	}
	
	public Set<Venta> getCompras() {
		return compras;
	}

	public void setCompras(Set<Venta> compras) {
		this.compras = compras;
	}

	@Override
	public String toString() {
		return codarti+" - "+denom+" - "+stock+" - "+pvp;
	}
	
	
	public int obtenerCompras(HashSet<Venta> ventas) {
		compras=new HashSet<Venta>();
		
		for (Venta v : ventas) {
			if(v.getCodarti().getCodarti()==this.getCodarti()) {
				compras.add(v);
				}
		}
		return compras.size();
	}
	
	public int obtenerComprasId(HashSet<Venta> ventas, int idVenta) {
		int nArticulos=0;
		for (Venta venta : ventas) {
			if(venta.getCodarti().getCodarti()==this.getCodarti() && venta.getCodventa()==idVenta) {
				nArticulos=venta.getUniven();
				}
		}
		
		return nArticulos;
	}
	
	
	public int obtenerNAVendidos(HashSet<Venta> ventas) {
		int nArticulos=0;
		for (Venta venta : ventas) {
				nArticulos+=venta.getUniven();
		}
		return nArticulos;
	}
	
	
	
	
	//Metodo para insertar los datos de los articulos en la BD
	public void insertarArticulo(Articulo articulo) {
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"gestor.db4o");
				try{
						System.out.println(articulo.getCodarti()+" Almacenado");
						bd.store(articulo);
					}
				finally{
					bd.close();
				}
	}
	
	//Obtener los articulos con el HasMap
	public HashSet<Articulo> obtenerArticulosHashSet(){
		HashSet<Articulo> articulos = new HashSet<Articulo>();
		Articulo articulo;
		
		ObjectContainer bd=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"gestor.db4o");
		try{
			ObjectSet res=bd.queryByExample(new Articulo(0, null, 0, 0));
			while(res.hasNext()){
				articulos.add((Articulo)res.next());
				}
			}
				finally{
					bd.close();
				}
			
		return articulos;
	}
	
	
	
	public void obtenerAllDatosA(HashSet<Venta> ventas, HashSet<Articulo> articulos ) {
		int totalSumaUnive=0;
		int totalSumaVent=0;
		float sumaImporte=0;
		System.out.println("CODARTI – DENOMINACION – STOCK – PVP – SUMA_UNIVEN – SUMA_IMPORTE – NUM_VENTAS");
		System.out.println("--------------------------------------------------------------------------------------------------");
		for (Articulo articulo : articulos) {
			totalSumaUnive+=articulo.obtenerNAVendidos(ventas);
			totalSumaVent+=articulo.obtenerCompras(ventas);
			sumaImporte+=articulo.obtenerCompras(ventas)*articulo.getPvp();
			System.out.println(articulo.toString()+" - "+
					articulo.obtenerNAVendidos(ventas)+" - "+
					articulo.obtenerNAVendidos(ventas)*articulo.getPvp()+" - "+
					articulo.obtenerCompras(ventas));
		} 			

		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.println("TOTAL SUMA_UNIVEN: "+totalSumaUnive);
		System.out.println("TOTAL SUMA_IMPORTE: "+sumaImporte);
		System.out.println("TOTAL NUM_VENTAS: "+totalSumaVent);
	}
	
	public void ArticuloMasVendido(HashSet<Venta> ventas, HashSet<Articulo> articulos) {
		System.out.println("ARTICULO MAS VENDIDO: ");
		int masComprado=0;
		for (Articulo articulo : articulos) {
			if(articulo.obtenerCompras(ventas)>masComprado)
				masComprado=articulo.obtenerCompras(ventas);
		} 
		for (Articulo articulo : articulos) {
			if(articulo.obtenerCompras(ventas)==masComprado)
				System.out.println(articulo.getCodarti()+" - "+articulo.getDenom()+"    VECES VENDIDO: "+masComprado);
		}
	}
	
	public void mediaPorArticulo(HashSet<Venta> ventas, HashSet<Articulo> articulos) {
		System.out.println("\n\nMEDIA IMPORTE POR ARTICULOS: ");
		int mediaPorVenta=0;
		
		for (Articulo articulo : articulos) {
			if(articulo.obtenerCompras(ventas)>0)
				System.out.println(articulo.getCodarti()+" - "+articulo.getDenom()+"    Vendido en: "+
			articulo.obtenerCompras(ventas)+" compras, con una media de "+
				(articulo.getPvp()*articulo.obtenerNAVendidos(ventas))/articulo.obtenerCompras(ventas));
		}
	}
}
