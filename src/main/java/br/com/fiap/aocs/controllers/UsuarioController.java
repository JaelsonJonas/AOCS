package br.com.fiap.aocs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.aocs.models.Usuario;
import br.com.fiap.aocs.repository.UsuarioRepository;

@RestController
public class UsuarioController {

    // List<Usuario> usuarios = new ArrayList<Usuario>();
    @Autowired
    private UsuarioRepository repository;


    @GetMapping("api/usuarios")
    public List<Usuario> getAllUsers(){

        return repository.findAll();
    }

    @GetMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> returnWithId(@PathVariable Integer id) {

           Optional<Usuario> usuarioContainer = repository.findById(id);

           if (usuarioContainer.isPresent()){
            
            return ResponseEntity.ok().body(usuarioContainer.get());

           }

           return ResponseEntity.notFound().build();

    }

    @PostMapping("api/register")
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario newUser) {

        //validar se o login ja existe, se sim retornar que não é possivel gerar esse login
    
        repository.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

    @PostMapping("api/login")
    public ResponseEntity<Usuario> postMethodName(@RequestBody Usuario u) {
      
      
        Optional<Usuario> usuarioConteiner = repository.findByLoginAndSenha(u.getLogin(), u.getSenha());

        if(usuarioConteiner.isPresent()){
            return ResponseEntity.ok().body(usuarioConteiner.get());
        }

        return ResponseEntity.notFound().build();

    }

}
