package br.com.senai.Repository;

import br.com.senai.Entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.cep = ?1")
    Endereco findByCep(String cep);

    @Query("SELECT e FROM Endereco e WHERE e.cidade = ?1")
    List<Endereco> findByCidade(String cidade);

    @Query("SELECT e FROM Endereco e WHERE e.uf = ?1")
    List<Endereco> findByUf(String uf);
}
