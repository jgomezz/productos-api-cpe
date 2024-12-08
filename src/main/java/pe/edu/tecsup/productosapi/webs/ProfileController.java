package pe.edu.tecsup.productosapi.webs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.tecsup.productosapi.entities.Usuario;

@Slf4j
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @GetMapping
    public Usuario profile(@AuthenticationPrincipal Usuario usuario) {
        log.info("profile("+usuario+")");
        return usuario;
    }

}
