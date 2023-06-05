package br.com.fiap.aocs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import br.com.fiap.aocs.DTO.TokenJWT;
import br.com.fiap.aocs.DTO.UsuarioDTO;
import br.com.fiap.aocs.DTO.UsuarioLogin;
import br.com.fiap.aocs.DTO.UsuarioRegister;
import br.com.fiap.aocs.exceptions.RestNotFoundException;
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
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenService tokenService;

    @GetMapping("api/usuario")
    public Page<UsuarioDTO> getAllUsers(Pageable pageable) {

        return repository.findAll(pageable).map(n -> new UsuarioDTO(n.getLogin(), getTarefas(n.getId())));
    }

    @GetMapping("api/usuario/{id}")
    public EntityModel<UsuarioDTO> returnWithId(@PathVariable Long id) {

        Usuario login = getUsuario(id);

        List<TarefaDTO> tarefas = getTarefas(id);

        UsuarioDTO dto = new UsuarioDTO(login.getLogin(), tarefas);

        return dto.toEntityModel(login, dto);
    }

    @PostMapping("api/register")
    public ResponseEntity<Object> register(@RequestBody @Valid UsuarioRegister post) {

        Usuario newUser = new Usuario(post);

        newUser.setSenha(encoder.encode(post.senha()));

        repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    @PostMapping("api/login")
    public ResponseEntity<TokenJWT> login(@RequestBody @Valid UsuarioLogin dados) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),
                dados.senha());

        Authentication autentication = manager.authenticate(authenticationToken);

        String tokenJWT = tokenService.gerarToken((Usuario) autentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));

    }

    private Usuario getUsuario(Long id) {

        return repository.findById(id).orElseThrow(() -> new RestNotFoundException("Usuario nao encontrado"));

    }

    private List<TarefaDTO> getTarefas(Long id) {
        return tRepository.findByIdUsuario(id).stream().map(TarefaDTO::new).toList();

    }

}
