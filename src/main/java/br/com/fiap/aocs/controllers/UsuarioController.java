package br.com.fiap.aocs.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.aocs.models.Retorno;
import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.UsuarioRepository;

@RestController
public class UsuarioController {

    // List<Usuario> usuarios = new ArrayList<Usuario>();
    @Autowired
    private UsuarioRepository repository;

    @GetMapping("api/usuarios")
    public List<Usuario> getAllUsers() {

        return repository.findAll();
    }

    @GetMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> returnWithId(@PathVariable Integer id) {

        Optional<Usuario> usuarioContainer = repository.findById(id);

        if (usuarioContainer.isPresent()) {

            return ResponseEntity.ok().body(usuarioContainer.get());

        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("api/register")
    public ResponseEntity<Retorno> register(@RequestBody Usuario newUser, UriComponentsBuilder uriCompBuilder) {

        // validar se o login ja existe, se sim retornar que não é possivel gerar esse
        // login
        ExampleMatcher em = ExampleMatcher.matching().
        
                withMatcher("DS_LOGIN",
                ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Usuario> criterioDeBusca = Example.of(newUser, em);

        Optional<Usuario> optUsr = repository.findOne(criterioDeBusca);

        if (optUsr.isPresent()) {
            return ResponseEntity.badRequest().body(new Retorno("Desculpe, login já em uso. Por favor, tente outro."));
        }

        repository.save(newUser);

        URI uri = uriCompBuilder.path("api/usuario/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(uri).body(new Retorno("Usuario criado com sucesso!"));

        // repository.save(newUser);

        // return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    @PostMapping("api/login")
    public ResponseEntity<Usuario> postMethodName(@RequestBody Usuario u) {

        Optional<Usuario> usuarioConteiner = repository.findByLoginAndSenha(u.getLogin(), u.getSenha());

        if (usuarioConteiner.isPresent()) {
            return ResponseEntity.ok().body(usuarioConteiner.get());
        }

        return ResponseEntity.notFound().build();

    }

}
