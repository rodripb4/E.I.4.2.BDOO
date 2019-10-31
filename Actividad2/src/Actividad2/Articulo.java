package Actividad2;

import java.util.ArrayList;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;




public class Articulo {
	
	private int codarti;
	private String denom;
	private int stock;
	private float pvp;
	private ArrayList<Venta> compras;
	
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

	public ArrayList<Venta> getcompras() {
		return compras;
	}

	public void setCompras(ArrayList<Venta> compras) {
		compras = compras;
	}
	
	@Override
	public String toString() {
		return codarti+" - "+denom+" - "+stock+" - "+pvp;
	}
	
	
	public int obtenerCompras(ArrayList<Venta> ventas) {
		compras=new ArrayList<Venta>();
		
		for (Venta v : ventas) {
			if(v.getCodarti().getCodarti()==this.getCodarti()) {
				compras.add(v);
				}
		}
		return compras.size();
	}
	
	public int obtenerComprasId(ArrayList<Venta> ventas, int idVenta) {
		int nArticulos=0;
		for (Venta venta : ventas) {
			if(venta.getCodarti().getCodarti()==this.getCodarti() && venta.getCodventa()==idVenta) {
				nArticulos=venta.getUniven();
				}
		}
		
		return nArticulos;
	}
	
	
	public int obtenerNAVendidos(ArrayList<Venta> ventas) {
		int nArticulos=0;
		for (Venta venta : ventas) {
			//if(venta.getCodarti().codarti==this.codarti) {
				nArticulos+=venta.getUniven();
				//}
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
	public ArrayList<Articulo> obtenerArticulosArray(){
		ArrayList<Articulo> articulos=new ArrayList<Articulo>();
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
	
	
	
	public void obtenerAllDatosA(ArrayList<Venta> ventasArray, ArrayList<Articulo> articulosArray ) {
		int totalSumaUnive=0;
		int totalSumaVent=0;
		float sumaImporte=0;
		System.out.println("CODARTI – DENOMINACION – STOCK – PVP – SUMA_UNIVEN – SUMA_IMPORTE – NUM_VENTAS");
		System.out.println("--------------------------------------------------------------------------------------------------");
		for (Articulo articulo : articulosArray) {
			totalSumaUnive+=articulo.obtenerNAVendidos(ventasArray);
			totalSumaVent+=articulo.obtenerCompras(ventasArray);
			sumaImporte+=articulo.obtenerCompras(ventasArray)*articulo.getPvp();
			System.out.println(articulo.toString()+" - "+
					articulo.obtenerNAVendidos(ventasArray)+" - "+
					articulo.obtenerNAVendidos(ventasArray)*articulo.getPvp()+" - "+
					articulo.obtenerCompras(ventasArray));
		} 			

		System.out.println("--------------------------------------------------------------------------------------------------");
		System.out.println("TOTAL SUMA_UNIVEN: "+totalSumaUnive);
		System.out.println("TOTAL SUMA_IMPORTE: "+sumaImporte);
		System.out.println("TOTAL NUM_VENTAS: "+totalSumaVent);
	}
	
	public void ArticuloMasVendido(ArrayList<Venta> ventasArray, ArrayList<Articulo> articulosArray) {
		System.out.println("ARTICULO MAS VENDIDO: ");
		int masComprado=0;
		for (Articulo articulo : articulosArray) {
			if(articulo.obtenerCompras(ventasArray)>masComprado)
				masComprado=articulo.obtenerCompras(ventasArray);
		} 
		for (Articulo articulo : articulosArray) {
			if(articulo.obtenerCompras(ventasArray)==masComprado)
				System.out.println(articulo.getCodarti()+" - "+articulo.getDenom()+"    VECES VENDIDO: "+masComprado);
		}
	}
	
	public void mediaPorArticulo(ArrayList<Venta> ventasArray, ArrayList<Articulo> articulosArray) {
		System.out.println("\n\nMEDIA IMPORTE POR ARTICULOS: ");
		int mediaPorVenta=0;
		
		for (Articulo articulo : articulosArray) {
			if(articulo.obtenerCompras(ventasArray)>0)
				System.out.println(articulo.getCodarti()+" - "+articulo.getDenom()+"    Vendido en: "+
			articulo.obtenerCompras(ventasArray)+" compras, con una media de "+
				(articulo.getPvp()*articulo.obtenerNAVendidos(ventasArray))/articulo.obtenerCompras(ventasArray));
		}
	}
}
