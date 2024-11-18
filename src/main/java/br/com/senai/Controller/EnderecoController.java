package br.com.senai.Controller;

import br.com.senai.Entity.Endereco;
import br.com.senai.Service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> findAll() {
        return enderecoService.findAll();
    }

    @GetMapping("/{id}")
    public Endereco findById(@PathVariable Long id) {
        return enderecoService.findById(id);
    }

    @GetMapping("/buscarCep/{cep}")
    public Endereco findByCep(@PathVariable String cep) {
        return enderecoService.findByCep(cep);
    }

    @GetMapping("/buscarCidade/{cidade}")
    public List<Endereco> findByCidade(@PathVariable String cidade) {
        return enderecoService.findByCidade(cidade);
    }

    @GetMapping("/buscarUf/{uf}")
    public List<Endereco> findByUf(@PathVariable String uf) {
        return enderecoService.findByUf(uf);
    }

    @PostMapping
    public Endereco cadastrarEndereco(@RequestBody Endereco endereco) {
        return enderecoService.cadastrarEndereco(endereco);
    }

    @DeleteMapping("removerEndereco/{id}")
    public void removerEndereco(@PathVariable Long id) {
        enderecoService.removerEndereco(id);
    }

    @PutMapping("editarEndereco/{id}")
    public Endereco editarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        return enderecoService.editarEndereco(id, endereco);
    }


}
