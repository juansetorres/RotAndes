package vos;
import org.codehaus.jackson.annotate.*;

public class Ingrediente {
	@JsonProperty(value="id")
	private Long idIngre;
	@JsonProperty(value="NAME")
	private String name;
	@JsonProperty(value="DESCRIPCION")
	private String descrip;
	
	public Ingrediente(@JsonProperty(value="id")Long pId,@JsonProperty(value="TIPOCOMIDA") String pName,@JsonProperty(value="PAGINAWEB")String pDescrip){
		super();
		idIngre =pId;
		name = pName;
		descrip = pDescrip;
	}
	public String getName(){
		return name;
	}
	public String getDescrip(){
		return descrip;
	}
	
	public void setName(String pName){
		name = pName;
	}
	public void setDescrip(String pDescrip){
		descrip = pDescrip;
	}
	public void setId(Long pId){
		idIngre = pId;
	}
	public Long getId(){
		return idIngre;
	}

}
