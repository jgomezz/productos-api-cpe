package pe.edu.tecsup.productosapi.services;

import pe.edu.tecsup.productosapi.entities.Usuario;

public interface AuthenticationService {

    Usuario findByUsernameAndPassword(String username, String password);

    Usuario findByUsername(String username);

}
