package vos;
import org.codehaus.jackson.annotate.*;

public class Zona {
	//Atributos
		@JsonProperty(value="id")
		private Long idZone;
		@JsonProperty(value = "CAPACIDAD")
		private Integer capacidad;
	public Zona(@JsonProperty(value="id") Long pId, @JsonProperty(value = "CAPACIDAD") Integer pCapacidad){
		super();
		idZone = pId;
		capacidad = pCapacidad;
	}
	public Long getId(){
		return idZone;
		
	}
	public Integer getCap(){
		return capacidad;
		
	}
	public void setId(Long pId){
		idZone=pId;
	}
	public void setCap(Integer pCap){
		capacidad = pCap;
	}
	
			
			
			

}
