package vos;
import org.codehaus.jackson.annotate.JsonProperty;
public class Sirven {
	
	@JsonProperty("id")
	private Long id;
	@JsonProperty("idProducto")
	private Long idProdu;
	@JsonProperty("idRestaurante")
	private Long idRest;
	@JsonProperty("idMenu")
	private Long idMenu;
	/**
	 * cantidad en bodega
	 */
	@JsonProperty("CANT")
	private Integer cantidad;
	
	public Sirven(@JsonProperty("idProducto") Long pId,@JsonProperty("idRestaurante") Long rId,@JsonProperty("CANT")Integer pCant,@JsonProperty("id") Long pIdSirven,@JsonProperty("idMenu") Long pIdMenu){
		super();
		id = pIdSirven;
		idProdu = pId;
		idRest = rId;
		cantidad=pCant;
		idMenu = pIdMenu;
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
	public void setId(Long pID){
		id = pID;
	}
	public Long getId(){
		return id;
	}
	public void setIdMenu(Long pIdMenu){
		idMenu = pIdMenu;
	}
	public Long getIdMenu(){
		return idMenu;
	}

}
