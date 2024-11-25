package br.com.senai.Repository;

import br.com.senai.Entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    @Query("SELECT j FROM Jogo j WHERE j.nome ILIKE %?1%")
    List<Jogo> buscarJogoPorNome(String nomeJogo);

    @Query("SELECT j FROM Jogo j WHERE lower(j.genero) = lower(?1)")
    List<Jogo> buscarJogoPorGenero(String generoJogo);
}
