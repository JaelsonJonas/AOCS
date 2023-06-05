package br.com.fiap.aocs.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.aocs.exceptions.RestNotFoundException;
import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.UsuarioRepository;
import br.com.fiap.aocs.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        log.info("Chamando Filter!!");
        String tokenJWT = recuperarToken(request); //recupera o Token do header da request 

        if (tokenJWT != null) {
            String subject = tokenService.getSubject(tokenJWT);
            log.info(subject);

            Usuario usuario = repository.findByLogin(subject)
                    .orElseThrow(() -> new RestNotFoundException("Usuario not found"));

            UsernamePasswordAuthenticationToken authentucation = new UsernamePasswordAuthenticationToken(usuario, null,
                    usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentucation);
        }

        filterChain.doFilter(request, response); // necessario para chamar os proximos filtros da aplicação

    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null)
            return authorizationHeader.replace("Bearer ", "");

        return null;

    }

}
