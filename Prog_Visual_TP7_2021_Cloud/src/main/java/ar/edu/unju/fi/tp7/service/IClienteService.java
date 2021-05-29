package ar.edu.unju.fi.tp7.service;

import java.util.List;

import ar.edu.unju.fi.tp7.model.Cliente;



public interface IClienteService {
	
	public void generarTablaCliente();
	
	public void guardarCliente(Cliente cliente);
	
	public List<Cliente> obtenerClientes();

}
