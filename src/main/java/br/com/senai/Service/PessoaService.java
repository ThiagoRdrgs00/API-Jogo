package br.com.senai.Service;

import br.com.senai.Entity.Pessoa;
import br.com.senai.Exception.EntityException;
import br.com.senai.Repository.PessoaRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPessoaPorID(Long idPessoa) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(
                () -> new EntityException("Cadastro não encontrado na Base de Dados (id: " + idPessoa + ")!")
        );
        return pessoa;
    }

    public List<Pessoa> buscarPessoaPorNome(String nomePessoa) {
        List<Pessoa> pessoas = new ArrayList<>();
        try {
            pessoas = pessoaRepository.buscarPessoaPorNome(nomePessoa);

            System.out.println();
        } catch(EntityException entidade) {
            System.out.println(entidade.getMessage());
        }
        return pessoas;
    }

    public void excluirPessoa(Long idPessoa) {
        pessoaRepository.deleteById(idPessoa);
    }
}