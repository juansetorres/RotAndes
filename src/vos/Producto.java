package vos;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Producto {
	
	@JsonProperty(value="id")
	private Long id;
	
	@JsonProperty(value="name")
	private String name;
	
	@JsonProperty(value="descripcion")
	private String descripcion;

	@JsonProperty(value="costo")
	private Double costo;

	@JsonProperty(value="precio")
	private Double precio;

	@JsonProperty(value="disponibilidad")
	private Integer disponibilidad;

	@JsonProperty(value="idrestaurante")
	private Long restaurante;

	@JsonProperty(value="cantidadMaxima")
	private int cantidadMaxima;
	
	private List<Ingrediente> ingredientes;

	public Producto(@JsonProperty(value="id") Long id,@JsonProperty(value="name") String pName, @JsonProperty(value="descripcion") String pDesc,@JsonProperty(value="costo") Double costo, @JsonProperty(value="precio") Double precio,@JsonProperty(value="disponibilidad") Integer disponibilidad,@JsonProperty(value="idrestaurante") Long restaurante, @JsonProperty(value="cantidadMaxima") int cantidadMaxima){
		this.id = id;
		this.name = pName;
		this.descripcion = pDesc;
		this.costo = costo;
		this.precio = precio;
		this.disponibilidad = disponibilidad;
		this.restaurante = restaurante;
		this.cantidadMaxima = cantidadMaxima;
		setIngredientes(new ArrayList<>());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idP) {
		this.id = idP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Integer disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Long getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Long restaurante) {
		this.restaurante = restaurante;
	}

	public int getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setCantidadMaxima(int cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}
}
