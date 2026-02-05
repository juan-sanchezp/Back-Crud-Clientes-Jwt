package co.edu.unicauca.asae.core.init;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.edu.unicauca.asae.core.modelo.seguridad.ERol;
import co.edu.unicauca.asae.core.modelo.seguridad.Rol;
import co.edu.unicauca.asae.core.modelo.seguridad.Usuario;
import co.edu.unicauca.asae.core.repository.RolRepository;
import co.edu.unicauca.asae.core.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RolRepository rolRepository,
                           UsuarioRepository usuarioRepository,
                           PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // Crear roles
        Rol rolUser = rolRepository.findByNombre(ERol.ROLE_USER)
                .orElseGet(() -> rolRepository.save(new Rol(ERol.ROLE_USER)));

        Rol rolAdmin = rolRepository.findByNombre(ERol.ROLE_ADMIN)
                .orElseGet(() -> rolRepository.save(new Rol(ERol.ROLE_ADMIN)));

        // Crear admin
        if (!usuarioRepository.existsByUsername("admin")) {

            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setNombre("Administrador");
            admin.setEmail("admin@sistema.local");
            admin.setRoles(Set.of(rolAdmin));

            usuarioRepository.save(admin);

            System.out.println("✔ Usuario ADMIN creado");
        }
    }
}

