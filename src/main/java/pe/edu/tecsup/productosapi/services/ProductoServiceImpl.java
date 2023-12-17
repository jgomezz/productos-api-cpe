package pe.edu.tecsup.productosapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import pe.edu.tecsup.productosapi.entities.Producto;
import pe.edu.tecsup.productosapi.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<Producto> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public Producto findById(Long id) {
		return productoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No existe registro"));
	}

	@Override
	public void save(Producto producto) {
		productoRepository.save(producto);
	}

	@Override
	public void deleteById(Long id) {
		productoRepository.deleteById(id);
	}

}
