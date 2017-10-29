package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class EquivalentesP {
	
	@JsonProperty(value="tipo")
	private String tipo;

	@JsonProperty(value="producto1")
	private String produ1;
	
	@JsonProperty(value="producto2")
	private String produ2;
	
	public EquivalentesP (@JsonProperty(value="tipo") String pTipo,@JsonProperty(value="producto1") String pProdu1,@JsonProperty(value="producto2") String pProdu2){
		super();
		this.tipo = pTipo;
		this.produ1 = pProdu1;
		this.produ2 = pProdu2;
	}
	public String getProdu1() {
		return produ1;
	}

	public void setProdu1(String pProdu1) {
		this.produ1 = pProdu1;
	}

	public String getProdu2() {
		return produ2;
	}

	public void setProdu2(String pProdu2) {
		this.produ2 = pProdu2;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String pTipo) {
		this.tipo = pTipo;
	}


}
