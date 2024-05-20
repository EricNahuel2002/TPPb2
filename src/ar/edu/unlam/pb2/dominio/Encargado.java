package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Encargado extends Empleado{

	public Encargado(Integer codigo, String nombre, Double sueldo, LocalDate anioIngreso) {
		super(codigo, nombre, sueldo, anioIngreso);
		// TODO Auto-generated constructor stub
	}
	// coleccion de empleados a su cargo
	// bono extra en base a la cantidad de empleados a su cargo
}
