package ar.edu.unju.fi.tp7.service.imp;



import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tp7.model.Producto;
import ar.edu.unju.fi.tp7.service.IProductoService;
import ar.edu.unju.fi.tp7.util.TablaProducto;



@Service
public class IProductoServiceImp implements IProductoService {
	
	@Autowired
	Producto producto;
	private List<Producto> productos;
	/*List<Producto> productos = new ArrayList<Producto>();*/
	
	private static final Log LOGGER = LogFactory.getLog(IProductoServiceImp.class);
	
	@Override
	public void generarTablaProducto() {
		productos = TablaProducto.listaProductos;
		productos.add(new Producto(201, "Producto 201", 2010.00, "Marca 201", 10));
	}
	
	
	/*Se realiza el alta del producto nuevo a la lista 'productos'*/
	@Override
	public void altaProducto(Producto producto) {
		LOGGER.info("METHOD: altaProducto(Producto producto): Agrega el objeto Producto a la lista de productos");
		/*LOGGER.info("RESULT:" + productos.get(productos.size() -1));	*/	
		
		if (productos==null) {
			generarTablaProducto();	
		}
		productos.add(producto);
		LOGGER.info("METHOD: altaProducto(Producto producto): Agrega el objeto Producto a la lista de productos");
		/*LOGGER.info("RESULT:" + productos.get(productos.size() -1));*/
	}

	/*Se busca el ultimo objeto dado de alta*/
	@Override
	public Producto getUltimoProducto() {
		Producto aux_producto = productos.get(productos.size() -1);
		LOGGER.info("METHOD: getUltimoProducto(): Se realiza la busqueda del ultimo objeto Producto agregado a la lista de productos");
		/*LOGGER.info("RESULT:" +  productos.get(productos.size() -1));*/	
		return aux_producto;
	}

	@Override
	public Producto getProducto() {
		return this.producto;
	}

	/*Visualiza al ultimo producto dado de alta*/
	@Override
	public List<Producto> getAllProductos() {
		// TODO Auto-generated method stub
		return productos;
	}


	@Override
	public Producto getProductoPorCodigo(int codigo) {
		Producto Aux_producto = new Producto();
		for (Producto p : productos) {
			if (p.getCodigo() == codigo) {
				Aux_producto = p;
			}
		}
		return Aux_producto;
	}


	


}
