package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Cliente {

	private Integer numero;
	private String nombre;
	
	public Cliente(Integer numeroCliente, String nombre) {
		this.numero = numeroCliente;
		this.nombre = nombre;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(numero, other.numero);
	}
	
	
}
