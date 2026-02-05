package co.edu.unicauca.asae.core.security;

import co.edu.unicauca.asae.core.modelo.seguridad.Usuario;
import co.edu.unicauca.asae.core.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

//BUSCA el rol del token en la base de datos
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuario no encontrado: " + username));

        List<GrantedAuthority> authorities = usuario.getRoles().stream()
            .map(rol -> new SimpleGrantedAuthority(rol.getNombre().name()))
            .collect(Collectors.toList());


        // 🔍 Debug opcional (recomendado mientras pruebas)
        authorities.forEach(a ->
            System.out.println("ROL CARGADO: " + a.getAuthority())
        );

        return new UserDetailsImpl(
            usuario.getUsername(),
            usuario.getPassword(),
            authorities
        );
    }
}
/*     @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // o desde BD luego

        return new UserDetailsImpl(
            usuario.getUsername(),
            usuario.getPassword(),
            authorities
        );
    }
} */
