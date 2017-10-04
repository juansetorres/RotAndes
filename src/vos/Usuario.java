package vos;

import org.codehaus.jackson.annotate.*;

public class Usuario {
	//Atributos
		@JsonProperty(value="id")
		private Long idUsu;
		@JsonProperty(value = "correo")
		private String correo;
		@JsonProperty(value = "name")
		private String name;
		@JsonProperty(value = "rol")
		private Integer rol;
	//CONSTANTES
		public final static Integer CLIENTE = 1;
		public final static Integer ADMIN = 0;
		
		
		
		
	public Usuario(@JsonProperty(value = "id") Long pId,@JsonProperty(value = "correo") String pCorreo,@JsonProperty(value = "name") String pNombre,@JsonProperty(value = "rol") Integer pRol){
			super();
			idUsu = pId;
			name =pNombre;
			correo = pCorreo;
			rol = pRol;
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
	public void setCliente(Integer pRol){
		rol = pRol;
	}
	public Integer getRol(){
		return rol;
	}

}
