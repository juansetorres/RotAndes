package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class ProductoIngrediente {

	@JsonProperty(value="ID_PRODUCTO")
	private Long idProducto;
	
	@JsonProperty(value="ID_ING")
	private Long idIng;

	public ProductoIngrediente( @JsonProperty(value="ID_PRODUCTO")Long idProducto,@JsonProperty(value="ID_ING")Long idIng) {
		super();
		this.idIng = idIng;
		this.idProducto = idProducto;
	}

	public Long getIdIng() {
		return idIng;
	}

	public void setIdInf(Long idI) {
		this.idIng = idI;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
}
