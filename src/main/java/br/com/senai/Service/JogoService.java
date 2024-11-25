package br.com.senai.Service;

import br.com.senai.Entity.Jogo;
import br.com.senai.Exception.EntityException;
import br.com.senai.Repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JogoService {
    @Autowired
    private JogoRepository jogoRepository;

    //Cadastrar
    public Jogo cadastrarJogo(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    //Excluir
    public void excluirJogo(Long idJogo) {
        jogoRepository.deleteById(idJogo);
    }

    //Editar
    public Jogo editarJogo(Long id, Jogo jogo) {
        Jogo jogoOld = jogoRepository.findById(id).orElseThrow(
                () -> new EntityException("Jogo não encontrado!")
        );
        jogoOld.setNome(jogo.getNome());
        jogoOld.setGenero(jogo.getGenero());
        jogoOld.setDesenvolvedora(jogo.getDesenvolvedora());
        jogoOld.setData_publicacao(jogo.getData_publicacao());

        return jogoRepository.save(jogoOld);
    }

    //Listar
    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    public Jogo buscarJogoPorID(Long idJogo) {
        Jogo jogo = jogoRepository.findById(idJogo).orElseThrow(
                () -> new EntityException("Jogo não encontrado na Base de Dados (id: " + idJogo + ")!")
        );

        return jogo;
    }

    public List<Jogo> buscarJogoPorNome(String nomeJogo) {
        List<Jogo> jogos = new ArrayList<>();
        try {
            jogos = jogoRepository.buscarJogoPorNome(nomeJogo);

            System.out.println();
        } catch(EntityException entidade) {
            System.out.println(entidade.getMessage());
        }

        return jogos;
    }

    public List<Jogo> buscarJogoPorGenero(String generoPessoa) {
        List<Jogo> jogos = new ArrayList<>();
        try {
            jogos = jogoRepository.buscarJogoPorGenero(generoPessoa);

            System.out.println();
        } catch(EntityException entidade) {
            System.out.println(entidade.getMessage());
        }

        return jogos;
    }
}
