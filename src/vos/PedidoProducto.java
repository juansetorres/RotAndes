package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class PedidoProducto {

	@JsonProperty(value="numPedido")
	private Long numPedido;
	
	@JsonProperty(value="idProducto")
	private Long idProducto;

	public PedidoProducto(@JsonProperty(value="numPedido")Long numPedido,@JsonProperty(value="idProducto") Long idPlato) {
		super();
		this.numPedido = numPedido;
		this.idProducto = idPlato;
	}

	public Long getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(Long numPedido) {
		this.numPedido = numPedido;
	}

	public Long getIdPlato() {
		return idProducto;
	}

	public void setIdPlato(Long idPlato) {
		this.idProducto = idPlato;
	}
}
