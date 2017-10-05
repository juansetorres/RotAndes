package vos;
import org.codehaus.jackson.annotate.JsonProperty;
public class Sirven {
	
	@JsonProperty("idProducto")
	private Long idProdu;
	@JsonProperty("idRestaurante")
	private Long idRest;
	/**
	 * cantidad en bodega
	 */
	@JsonProperty("CANT")
	private Integer cantidad;
	
	public Sirven(@JsonProperty("idProducto") Long pId,@JsonProperty("idRestaurante") Long rId,@JsonProperty("CANT")Integer pCant){
		super();
		idProdu = pId;
		idRest = rId;
		cantidad=pCant;
	}
	public Long getIdRestaurante(){
		return idRest;
	}
	public Long getIdProducto(){
		return idProdu;
	}
	public Integer darCantidad(){
		return cantidad;
	}
	public void setIdRest(Long TidRest){
		idRest = TidRest;
	}
	public void setIdPro(Long TidProd){
		idProdu = TidProd;
	}
	public void setCant(Integer pCant){
		cantidad = pCant;
	}

}
