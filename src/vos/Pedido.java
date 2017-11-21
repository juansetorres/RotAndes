package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido {
	
	@JsonProperty(value="numPedido")
	private Long numPedido;
	
	@JsonProperty(value="precio")
	private double precio;
	
	@JsonProperty(value="fecha")
	private String fecha;
	
	@JsonProperty(value="idusuario")
	private Long idusuario;
	
	@JsonProperty(value="pagado")
	private int pagado;
	
	@JsonProperty(value="entregado")
	private int entregado;
	
	@JsonProperty(value="hora")
	private String hora;

	@JsonProperty(value="cambios")
	private String cambios;

	public Pedido(@JsonProperty(value="numPedido")Long numPedido, @JsonProperty(value="precio")double precio, @JsonProperty(value="fecha")String fecha, 
			@JsonProperty(value="idusuario")Long correo, @JsonProperty(value="pagado")int pagado, @JsonProperty(value="entregado")int entregado
			, @JsonProperty(value="hora")String hora, @JsonProperty(value="cambios") String cambios) {
		super();
		this.numPedido = numPedido;
		this.precio = precio;
		this.fecha = fecha;
		this.idusuario = correo;
		this.pagado = pagado;
		this.entregado = entregado;
		this.hora = hora;
		if (cambios != null && cambios != "") this.cambios = cambios;
		else cambios = "Sin cambios";
	}

	public String getCambios() {
		return cambios;
	}

	public void setCambios(String cambios) {
		this.cambios = cambios;
	}

	public Long getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(Long numPedido) {
		this.numPedido = numPedido;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getUsu() {
		return idusuario;
	}

	public void setUsu(Long correo) {
		this.idusuario = correo;
	}

	public int getPagado() {
		return pagado;
	}

	public void setPagado(int pagado) {
		this.pagado = pagado;
	}

	public int getEntregado() {
		return entregado;
	}

	public void setEntregado(int entregado) {
		this.entregado = entregado;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
}
