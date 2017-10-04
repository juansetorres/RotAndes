package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido {
	@JsonProperty(value="id")
	private Long idPed;
	@JsonProperty(value="fecha")
	private Integer fecha;
	@JsonProperty(value="cpersonas")
	private Integer cantPersonas;
	public Pedido(@JsonProperty(value="id") Long pId,@JsonProperty(value="fecha")Integer pFecha,@JsonProperty(value="cpersonas") Integer pCantidadPersonas){
		super();
		idPed =pId;
		fecha = pFecha;
		cantPersonas = pCantidadPersonas;
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

}
