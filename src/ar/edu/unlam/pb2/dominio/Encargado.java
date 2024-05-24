package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Encargado extends Empleado{

	private List<Empleado> empleadosACargo;
	private static final Double PORCIENTO_A_SUBIR_ANTES = 0.40;
	private static final Double PORCIENTO_A_SUBIR_DESPUES = 0.35;
	private final Double PRIMA_POR_CANTIDAD_DE_EMPLEADOS_A_CARGO = 100.0;

	public Encargado(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		this.empleadosACargo = new ArrayList<>();
	}
	// coleccion de empleados a su cargo
	// bono extra en base a la cantidad de empleados a su cargo

	@Override
	public void cobrarSueldo(Double sueldo) {
		Double primaPorAntiguedad = this.calcularSueldoEnBaseALaAntiguedad(sueldo);
		Double primaPorCantidadDeEmpleadosACargo = this.calcularSueldoEnBaseALaCantidadDeEmpleadosACargo();
		super.setSueldo(sueldo + primaPorAntiguedad + primaPorCantidadDeEmpleadosACargo);
	}


	private Double calcularSueldoEnBaseALaCantidadDeEmpleadosACargo() {
		Double primaPorCantidadDeEmpleadosACargo = PRIMA_POR_CANTIDAD_DE_EMPLEADOS_A_CARGO * empleadosACargo.size();
		return primaPorCantidadDeEmpleadosACargo;
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

	public Boolean asignarEmpleado(Empleado mesero) {
		return empleadosACargo.add(mesero);
	}

	public List<Empleado> getEmpleadosACargo() {
		return empleadosACargo;
	}


}
