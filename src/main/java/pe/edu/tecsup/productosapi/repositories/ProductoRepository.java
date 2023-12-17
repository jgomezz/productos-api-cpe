package pe.edu.tecsup.productosapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.productosapi.entities.Producto;

/**
 * 
 * @author jgomez
 *
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	@Override
	List<Producto> findAll();
	
}