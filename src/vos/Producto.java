package vos;

import org.codehaus.jackson.annotate.*;

public class Producto {
	@JsonProperty(value="id")
	private Long idPro;
	@JsonProperty(value = "COSTO")
	private Integer costo;
	@JsonProperty(value = "PRECIO")
	private Integer precio;
	@JsonProperty(value="NAME")
	private String name;
	@JsonProperty(value="DESCRIPCION")
	private String descrip;
	public Producto(@JsonProperty(value="id") Long pId,@JsonProperty(value="NAME")String pNombre,@JsonProperty(value="DESCRIPCION")String pDescrip,@JsonProperty(value = "COSTO") Integer pCosto, @JsonProperty(value = "PRECIO") Integer pPrecio){
		super();
		idPro=pId;
		name = pNombre;
		descrip=pDescrip;
		costo=pCosto;
		precio = pPrecio;
	}
	public Integer getCosto(){
		return costo;
	}
	public Integer getPrecio(){
		return precio;
	}
	public Long getId(){
		return idPro;
	}
	
	public void setCosto(Integer pCosto){
		costo = pCosto;
	}
	public void setPrecio(Integer pPrecio){
		precio = pPrecio;
	}
	public void setId(Long pId){
		idPro = pId;
	}
	public String getName(){
		return name;
	}
	public String getDescrip(){
		return descrip;
	}
	
	public void setName(String pName){
		name = pName;
	}
	public void setDescrip(String pDescrip){
		descrip = pDescrip;
	}

}
