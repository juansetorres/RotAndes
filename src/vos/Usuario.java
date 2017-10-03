package vos;

import org.codehaus.jackson.annotate.*;

public class Usuario {
	//Atributos
		@JsonProperty(value="id")
		private Long idUsu;
		@JsonProperty(value = "CORREO")
		private String correo;
		@JsonProperty(value = "NAME")
		private String name;
		
	public Usuario(@JsonProperty(value = "id") Long pId,@JsonProperty(value = "CORREO") String pCorreo,@JsonProperty(value = "NAME") String pNombre){
			super();
			idUsu = pId;
			name =pNombre;
			correo = pCorreo;
	}
		
	public String getName(){
			return name;
	}
	public String getCorreo(){
			return correo;
	}
	public Long getId(){
			return idUsu;
	}
	public void setName(String pName){
			name= pName;
			
	}
	public void setCorreo(String pCorreo){
			correo = pCorreo;
	}
	public void setId(){
			
	}

}
