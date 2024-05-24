package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Mesero extends Empleado{
	private Integer cantidadDePedidosTomados;
	private static final Double PORCIENTO_A_SUBIR_ANTES = 0.40;
	private static final Double PORCIENTO_A_SUBIR_DESPUES = 0.35;
	private final Double PRIMA_POR_PEDIDO = 50.0;

	public Mesero(Integer codigo, String nombre, LocalDate anioIngreso) {
		super(codigo, nombre, anioIngreso);
		this.cantidadDePedidosTomados = 0;
	}
	
	public void incrementarCantidadDePedidosTomados() {
		this.cantidadDePedidosTomados++;
	}
	//cantidad de pedidos
	//propina o bono extra en base a la cantidad de pedidos

	public Integer getCantidadDePedidosTomados() {
		return cantidadDePedidosTomados;
	}

	@Override
	public void cobrarSueldo(Double sueldo) {
		Double primaPorAntiguedad = this.calcularSueldoEnBaseALaAntiguedad(sueldo);
		Double primaPorPedido = this.calcularSueldoEnBaseALaCantidadDePedidos();
		super.setSueldo(sueldo + primaPorAntiguedad + primaPorPedido);
	}
	private Double calcularSueldoEnBaseALaCantidadDePedidos() {
		Double primaPorPedido = this.PRIMA_POR_PEDIDO * cantidadDePedidosTomados;
		return primaPorPedido;
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
