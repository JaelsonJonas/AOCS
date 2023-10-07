package br.com.fiap.aocs.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.aocs.models.Token;
import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.UsuarioRepository;
import jakarta.validation.Valid;

@Service
public class TokenService {

    @Autowired
    private UsuarioRepository repository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken( @Valid Usuario credencial){
        Algorithm alg = Algorithm.HMAC256(secret);
        String token = JWT.create()
                        .withSubject(credencial.getLogin())
                        .withIssuer("AOCS")
                        .withExpiresAt(Instant.now().plus(1,ChronoUnit.HOURS))
                        .sign(alg);
        
        return new Token(token, "JWT",  "Bearer");
    }


    public Usuario getValidateUser(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                    .withIssuer("AOCS")
                    .build()
                    .verify(token)
                    .getSubject()
                    ;

        return repository.findByLogin(email)
                    .orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }
}
