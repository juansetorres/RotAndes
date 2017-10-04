package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import vos.Producto;

public class Pedido {
	@JsonProperty(value="id")
	private Long idPed;
	@JsonProperty(value="fecha")
	private Integer fecha;
	@JsonProperty(value="cpersonas")
	private Integer cantPersonas;
	@JsonProperty(value="estado")
	private Integer estado;
	
	private ArrayList<Producto> productos = new ArrayList<>(); 
	
	//CONSTANTES//
	private static final Integer FINALIZADO = 0;
	private static final Integer ENCOLA = 1;
	
	
	public Pedido(@JsonProperty(value="id") Long pId,@JsonProperty(value="fecha")Integer pFecha,@JsonProperty(value="cpersonas") Integer pCantidadPersonas,@JsonProperty(value="estado")Integer pEstado){
		super();
		idPed =pId;
		fecha = pFecha;
		cantPersonas = pCantidadPersonas;
		estado =pEstado;
	}
	public void setId(Long pId){
		idPed=pId;
	}
	public void setFecha(Integer pFecha){
		fecha = pFecha;
	}
	public void setPersonas(Integer pCantidadPersonas){
		cantPersonas = pCantidadPersonas;
	}
	public Integer getFecha(){
		return fecha;
	}
	public Integer getCantPersonas(){
		return cantPersonas;
	}
	public Long getId(){
		return idPed;
	}
	public void setEstado(Integer pEstado){
		estado = pEstado;
	}
	public Integer getEstado(){
		return estado;
	}
	public ArrayList<Producto> getProd(){
		return productos;
	}

}
