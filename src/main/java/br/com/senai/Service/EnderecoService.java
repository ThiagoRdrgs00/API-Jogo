package br.com.senai.Service;

import br.com.senai.Entity.Endereco;
import br.com.senai.Exception.EntityException;
import br.com.senai.Repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired

    EnderecoRepository enderecoRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco findById(Long id) {
        return enderecoRepository.findById(id).orElseThrow(
                () -> new EntityException("ID não encontrado")
        );
    }

    public Endereco findByCep(String cep) {
        return enderecoRepository.findByCep(cep);
    }

    public List<Endereco> findByCidade(String cidade) {
        return enderecoRepository.findByCidade(cidade);
    }

    public List<Endereco> findByUf(String uf) {
        return enderecoRepository.findByUf(uf);
    }

    public Endereco cadastrarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void removerEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

    public Endereco editarEndereco(Long id, Endereco endereco) {
        Endereco enderecoOld = enderecoRepository.findById(id).orElseThrow(
                () -> new EntityException("Endereco não encontrado")
        );
        enderecoOld.setUf(endereco.getUf());
        enderecoOld.setCep(endereco.getCep());
        enderecoOld.setBairro(endereco.getBairro());
        enderecoOld.setCidade(endereco.getCidade());
        enderecoOld.setRua(endereco.getRua());
        enderecoOld.setEstado(endereco.getEstado());

        return enderecoRepository.save(enderecoOld);
    }

}
