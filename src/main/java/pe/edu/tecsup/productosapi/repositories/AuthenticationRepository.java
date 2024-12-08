package pe.edu.tecsup.productosapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.tecsup.productosapi.entities.Usuario;

public interface AuthenticationRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.username=:username AND u.password=:password")
    Usuario findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM Usuario u WHERE u.username=:username")
    Usuario findByUsername(@Param("username") String username);

}
