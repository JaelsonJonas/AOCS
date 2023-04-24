package br.com.fiap.aocs.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.aocs.DTO.TarefaDTO;
import br.com.fiap.aocs.DTO.UsuarioDTO;
import br.com.fiap.aocs.DTO.ValidaUsuarioDTO;
import br.com.fiap.aocs.exceptions.RestNotFoundException;
import br.com.fiap.aocs.models.ReturnAPI;
import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.TarefaRepository;
import br.com.fiap.aocs.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TarefaRepository tRepository;

    @GetMapping("api/usuario")
    public List<UsuarioDTO> getAllUsers() {

        return repository.findAll().stream().map(n -> new UsuarioDTO(n.getLogin(), getTarefas(n.getId())))
                .toList();
    }

    @GetMapping("api/usuario/{id}")
    public EntityModel<UsuarioDTO> returnWithId(@PathVariable Long id) {

        Usuario login = getUsuario(id);

        List<TarefaDTO> tarefas = getTarefas(id);

        UsuarioDTO dto = new UsuarioDTO(login.getLogin(), tarefas);

        return dto.toEntityModel(login, dto);
    }

    @PostMapping("api/register")
    public ResponseEntity<ReturnAPI> register(@RequestBody @Valid ValidaUsuarioDTO validaUsuarioDTO,
            UriComponentsBuilder uriCompBuilder) {

        // validar se o login ja existe, se sim retornar que não é possivel gerar esse
        // login
        ExampleMatcher em = ExampleMatcher.matching().withMatcher("DS_LOGIN",
                usuario -> usuario.exact());
        Example<Usuario> criterioDeBusca = Example.of(new Usuario(validaUsuarioDTO.login().toLowerCase()), em);

        Optional<Usuario> optUsr = repository.findOne(criterioDeBusca);

        if (optUsr.isPresent()) {
            return ResponseEntity.badRequest()
                    .body(new ReturnAPI("Desculpe, login já em uso. Por favor, tente outro."));
        }

        Usuario newUser = new Usuario(validaUsuarioDTO);

        repository.save(newUser);

        URI uri = uriCompBuilder.path("api/usuario/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(uri).body(new ReturnAPI("Usuario cadastrado com sucesso!!"));

    }

    @PostMapping("api/login")
    public ResponseEntity<ReturnAPI> postMethodName(@RequestBody Usuario u) {

        Optional<Usuario> usuarioConteiner = repository.findByLoginAndSenha(u.getLogin(), u.getSenha());

        if (usuarioConteiner.isPresent()) {
            return ResponseEntity.ok().body(new ReturnAPI("Login realizado com sucesso!!"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReturnAPI("Login e senha invalido"));

    }

    private Usuario getUsuario(Long id) {

        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Usuario nao encontrado"));

    }

    private List<TarefaDTO> getTarefas(Long id) {
        return tRepository.findByIdUsuario(id).stream().map(TarefaDTO::new).toList();

    }

}
