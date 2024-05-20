package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;

public class Mesero extends Empleado{
	
	private Integer cantidadDePedidosTomados;

	public Mesero(Integer codigo, String nombre, Double sueldo, LocalDate anioIngreso) {
		super(codigo, nombre, sueldo, anioIngreso);
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
	
}
