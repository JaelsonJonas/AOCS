package br.com.fiap.aocs.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.UsuarioRepository;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {

        usuarioRepository.saveAll(List.of(
                new Usuario("jaelson@aocs.com.br", "123432"),
                new Usuario("jow@aocs.com.br", "123432"),
                new Usuario("z@aocs.com.br", "123432")));

    }

}
