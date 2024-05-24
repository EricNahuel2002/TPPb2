package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Cajero extends Empleado{
	private static final Double PORCIENTO_A_SUBIR_ANTES = 0.40;
	private static final Double PORCIENTO_A_SUBIR_DESPUES = 0.35;
	
	public Cajero(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void cobrarSueldo(Double sueldo) {
		Double primaPorAntiguedad = this.calcularSueldoEnBaseALaAntiguedad(sueldo);
		super.setSueldo(sueldo + primaPorAntiguedad);
	}

	@Override
	public Double calcularSueldoEnBaseALaAntiguedad(Double sueldo) {
		Double sueldoT = 0.0;

		// yo lo calcule de acuerdo al sueldo base no al sueldo calculado despues 
		if (super.getAnioIngreso().isBefore(LocalDate.of(2010, 01, 01))) {
			sueldoT =	sueldo * PORCIENTO_A_SUBIR_ANTES;
		}else if (super.getAnioIngreso().isAfter(LocalDate.of(2010, 01, 01))) {
			sueldoT = sueldo * PORCIENTO_A_SUBIR_DESPUES;
		}
		
		return sueldoT;
	}
	
}
