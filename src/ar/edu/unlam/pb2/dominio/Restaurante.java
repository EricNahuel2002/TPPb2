package ar.edu.unlam.pb2.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashSet;
public class Restaurante implements IRestaurante{

	private String nombre;
	private List<Empleado> empleados;
	private List<Cliente> clientes;
	private List<Reserva> reservas;
	private List<ReservaCliente> reservasClientes;

	public Restaurante(String nombreRestaurante) {
		this.nombre = nombreRestaurante;
		this.empleados = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.reservas = new ArrayList<>();
		this.reservasClientes = new ArrayList<>();
	}
	//cantidad de veces que un cliente fue al restaurante
	//coleccion de clientes
	//coleccion de empleados

	public Boolean agregarEmpleado(Empleado mesero) {
		Empleado empleadoEncontrado = this.buscarUnEmpleado(mesero.getCodigo());
		if(empleadoEncontrado == null) {
		return empleados.add(mesero);
		}
		return false;
	}

	public Empleado buscarUnEmpleado(Integer codigo) {
		for(Empleado empleado: empleados) {
			if(empleado.getCodigo().equals(codigo)) {
				return empleado;
			}
		}
		return null;
	}
	@Override
	public Boolean despedirUnEmpleado(Integer codigo) {
		Empleado empleado = this.buscarUnEmpleado(codigo);
		if(empleado != null) {
			return empleados.remove(empleado);
		}
		return false;
	}
	@Override
	public List<Empleado> obtenerListaDeEmpleadosOrdenadoDeMayorAMenorPorSueldo() {
		Collections.sort(empleados, (o1, o2) -> o2.getSueldo().compareTo(o1.getSueldo()));
		return this.empleados;
	}
	@Override
	public List<Empleado> obtenerListaDeEncargadosOrdenadoDeMayorAMenorPorSueldo() {
		List<Empleado> encargados = this.obtenerEncargados();
		Collections.sort(encargados, (o1, o2) -> o2.getSueldo().compareTo(o1.getSueldo()));
		return encargados;
	}
	@Override
	public List<Empleado> obtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo() {
		List<Empleado> meseros = this.obtenerMeseros();
		Collections.sort(meseros, (o1, o2) -> o2.getSueldo().compareTo(o1.getSueldo()));
		return meseros;
	}
	private List<Empleado> obtenerMeseros() {
		List<Empleado> meseros = new ArrayList<>();
		for(Empleado empleado: this.empleados) {
			if(empleado instanceof Mesero) {
				meseros.add(empleado);
			}
		}
		return meseros;
	}
	@Override
	public List<Empleado> obtenerListaDeCajerosOrdenadoDeMayorAMenorPorSueldo() {
		List<Empleado> cajeros = this.obtenerCajeros();
		Collections.sort(cajeros, (o1, o2) -> o2.getSueldo().compareTo(o1.getSueldo()));
		return cajeros;
	}
	private List<Empleado> obtenerCajeros() {
		List<Empleado> cajeros = new ArrayList<>();
		for(Empleado empleado: this.empleados) {
			if(empleado instanceof Cajero) {
				cajeros.add(empleado);
			}
		}
		return cajeros;
	}
	@Override
	public Empleado obtenerElMeseroDelMes() {
		Empleado mesero = reservasClientes.get(0).getMesero();
		for(ReservaCliente rc: reservasClientes) {
			if(((Mesero) rc.getMesero()).getCantidadDePedidosTomados() > ((Mesero) mesero).getCantidadDePedidosTomados()) {
				mesero = rc.getMesero();
			}
		}
		return mesero;
	}
	@Override
	public HashSet<Cliente> obtenerLaCantidadDeClientesQueFueronAlRestaurante() {
		HashSet<Cliente> clientes = new HashSet<>();
		for(ReservaCliente rc: reservasClientes) {
			clientes.add(rc.getCliente());
		}
		return clientes;
	}
	@Override
	public Boolean agregarCliente(Cliente cliente) {
		return this.clientes.add(cliente);
	}
	@Override
	public Cliente buscarUnCliente(Integer id) {
		for(Cliente cliente: clientes) {
			if(cliente.getNumero().equals(id)) {
				return cliente;
			}
		}
		return null;
	}
	@Override
	public Double calcularElSueldoEnBaseALaAntiguedadDeUnEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean agregarReserva(Reserva reserva) {
		return reservas.add(reserva);
	}

	public Boolean realizarReservaCliente(Reserva reserva, Cliente cliente) {
		Reserva reservaEncontrada = this.buscarReserva(reserva.getId());
		Cliente clienteEncontrado = this.buscarUnCliente(cliente.getNumero());
		if(reservaEncontrada != null && clienteEncontrado != null) {
			ReservaCliente rc = new ReservaCliente(reservaEncontrada,clienteEncontrado);
			return reservasClientes.add(rc);
		}
		return false;
	}
	private Reserva buscarReserva(Integer id) {
		for(Reserva reserva: reservas) {
			if(reserva.getId().equals(id)) {
				return reserva;
			}
		}
		return null;
	}

	public Boolean agregarReservaCliente(ReservaCliente rc) {
		return reservasClientes.add(rc);
	}

	public List<Empleado> obtenerEncargados() {
		List<Empleado> encargados = new ArrayList<>();
		for(Empleado empleado:empleados) {
			if(empleado instanceof Encargado) {
				encargados.add(empleado);
			}
		}
		return encargados;
	}

	public List<Reserva> obtenerHistorialDeReservasDeUnCliente(Cliente cliente) {
		List<Reserva> reservasDeUnCliente = new ArrayList<>();
		for(ReservaCliente rc:reservasClientes) {
			if(rc.getCliente().equals(cliente)) {
				reservasDeUnCliente.add(rc.getReserva());
			}
		}
		return reservasDeUnCliente;
	}

	public Boolean queUnMeseroTomeUnaReservaCliente(Reserva reserva, Cliente cliente, Empleado mesero) {
		for(ReservaCliente rc: reservasClientes) {
			if(rc.getReserva().equals(reserva) && rc.getCliente().equals(cliente)) {
				rc.setMesero(mesero);
				((Mesero) mesero).incrementarCantidadDePedidosTomados();
				return true;
			}
		}
		return false;
	}
}
