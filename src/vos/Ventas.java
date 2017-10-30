package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Ventas {
	@JsonProperty("precio")
	public double precio;
	
	@JsonProperty("idRestaurante")
	public Long idRest;
	
	@JsonProperty("numVentas")
	public int ventas;
	
	public Ventas(@JsonProperty("idRestaurante") Long pIdRest,@JsonProperty("precio")double pPrecio,@JsonProperty("numVentas") int pVentas){
		super();
		this.idRest = pIdRest;
		this.precio = pPrecio;
		this.ventas = pVentas;
	}
	public Long getIdRest(){
		return idRest;
		
	}
	public void setIdRest(Long idRestt){
		this.idRest = idRestt;
	}
	public int getVentas(){
		return ventas;
	}
	public void setVentas(int pVentas){
		ventas= pVentas;
	}
	public double getPrecio(){
		return precio;
	}
	public void setPrecio(double pPrecio){
		precio= pPrecio;
	}

}
