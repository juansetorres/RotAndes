package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class MenuProducto {

	@JsonProperty(value="ID_MENU")
	private Long idMenu;

	@JsonProperty(value="ID_PRODUCTO")
	private Long idProducto;

	public MenuProducto(@JsonProperty(value="ID_MENU")Long idMenu, @JsonProperty(value="ID_PRODUCTO")Long idProducto) {
		super();
		this.idMenu = idMenu;
		this.idProducto = idProducto;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
}
