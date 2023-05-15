package br.com.fiap.aocs.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.aocs.models.Credencial;
import br.com.fiap.aocs.models.Token;
import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.UsuarioRepository;
import jakarta.validation.Valid;

public class TokenService {

    @Autowired
    private UsuarioRepository repository;

    public Token generateToken( @Valid Credencial credencial){
        Algorithm alg = Algorithm.HMAC256("jow");
        String token = JWT.create()
                        .withSubject(credencial.email())
                        .withIssuer("AOCS")
                        .withExpiresAt(Instant.now().plus(1,ChronoUnit.HOURS))
                        .sign(alg);
        
        return new Token(token, "JWT",  "Bearer");
    }


    public Usuario getValidateUser(String token) {
        Algorithm alg = Algorithm.HMAC256("jow");
        var email = JWT.require(alg)
                    .withIssuer("AOCS")
                    .build()
                    .verify(token)
                    .getSubject()
                    ;

        return repository.findByEmail(email)
                    .orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }
}
