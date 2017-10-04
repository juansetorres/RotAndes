package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.*;

public class Producto {
	@JsonProperty(value="id")
	private Long idPro;
	@JsonProperty(value = "costo")
	private Integer costo;
	@JsonProperty(value = "precio")
	private Integer precio;
	@JsonProperty(value="name")
	private String name;
	@JsonProperty(value="descripcion")
	private String descrip;
	
	private ArrayList<Ingrediente> ingredientes = new ArrayList<>();

	public Producto(@JsonProperty(value="id") Long pId,@JsonProperty(value="name")String pNombre,@JsonProperty(value="descripcion")String pDescrip,@JsonProperty(value = "costo") Integer pCosto, @JsonProperty(value = "precio") Integer pPrecio){
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
	public ArrayList<Ingrediente> darIngredientes(){
		return ingredientes;
	}

}
