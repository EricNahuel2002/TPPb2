package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Pedido {

	private Integer id;
	private LocalDate dia;
	private LocalTime hora;
	private Empleado mesero;

	public Pedido(Integer id, LocalDate dia, LocalTime hora) {
		this.id = id;
		this.dia = dia;
		this.hora = hora;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getDia() {
		return dia;
	}

	public LocalTime getHora() {
		return hora;
	}
	
}
