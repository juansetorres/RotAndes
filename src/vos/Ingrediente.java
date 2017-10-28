package vos;
import org.codehaus.jackson.annotate.*;

public class Ingrediente {
	
	@JsonProperty(value="id")
	private Long idIngre;
	
	@JsonProperty(value="name")
	private String name;
	
	@JsonProperty(value="descripcion")
	private String descripcion;
	
	
	public Ingrediente(@JsonProperty(value="id")Long pId,@JsonProperty(value="name") String pName,@JsonProperty(value="descripcion")String pDescrip){
		super();
		idIngre =pId;
		name = pName;
		descripcion = pDescrip;
	}
	public String getName(){
		return name;
	}
	public String getDescrip(){
		return descripcion;
	}
	
	public void setName(String pName){
		name = pName;
	}
	public void setDescrip(String pDescrip){
		descripcion = pDescrip;
	}
	public void setId(Long pId){
		idIngre = pId;
	}
	public Long getId(){
		return idIngre;
	}

}
