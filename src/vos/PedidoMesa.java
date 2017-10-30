package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoMesa {

	@JsonProperty(value="pedido")
	private Pedido Pedido;
	
	@JsonProperty(value="productos")
	private List<Long> productos;
	
	@JsonProperty(value="menus")
	private List<Long> menus;

	public PedidoMesa(@JsonProperty(value="Pedido") Pedido pedido,@JsonProperty(value="productos") List<Long> productos,@JsonProperty(value="menus") List<Long> menus) {
		super();
		Pedido = pedido;
		this.productos = productos;
		this.menus = menus;
	}

	public Pedido getPedido() {
		return Pedido;
	}

	public void setPedido(Pedido pedido) {
		Pedido = pedido;
	}

	public List<Long> getProductos() {
		return productos;
	}

	public void setProductos(List<Long> productos) {
		this.productos = productos;
	}

	public List<Long> getMenus() {
		return menus;
	}

	public void setMenus(List<Long> menus) {
		this.menus = menus;
	}
	
}
