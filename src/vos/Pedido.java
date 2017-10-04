package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Pedido {
	@JsonProperty(value="id")
	private Long idPed;
	@JsonProperty(value="FECHA")
	private Integer fecha;
	@JsonProperty(value="CPERSONAS")
	private Integer cantPersonas;
	public Pedido(@JsonProperty(value="id") Long pId,@JsonProperty(value="FECHA")Integer pFecha,@JsonProperty(value="CPERSONAS") Integer pCantidadPersonas){
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
