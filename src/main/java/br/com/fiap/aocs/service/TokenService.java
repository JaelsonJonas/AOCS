package br.com.fiap.aocs.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.aocs.models.Usuario;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("AOCS") // nome da empresa resposavel pela APi
                    .withSubject(usuario.getLogin()) // incluindo o login do usuario no Token
                    .withClaim("id", usuario.getId()) // incluindo o id do usuario no Token
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o Token", exception);
        }

    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("AOCS") // nome da empresa resposavel pela APi
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT invalido ou expirado");
        }

    }

    // Metodo para criar o tempo para expirar o token
    private Instant dataExpiracao() {
        return LocalDateTime.now() // pega a hora atual
                .plusHours(2) // soma a hora atual com base no valor do argumento
                .toInstant(ZoneOffset.of("-03:00")); // seta o fuso horario, no caso esta em GMT-3
    }

}
