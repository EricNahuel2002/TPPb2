package ar.edu.unlam.pb2.dominio;

public class ReservaCliente {
	
	private Reserva reserva;
	private Cliente cliente;
	private Empleado mesero;

	public ReservaCliente(Reserva reserva, Cliente cliente) {
		this.reserva = reserva;
		this.cliente = cliente;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setMesero(Empleado mesero) {
		this.mesero = mesero;
	}

	public Empleado getMesero() {
		return mesero;
	}
}
