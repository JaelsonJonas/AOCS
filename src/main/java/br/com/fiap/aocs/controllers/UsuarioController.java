package br.com.fiap.aocs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.aocs.DTO.TarefaDTO;
import br.com.fiap.aocs.DTO.UsuarioDTO;
import br.com.fiap.aocs.DTO.ValidaUsuarioDTO;
import br.com.fiap.aocs.exceptions.RestNotFoundException;
import br.com.fiap.aocs.models.Credencial;
import br.com.fiap.aocs.models.ReturnAPI;
import br.com.fiap.aocs.models.Token;
import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.TarefaRepository;
import br.com.fiap.aocs.repository.UsuarioRepository;
import br.com.fiap.aocs.service.TokenService;
import jakarta.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TarefaRepository tRepository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    TokenService tokenService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("api/usuario")
    public List<UsuarioDTO> getAllUsers() {

        return repository.findAll().stream()
                .map(n -> new UsuarioDTO(n.getNome(), n.getLogin(), n.getFoto(), getTarefas(n.getId())))
                .toList();
    }

    @GetMapping("api/usuario/{id}")
    public EntityModel<UsuarioDTO> returnWithId(@PathVariable Long id) {

        Usuario login = getUsuario(id);

        List<TarefaDTO> tarefas = getTarefas(id);

        UsuarioDTO dto = new UsuarioDTO(login.getNome(), login.getLogin(), login.getFoto(), tarefas);

        return dto.toEntityModel(login, dto);
    }

    @PostMapping("api/register")
    public ResponseEntity<Object> register(@RequestBody @Valid ValidaUsuarioDTO validaUsuarioDTO) {

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

        newUser.setSenha(encoder.encode(newUser.getSenha()));

        repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    @PostMapping("api/login")
    public ResponseEntity<Token> login(@RequestBody @Valid Credencial credencial) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                credencial.login(), credencial.senha());

        Authentication authenticate = manager.authenticate(usernamePasswordAuthenticationToken);

        Token generateToken = tokenService.generateToken((Usuario) authenticate.getPrincipal());

        return ResponseEntity.ok(generateToken);

    }

    private Usuario getUsuario(Long id) {

        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Usuario nao encontrado"));

    }

    private List<TarefaDTO> getTarefas(Long id) {
        return tRepository.findByIdUsuario(id).stream().map(TarefaDTO::new).toList();

    }

}
