package com.estudos.clientes.controller;

import com.estudos.clientes.model.entities.Cliente;
import com.estudos.clientes.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes")
//@ResponseBody
public class ClienteController {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody Cliente cliente ){
        return this.clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    public Cliente getById(@PathVariable Integer id){
        return this.clienteRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id){
         this.clienteRepository
                .findById(id)
                .map(cliente-> {
                    clienteRepository.delete(cliente);
                    return  Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarCliente(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado){
        clienteRepository.findById(id).map(cliente ->{
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setCpf(clienteAtualizado.getCpf());
            return this.clienteRepository.save(cliente);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
