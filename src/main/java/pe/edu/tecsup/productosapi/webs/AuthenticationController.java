package pe.edu.tecsup.productosapi.webs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.tecsup.productosapi.entities.Usuario;
import pe.edu.tecsup.productosapi.services.AuthenticationService;
import pe.edu.tecsup.productosapi.services.JwtTokenService;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public Usuario login(@RequestParam String username, @RequestParam String password) throws Exception{

        log.info("login("+username+", "+password+")");

        Usuario usuario = authenticationService.findByUsernameAndPassword(username, password);
        log.info("Login success: " + usuario);

        if(usuario == null) throw new Exception("Usuario y/o clave invalido");

        String token = jwtTokenService.generateToken(usuario.getUsername());

        log.info("Token: " + token);

        usuario.setToken(token);

        return usuario;
    }


}
