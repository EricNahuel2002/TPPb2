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
	private List<Pedido> pedidos;
	private List<ReservaCliente> pedidosClientes;

	public Restaurante(String nombreRestaurante) {
		this.nombre = nombreRestaurante;
		this.empleados = new ArrayList<>();
		this.clientes = new ArrayList<>();
		this.pedidos = new ArrayList<>();
		this.pedidosClientes = new ArrayList<>();
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
		ordenarEmpleados(encargados);
		return encargados;
	}

	private void ordenarEmpleados(List<Empleado> encargados) {
		Collections.sort(encargados, (o1, o2) -> o2.getSueldo().compareTo(o1.getSueldo()));
	}
	@Override
	public List<Empleado> obtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo() {
		List<Empleado> meseros = this.obtenerMeseros();
		ordenarEmpleados(meseros);
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
		ordenarEmpleados(cajeros);
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
		Empleado mesero = pedidosClientes.get(0).getMesero();
		for(ReservaCliente rc: pedidosClientes) {
			if(((Mesero) rc.getMesero()).getCantidadDePedidosTomados() > ((Mesero) mesero).getCantidadDePedidosTomados()) {
				mesero = rc.getMesero();
			}
		}
		return mesero;
	}
	@Override
	public HashSet<Cliente> obtenerLaCantidadDeClientesQueFueronAlRestaurante() {
		HashSet<Cliente> clientes = new HashSet<>();
		for(ReservaCliente rc: pedidosClientes) {
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
	public Boolean agregarReserva(Pedido pedido) {
		return pedidos.add(pedido);
	}

	public Boolean realizarReservaCliente(Pedido pedido, Cliente cliente) {
		Pedido pedidoEncontrada = this.buscarReserva(pedido.getId());
		Cliente clienteEncontrado = this.buscarUnCliente(cliente.getNumero());
		//buscar pedidocliente
				//if(pedidoClienteEncontrada == null){
				// return false
		if(pedidoEncontrada != null && clienteEncontrado != null) {
			ReservaCliente rc = new ReservaCliente(pedidoEncontrada,clienteEncontrado);
			return pedidosClientes.add(rc);
		}
		return false;
	}
	private Pedido buscarReserva(Integer id) {
		for(Pedido pedido: pedidos) {
			if(pedido.getId().equals(id)) {
				return pedido;
			}
		}
		return null;
	}

	public Boolean agregarReservaCliente(ReservaCliente rc) {
		return pedidosClientes.add(rc);
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

	public List<Pedido> obtenerHistorialDeReservasDeUnCliente(Cliente cliente) {
		List<Pedido> pedidosDeUnCliente = new ArrayList<>();
		for(ReservaCliente rc:pedidosClientes) {
			if(rc.getCliente().equals(cliente)) {
				pedidosDeUnCliente.add(rc.getReserva());
			}
		}
		return pedidosDeUnCliente;
	}
	
	//									pedido  n----n clientes sale clase intermedia
	//									en 

	public Boolean queUnMeseroTomeUnaReservaCliente(Pedido pedido, Cliente cliente, Empleado mesero) {
		for(ReservaCliente rc: pedidosClientes) {
			if(rc.getReserva().equals(pedido) && rc.getCliente().equals(cliente)) {
				rc.setMesero(mesero);
				((Mesero) mesero).incrementarCantidadDePedidosTomados();
				return true;
			}
		}
		return false;
	}
}
