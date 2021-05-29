package ar.edu.unju.fi.tp7.service.imp;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp7.model.Cliente;
import ar.edu.unju.fi.tp7.service.IClienteService;
import ar.edu.unju.fi.tp7.util.TablaCliente;




@Service("clienteUtilService")
public class ClienteServiceImp implements IClienteService {
	private static final Log LOGGER = LogFactory.getLog(ClienteServiceImp.class);
	private List<Cliente> clientes;

	@Override
	public void generarTablaCliente() {
		clientes = TablaCliente.listaClientes;
		clientes.add(new Cliente("Documento", 20159753, "Maria Diaz", "email@hot.com","password", LocalDate.of(1980,05,20), 20, 388, 5123963, LocalDate.of(2020, 10,20)));
		LOGGER.info("METHOD: Agregamos un cliente manualmente a la tabla");
		LOGGER.info("RESULT: " + clientes);
	}

	@Override
	public void guardarCliente(Cliente cliente) {
		if (clientes==null) {
			generarTablaCliente();
		}
		cliente.getEdad();
		clientes.add(cliente);
		LOGGER.info("METHOD: clientes.add(cliente): Guardamos el objeto cliente al array clientes");
		LOGGER.info("RESULT: "  + cliente);
	}

	@Override
	public List<Cliente> obtenerClientes() {
		LOGGER.info("METHOD: Obtenemos el cliente");
		LOGGER.info("RESULT:" + clientes);
		return clientes;
	}

}
