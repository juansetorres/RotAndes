package vos;
import java.util.List;

import org.codehaus.jackson.annotate.*;

public class Menu {
	@JsonProperty(value="id")
	private Long id;
	@JsonProperty(value = "costo")
	private Integer costo;
	@JsonProperty(value = "precio")
	private Integer precio;
	@JsonProperty(value = "productos")
	private List<String> productos;
	
	public Menu(@JsonProperty(value ="id")Long pId,@JsonProperty(value = "costo")Integer pCosto,@JsonProperty(value = "precio")Integer pPrecio, @JsonProperty(value = "productos") List<String> rProductos){
		super();
		id = pId;
		costo = pCosto;
		precio = pPrecio;
		productos = rProductos;
	}
	public Long getIdMenu() {
		return id;
	}
	public void setIdMenu(Long idMenu) {
		this.id = idMenu;
	}
	public List<String> getProductos() {
		return productos;
	}
	public void setProductos(List<String> productos) {
		this.productos = productos;
	}
	public Integer getCosto(){
		return costo;
	}
	public Integer getPrecio(){
		return precio;
	}
	
	public void setCosto(Integer pCosto){
		costo = pCosto;
	}
	public void setPrecio(Integer pPrecio){
		precio = pPrecio;
	}

}
