package vos;
import org.codehaus.jackson.annotate.*;

public class Zona {
	//Atributos
	@JsonProperty(value="id")
	private Long idZone;
	
	@JsonProperty(value = "CAPACIDAD")
	private Integer capacidad;
	
	@JsonProperty(value = "NAME")
	private String name;
	
	
	public Zona(@JsonProperty(value="id") Long pId, @JsonProperty(value = "CAPACIDAD") Integer pCapacidad, @JsonProperty(value = "NAME") String pName){
		super();
		idZone = pId;
		capacidad = pCapacidad;
		name = pName;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}





}
