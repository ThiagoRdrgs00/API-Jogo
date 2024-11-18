package br.com.senai.Controller;

import br.com.senai.Entity.Pessoa;
import br.com.senai.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) {
         return pessoaService.cadastrarPessoa(pessoa);
    }

    @GetMapping("/listar")
    public List<Pessoa> listarPessoas() {
        return pessoaService.listarPessoas();
    }

    @GetMapping("/buscarId/{idPessoa}")
    public Pessoa buscarPessoaPorId(@PathVariable Long idPessoa) {
        return pessoaService.buscarPessoaPorID(idPessoa);
    }

    @GetMapping("/buscarNome/{nomePessoa}")
    public List<Pessoa> buscarPessoaPorNome(@PathVariable String nomePessoa) {
        return pessoaService.buscarPessoaPorNome(nomePessoa);
    }

    @DeleteMapping("/excluirId/{idPessoa}")
    public void excluirPessoa(@PathVariable Long idPessoa) {
        pessoaService.excluirPessoa(idPessoa);
    }
}
