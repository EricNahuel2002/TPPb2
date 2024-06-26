package ar.edu.unlam.pb2.paquetetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.pb2.dominio.*;

public class TestBase {

	private static final String NOMBRE_RESTAURANTE = "Luigi";
	private Restaurante restaurante;

	@Before
	public void init() {
		this.restaurante = new Restaurante(NOMBRE_RESTAURANTE);
	}

	@Test
	public void queUnEncargadoPuedaHeredarDeEmpleado() {
		Empleado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));

		assertTrue(encargado instanceof Encargado);
		assertTrue(encargado instanceof Empleado);
	}

	@Test
	public void queUnCajeroPuedaHeredarDeEmpleado() {
		Empleado cajero = new Cajero(13,"Enrique",LocalDate.of(2005, 1, 1));

		assertTrue(cajero instanceof Cajero);
		assertTrue(cajero instanceof Empleado);
	}

	@Test
	public void queUnMeseroPuedaHeredarDeEmpleado() {
		Empleado mesero = new Mesero(12,"Juan",LocalDate.of(2015, 5, 6));

		assertTrue(mesero instanceof Mesero);
		assertTrue(mesero instanceof Empleado);
	}

	@Test
	public void queSePuedaAgregarUnEmpleadoAlRestaurante() {
		Empleado mesero = new Mesero(12,"Juan",LocalDate.of(2015, 5, 6));
		
		Boolean empleadoAgregado = restaurante.agregarEmpleado(mesero);
		
		assertTrue(empleadoAgregado);
	}

	@Test
	public void queSePuedaBuscarUnEmpleadoPorSuId() {
		Empleado mesero = new Mesero(12,"Juan",LocalDate.of(2015, 5, 6));
		restaurante.agregarEmpleado(mesero);
		
		Empleado empleadoObtenido = restaurante.buscarUnEmpleado(mesero.getCodigo());
		
		assertEquals(mesero,empleadoObtenido);
	}
	
	@Test
	public void queNoSePuedaAgregarUnEmpleadoConElMismoCodigo() {
		Empleado mesero = new Mesero(12,"Juan",LocalDate.of(2015, 5, 6));
		Empleado mesero2 = new Mesero(12,"Lucas",LocalDate.of(2016, 2, 9));
		Boolean empleadoAgregado = restaurante.agregarEmpleado(mesero);
		Boolean empleadoAgregado2 = restaurante.agregarEmpleado(mesero2);
		
		assertTrue(empleadoAgregado);
		assertFalse(empleadoAgregado2);
	}

	@Test
	public void queSePuedaDespedirUnEmpleado() {
		Empleado mesero = new Mesero(12,"Juan",LocalDate.of(2015, 5, 6));
		restaurante.agregarEmpleado(mesero);
		
		Boolean empleadoDespedido = restaurante.despedirUnEmpleado(mesero.getCodigo());
		
		assertTrue(empleadoDespedido);
	}
	
	@Test
	public void queSePuedaCrearUnCliente() {
		Integer numeroCliente = 12;
		String nombre = "Juan";
		Cliente cliente = new Cliente(numeroCliente,nombre);
		
		assertNotNull(cliente);
	}
	
	@Test
	public void queSePuedaAgregarUnClienteAlRestaurante() {
		Cliente cliente = new Cliente(1,"Juan");
		
		Boolean clienteAgregado = restaurante.agregarCliente(cliente);
		
		assertTrue(clienteAgregado);
	}
	
	@Test
	public void queSePuedaBuscarUnClientePorId() {
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		
		Cliente clienteObtenido = restaurante.buscarUnCliente(cliente.getNumero());
		
		assertEquals(cliente,clienteObtenido);
	}
	
	@Test
	public void queSePuedaAgregarUnaReservaAlRestaurante() {
		Integer id = 213;
		Reserva reserva = new Reserva(id,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		
		Boolean reservaAgregada = restaurante.agregarReserva(reserva);
		
		assertTrue(reservaAgregada);
	}
	
	@Test
	public void queSePuedaAgregarUnaReservaClienteAlRestaurante() {
		ReservaCliente rc = new ReservaCliente(null,null);
		
		Boolean reservaClienteAgregada = restaurante.agregarReservaCliente(rc);
		
		assertTrue(reservaClienteAgregada);
	}

	@Test
	public void queUnClientePuedaHacerUnaReserva() {
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		
		Boolean reservaRealizada = restaurante.realizarReservaCliente(reserva,cliente);
		
		assertTrue(reservaRealizada);
	}

	@Test
	public void queSePuedaObtenerListaDeEmpleadosOrdenadoDeMayorAMenorPorSueldo() {
		Empleado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado cajero = new Cajero(13,"Enrique",LocalDate.of(2005, 1, 1));
		Empleado mesero = new Mesero(12,"Juan",LocalDate.of(2015, 5, 6));
		restaurante.agregarEmpleado(encargado);
		restaurante.agregarEmpleado(cajero);
		restaurante.agregarEmpleado(mesero);
		
		List<Empleado> empleadosObtenidos = restaurante.obtenerListaDeEmpleadosOrdenadoDeMayorAMenorPorSueldo();
		
		assertEquals(encargado,empleadosObtenidos.get(0));
		assertEquals(cajero,empleadosObtenidos.get(1));
		assertEquals(mesero,empleadosObtenidos.get(2));
	}
	
	
	@Test
	public void queSePuedaObtenerLosEncargadosDelRestaurante() {
		Empleado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado encargado2 = new Encargado(12,"lucas",LocalDate.of(2008, 5, 5));
		Empleado cajero = new Cajero(13,"Enrique",LocalDate.of(2005, 1, 1));
		Empleado mesero = new Mesero(10,"Juan",LocalDate.of(2015, 5, 6));
		restaurante.agregarEmpleado(encargado2);
		restaurante.agregarEmpleado(encargado);
		restaurante.agregarEmpleado(cajero);
		restaurante.agregarEmpleado(mesero);
		
		List<Empleado> encargadosObtenidos = restaurante.obtenerEncargados();
		
		assertEquals(2,encargadosObtenidos.size());
	}

	@Test
	public void queSePuedaObtenerListaDeEncargadosOrdenadoDeMayorAMenorPorSueldo() {
		Empleado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado encargado2 = new Encargado(12,"lucas",LocalDate.of(2008, 5, 5));
		Empleado encargado3 = new Encargado(11,"lucia",LocalDate.of(2009, 9, 4));
		restaurante.agregarEmpleado(encargado2);
		restaurante.agregarEmpleado(encargado3);
		restaurante.agregarEmpleado(encargado);
		
		List<Empleado> empleadosObtenidos = restaurante.obtenerListaDeEncargadosOrdenadoDeMayorAMenorPorSueldo();
		
		assertEquals(encargado2,empleadosObtenidos.get(0));
		assertEquals(encargado3,empleadosObtenidos.get(1));
		assertEquals(encargado,empleadosObtenidos.get(2));
	}

	@Test
	public void queSePuedaObtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo() {
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado mesero2 = new Mesero(12,"lucas",LocalDate.of(2008, 5, 5));
		Empleado mesero3 = new Mesero(11,"lucia",LocalDate.of(2009, 9, 4));
		restaurante.agregarEmpleado(mesero2);
		restaurante.agregarEmpleado(mesero3);
		restaurante.agregarEmpleado(mesero);
		
		List<Empleado> empleadosObtenidos = restaurante.obtenerListaDeMeserosOrdenadoDeMayorAMenorPorSueldo();
		
		assertEquals(mesero2,empleadosObtenidos.get(0));
		assertEquals(mesero3,empleadosObtenidos.get(1));
		assertEquals(mesero,empleadosObtenidos.get(2));
	}

	@Test
	public void queSePuedaObtenerListaDeCajerosOrdenadoDeMayorAMenorPorSueldo() {
		Empleado cajero = new Cajero(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado cajero2 = new Cajero(12,"lucas",LocalDate.of(2008, 5, 5));
		Empleado cajero3 = new Cajero(11,"lucia",LocalDate.of(2009, 9, 4));
		restaurante.agregarEmpleado(cajero2);
		restaurante.agregarEmpleado(cajero3);
		restaurante.agregarEmpleado(cajero);
		
		List<Empleado> empleadosObtenidos = restaurante.obtenerListaDeCajerosOrdenadoDeMayorAMenorPorSueldo();
		
		assertEquals(cajero2,empleadosObtenidos.get(0));
		assertEquals(cajero3,empleadosObtenidos.get(1));
		assertEquals(cajero,empleadosObtenidos.get(2));
	}
	
	@Test
	public void queSePuedaSaberCuantasVecesUnClienteVinoEnUnaSemana() {
		// almacenar cuantas reservas tiene
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		Reserva reserva2 = new Reserva(2,LocalDate.of(2024, 7, 5),LocalTime.of(16, 0));
		Reserva reserva3 = new Reserva(3,LocalDate.of(2024, 9, 6),LocalTime.of(16, 0));
		Reserva reserva4 = new Reserva(4,LocalDate.of(2024, 9, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.agregarReserva(reserva2);
		restaurante.agregarReserva(reserva3);
		restaurante.agregarReserva(reserva4);
		Cliente cliente = new Cliente(1,"Juan");
		Cliente cliente2 = new Cliente(2,"Lucas");
		restaurante.agregarCliente(cliente);
		restaurante.agregarCliente(cliente2);
		restaurante.realizarReservaCliente(reserva,cliente);
		restaurante.realizarReservaCliente(reserva,cliente2);
		restaurante.realizarReservaCliente(reserva2,cliente);
		restaurante.realizarReservaCliente(reserva3,cliente);
		
		List<Reserva> reservasObtenidasHechasPorUnCliente = restaurante.obtenerHistorialDeReservasDeUnCliente(cliente);
		
		assertEquals(3,reservasObtenidasHechasPorUnCliente.size());
	}
	
	@Test
	public void queUnMeseroTomeLaReservaDeUnCliente() {
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.realizarReservaCliente(reserva,cliente);
		
		Boolean reservaClienteTomadaPorMesero = restaurante.queUnMeseroTomeUnaReservaCliente(reserva,cliente,mesero);
		
		assertTrue(reservaClienteTomadaPorMesero);
	}
	
	
	@Test
	public void queSePuedaObtenerElMeseroDelMes() {
		// el que mas mesas atendio
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado mesero2 = new Mesero(12,"lucas",LocalDate.of(2008, 5, 5));
		restaurante.agregarEmpleado(mesero);
		restaurante.agregarEmpleado(mesero2);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		Cliente cliente2 = new Cliente(2,"Luis");
		restaurante.agregarCliente(cliente2);
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Reserva reserva2 = new Reserva(2,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva2);
		Reserva reserva3 = new Reserva(3,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva3);
		restaurante.realizarReservaCliente(reserva,cliente);
		restaurante.realizarReservaCliente(reserva2,cliente);
		restaurante.realizarReservaCliente(reserva3,cliente2);
		restaurante.queUnMeseroTomeUnaReservaCliente(reserva,cliente,mesero2);
		restaurante.queUnMeseroTomeUnaReservaCliente(reserva2,cliente,mesero2);
		restaurante.queUnMeseroTomeUnaReservaCliente(reserva3,cliente2,mesero);
		
		Empleado meseroDelMes = restaurante.obtenerElMeseroDelMes();
		
		assertEquals(mesero2,meseroDelMes);
	}
	
	@Test
	public void queSePuedaObtenerLaCantidadDeClientesQueFueronAlRestaurante() {
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		Reserva reserva2 = new Reserva(2,LocalDate.of(2024, 7, 5),LocalTime.of(16, 0));
		Reserva reserva3 = new Reserva(3,LocalDate.of(2024, 9, 6),LocalTime.of(16, 0));
		Reserva reserva4 = new Reserva(4,LocalDate.of(2024, 9, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		restaurante.agregarReserva(reserva2);
		restaurante.agregarReserva(reserva3);
		restaurante.agregarReserva(reserva4);
		Cliente cliente = new Cliente(1,"Juan");
		Cliente cliente2 = new Cliente(2,"Lucas");
		Cliente cliente3 = new Cliente(3,"Roberto");
		restaurante.agregarCliente(cliente);
		restaurante.agregarCliente(cliente2);
		restaurante.agregarCliente(cliente3);
		restaurante.realizarReservaCliente(reserva,cliente);
		restaurante.realizarReservaCliente(reserva,cliente2);
		restaurante.realizarReservaCliente(reserva2,cliente);
		restaurante.realizarReservaCliente(reserva3,cliente3);
		
		HashSet<Cliente> clientesObtenidos = restaurante.obtenerLaCantidadDeClientesQueFueronAlRestaurante();
		
		assertEquals(3,clientesObtenidos.size());
	}
	
	@Test
	public void queNoHallanPedidosClientesDuplicados() {
		//no se puede dar la misma combinacion dos veces de una reserva con un cliente
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		
		Boolean reservaClienteRealizada = restaurante.realizarReservaCliente(reserva,cliente);
		Boolean reservaClienteRealizada2 = restaurante.realizarReservaCliente(reserva,cliente);
		
		assertTrue(reservaClienteRealizada);
		assertFalse(reservaClienteRealizada2);
	}
	
	
	
	//AGREGADO HOY 21/5
	@Test
	public void queSePuedaCalcularElSueldoEnBaseALaAntiguedadDeUnEmpleado() {
		Empleado cajero2 = new Cajero(7,"cleopatra",LocalDate.of(2014, 06, 10));
		cajero2.setSueldo(2000.0);
		restaurante.agregarEmpleado(cajero2);
		Double sueldoEnBaseALaAntiguedad = this.restaurante.calcularElSueldoEnBaseALaAntiguedadDeUnEmpleado(cajero2);
		
		Double esperado= 700.0;
		assertEquals(esperado, sueldoEnBaseALaAntiguedad);
	}
	
	@Test
	public void queSePuedaPagarElSueldoDeUnEmpleado() {
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		Double sueldo = 2000.0;
		Boolean sueldoPagado = restaurante.pagarSueldoAUnEmpleado(mesero,sueldo);
		Empleado meseroObtenido = restaurante.buscarUnEmpleado(mesero.getCodigo());
		Double sueldoEsperado = 2800.0;
		
		assertTrue(sueldoPagado);
		assertEquals(sueldoEsperado,meseroObtenido.getSueldo());
	}
	
	@Test
	public void dadoQueSeCalculaElSuedoEnBaseALaAntiguedadDeUnEmpleadoQueSeTengaEnCuentaAdemasElSueldoEspecificoDeUnMesero() {
		Reserva reserva = new Reserva(1,LocalDate.of(2024, 5, 20),LocalTime.of(16, 0));
		restaurante.agregarReserva(reserva);
		Cliente cliente = new Cliente(1,"Juan");
		restaurante.agregarCliente(cliente);
		restaurante.realizarReservaCliente(reserva,cliente);
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		restaurante.agregarEmpleado(mesero);
		restaurante.queUnMeseroTomeUnaReservaCliente(reserva, cliente, mesero);
		restaurante.pagarSueldoAUnEmpleado(mesero,2000.0);
		Double sueldoEsperado = 2850.0;
		
		Empleado meseroObtenido = restaurante.buscarUnEmpleado(mesero.getCodigo());
		
		assertEquals(sueldoEsperado,meseroObtenido.getSueldo());
	}
	
	@Test
	public void dadoQueSeCalculaElSuedoEnBaseALaAntiguedadDeUnEmpleadoQueSeTengaEnCuentaAdemasElSueldoEspecificoDeUnCajero() {
		Empleado cajero = new Cajero(13,"Enrique",LocalDate.of(2005, 1, 1));
		restaurante.agregarEmpleado(cajero);
		restaurante.pagarSueldoAUnEmpleado(cajero,1000.0);
		
		Empleado cajeroObtenido = restaurante.buscarUnEmpleado(cajero.getCodigo());
		Double sueldoEsperado = 1400.0;
		
		assertEquals(sueldoEsperado, cajeroObtenido.getSueldo());
	}
	
	@Test
	public void queSePuedaAsignarEmpleadosAUnEncargado() {
		Empleado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado mesero2 = new Mesero(12,"lucas",LocalDate.of(2008, 5, 5));
		restaurante.agregarEmpleado(encargado);
		restaurante.agregarEmpleado(mesero);
		restaurante.agregarEmpleado(mesero2);
		
		Boolean empleadoAsignadoAEncargado = restaurante.asignarEmpleadoAEncargado(encargado,mesero);
		restaurante.asignarEmpleadoAEncargado(encargado,mesero2);
		Empleado empleado = restaurante.buscarUnEmpleado(encargado.getCodigo());
		
		assertTrue(empleadoAsignadoAEncargado);
		assertEquals(2,((Encargado) empleado).getEmpleadosACargo().size());
	}
	
	
	@Test
	public void dadoQueSeCalculaElSuedoEnBaseALaAntiguedadDeUnEmpleadoQueSeTengaEnCuentaAdemasElSueldoEspecificoDeUnEncargado() {
		Empleado encargado = new Encargado(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado mesero = new Mesero(15,"pepe",LocalDate.of(2009, 1, 5));
		Empleado mesero2 = new Mesero(12,"lucas",LocalDate.of(2008, 5, 5));
		restaurante.agregarEmpleado(encargado);
		restaurante.agregarEmpleado(mesero);
		restaurante.agregarEmpleado(mesero2);
		restaurante.asignarEmpleadoAEncargado(encargado,mesero);
		restaurante.asignarEmpleadoAEncargado(encargado,mesero2);
		
		restaurante.pagarSueldoAUnEmpleado(encargado,5000.0);
		
		Empleado encargadoObtenido = restaurante.buscarUnEmpleado(encargado.getCodigo());
		Double sueldoEsperado = 7200.0;
		
		assertEquals(sueldoEsperado, encargadoObtenido.getSueldo());
	}
	

	@Test
	public void queSePuedaCalcularUnAguinaldo() {
		
	}


	/*
	 * @Test public void queSePuedaCrearUnaComida() {
	 * 
	 * }
	 */

	/*
	 * @Test public void queSePuedaVenderUnProducto() {
	 * 
	 * }
	 */

	// clase intermedia entre cliente y comida



	// clase intermedia entre cliente y reserva y el id del mesero
	

}
