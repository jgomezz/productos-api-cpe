package pe.edu.tecsup.productosapi.services;

import org.springframework.stereotype.Service;
import pe.edu.tecsup.productosapi.entities.Usuario;
import pe.edu.tecsup.productosapi.repositories.AuthenticationRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private AuthenticationRepository usuarioRepository;

    AuthenticationServiceImpl(AuthenticationRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario findByUsernameAndPassword(String username, String password) {
        return usuarioRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
