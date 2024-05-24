package ar.edu.unlam.pb2.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Reserva {

	private Integer id;
	private LocalDate dia;
	private LocalTime hora;
	private Empleado mesero;

	public Reserva(Integer id, LocalDate dia, LocalTime hora) {
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

	@Override
	public int hashCode() {
		return Objects.hash(dia, hora, id, mesero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(id, other.id);
	}
	
}
