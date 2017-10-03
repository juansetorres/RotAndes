package vos;
import org.codehaus.jackson.annotate.*;

public class Menu {
	@JsonProperty(value="id")
	private Long idMenu;
	@JsonProperty(value = "COSTO")
	private Integer costo;
	@JsonProperty(value = "PRECIO")
	private Integer precio;
	
	public Menu(@JsonProperty(value ="id")Long pId,@JsonProperty(value = "COSTO")Integer pCosto,@JsonProperty(value = "PRECIO")Integer pPrecio){
		super();
		idMenu = pId;
		costo = pCosto;
		precio = pPrecio;
	}
	public Integer getCosto(){
		return costo;
	}
	public Integer getPrecio(){
		return precio;
	}
	public Long getId(){
		return idMenu;
	}
	
	public void setCosto(Integer pCosto){
		costo = pCosto;
	}
	public void setPrecio(Integer pPrecio){
		precio = pPrecio;
	}
	public void setId(Long pId){
		idMenu = pId;
	}

}
