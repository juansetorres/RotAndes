package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoMenu {

	@JsonProperty(value="numPedido")
	private Long numPedido;
	
	@JsonProperty(value="idMenu")
	private Long idMenu;

	public PedidoMenu(@JsonProperty(value="numPedido")Long numPedido, @JsonProperty(value="idMenu")Long idMenu) {
		super();
		this.numPedido = numPedido;
		this.idMenu = idMenu;
	}

	public Long getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(Long numPedido) {
		this.numPedido = numPedido;
	}

	public Long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
	}
}
