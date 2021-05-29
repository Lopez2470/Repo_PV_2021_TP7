package ar.edu.unju.fi.tp7.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp7.model.Cliente;
import ar.edu.unju.fi.tp7.service.IClienteService;
import ar.edu.unju.fi.tp7.service.imp.ClienteServiceImp;



@Controller
public class ClienteController {
	private static final Log LOGGER = LogFactory.getLog(ClienteServiceImp.class);
	@Autowired
	private Cliente cliente;
	
	@Autowired
	@Qualifier("clienteUtilService")
	private IClienteService clienteService;
	
	
	@GetMapping("cliente/nuevo")
	public String getNuevoClientePage(Model model) {
		model.addAttribute("cliente", cliente);
		LOGGER.info("METHOD: getNuevoClientePage");
		LOGGER.info("RESULT: Visualizamos la pagina cliente-nuevo.html"
				+ " para dar de alta");
		return"cliente-nuevo";
	}
	
	@PostMapping("/cliente/guardar")
	public ModelAndView guardarCliente(@ModelAttribute("cliente") Cliente cliente){
		ModelAndView modelView = new ModelAndView("cliente-nuevo");
		clienteService.guardarCliente(cliente);
		modelView.addObject("cliente-nuevo", clienteService.obtenerClientes());
		LOGGER.info("METHOD: guardarCliente(): Guardamos el nuevo cliente cargado en "
				+ "el formulario de altas de clientes");
		LOGGER.info("RESULT: "+ cliente);
		
		LOGGER.info("METHOD: getClientesPage()");
		LOGGER.info("RESULT: Visualizamos la pagina clientes.html que nos muestra el listado de"
				+ "clientes dados de alta");
		return modelView;
	}
	
	@GetMapping("/cliente/listado")
	public ModelAndView getClientesPage() {
		ModelAndView modelView = new ModelAndView("clientes");
		if (clienteService.obtenerClientes()==null) {
			clienteService.generarTablaCliente();
		}
		modelView.addObject("clientes", clienteService.obtenerClientes());
		
		return modelView;
	}
	
	
}
