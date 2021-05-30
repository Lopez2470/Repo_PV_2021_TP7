package ar.edu.unju.fi.tp7.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp7.model.Cuenta;
import ar.edu.unju.fi.tp7.service.IClienteService;
import ar.edu.unju.fi.tp7.service.ICuentaService;

@Controller
public class CuentaController {

	private Cuenta cuenta;
	
	@Autowired
	@Qualifier("cuentaServiceMysql")
	private ICuentaService cuentaService;
	
	@Autowired
	@Qualifier("clienteServiceMysql")
	private IClienteService clienteService;
	
	@GetMapping("/cuenta/nueva")
	public String getCuentaNuevaPage(Model model) {
		model.addAttribute(cuenta);
		return "cuenta-nueva";
	}
	
	@PostMapping("/cuenta/guardar")
	public ModelAndView guardarCuentaPage(@ModelAttribute("cuenta") Cuenta cuenta) {
		ModelAndView modelView = new ModelAndView("cuentas");
		//Cliente cliente = clienteService.getClientePorDocumento(cuenta.getCliente().getNroDocumento());
		//cuenta.setCliente(cliente);
		cuentaService.guardarCuenta(cuenta);
		modelView.addObject("cuentas", cuentaService.getCuentas());
		return modelView;		
	}
	
	@GetMapping("/cuenta/listado")
	public ModelAndView getListadoCuentaPage() {
		ModelAndView modelView = new ModelAndView("cuentas");
		modelView.addObject("cuentas", cuentaService.getCuentas());
		return modelView;
	}
}
