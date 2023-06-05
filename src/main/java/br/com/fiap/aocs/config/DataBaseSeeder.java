package br.com.fiap.aocs.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.aocs.models.Tarefa;
import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.TarefaRepository;
import br.com.fiap.aocs.repository.UsuarioRepository;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

        @Autowired
        UsuarioRepository usuarioRepository;

        @Autowired
        TarefaRepository tarefaRepository;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) throws Exception {

                usuarioRepository.saveAll(List.of(
                                new Usuario("jaelson@aocs.com.br", passwordEncoder.encode("123432")),
                                new Usuario("jow@aocs.com.br", passwordEncoder.encode("123432")),
                                new Usuario("z@aocs.com.br", passwordEncoder.encode("123432"))));

                tarefaRepository.saveAll(List.of(
                                Tarefa.builder().titulo("Ler sobre Xadrez")
                                                .descricao("Ler 25 paginas sobre o livro da arte das duas torrer")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build(),
                                Tarefa.builder().titulo("Estudar Spring Framework")
                                                .descricao("ver sobre Spring JPA")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build(),
                                Tarefa.builder().titulo("Estudar Goverancia")
                                                .descricao("Focar sobre Cobit e sobre a ISO 9001")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build(),
                                Tarefa.builder().titulo("Estudar sobre reciclagem")
                                                .descricao("Ver formas de ganhar dinheiro e incluir sobre o projeto do Challange")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build(),
                                Tarefa.builder().titulo("Ler sobre SQL")
                                                .descricao("Focar sobre como é o funcionamento do PL/SQL")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build(),
                                Tarefa.builder().titulo("Estudar sobre ReactNative")
                                                .descricao("Ver mais sobre os compomentes principais de React")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build(),
                                Tarefa.builder().titulo("Ver sobre o RabbitMQ")
                                                .descricao("ver sobre como funciona as consumer")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build(),
                                Tarefa.builder().titulo("Ler sobre Xadrez")
                                                .descricao("Ler 25 paginas sobre o livro da arte das duas torrer")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build(),
                                Tarefa.builder().titulo("Ler sobre Xadrez")
                                                .descricao("Ler 25 paginas sobre o livro da arte das duas torrer")
                                                .data(LocalDate.of(2023, 6, 6)).duracao(LocalTime.of(0, 15, 0))
                                                .idUsuario(1l).build()));
        }

}
