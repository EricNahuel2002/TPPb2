package ar.edu.unlam.pb2.dominio;

public class ReservaCliente {
	
	private Pedido pedido;
	private Cliente cliente;
	private Empleado mesero;
	//que una pedido tenga una COLECCION DE MESEROS

	public ReservaCliente(Pedido pedido, Cliente cliente) {
		this.pedido = pedido;
		this.cliente = cliente;
	}

	public Pedido getReserva() {
		return pedido;
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
