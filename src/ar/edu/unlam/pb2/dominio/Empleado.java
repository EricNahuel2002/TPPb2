package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Empleado {
	private Integer codigo;
	private String nombre;
	private Double sueldo;
	private LocalDate anioIngreso;
	//valor x hora Double
	//cantidad de horas Integer
	
	
	public Empleado(Integer codigo, String nombre, LocalDate anioIngreso) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.sueldo = 0.0;
		this.anioIngreso = anioIngreso;
	}
	//abstract calcular sueldo 
	//abstract calcular aguinaldo VEMOS
	//abstract cobrar sueldo // que el atributo sueldoDestinadoAEmpleados (constante) baje y el sueldo
	//del empleado aumente
	
	public abstract void cobrarSueldo(Double sueldo);
	public abstract Double calcularSueldoEnBaseALaAntiguedad(Double sueldo);

	public Integer getCodigo() {
		return codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public Double getSueldo() {
		return sueldo;
	}
	
	public void setSueldo(Double sueldo) {
		this.sueldo = sueldo;
	}


	public LocalDate getAnioIngreso() {
		return anioIngreso;
	}


	@Override
	public int hashCode() {
		return Objects.hash(anioIngreso, codigo, nombre, sueldo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(anioIngreso, other.anioIngreso) && Objects.equals(codigo, other.codigo)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(sueldo, other.sueldo);
	}


	
	
	

}
