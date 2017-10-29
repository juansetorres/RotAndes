package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Equivalentes {

	@JsonProperty(value="ingrediente")
	private String ingrediente;

	@JsonProperty(value="equivalente")
	private String equivalente;

	public Equivalentes(@JsonProperty(value="ingrediente")String ingrediente, @JsonProperty(value="equivalente")String equivalente) {
		super();
		this.ingrediente = ingrediente;
		this.equivalente = equivalente;
	}

	public String getNomIngrediente() {
		return ingrediente;
	}

	public void setNomIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getEquivalente() {
		return equivalente;
	}

	public void setEquivalente(String equivalente) {
		this.equivalente = equivalente;
	}

}
