package br.com.senai.Controller;

import br.com.senai.Entity.Jogo;
import br.com.senai.Service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/jogo")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    //Cadastrar
    @PostMapping("/cadastrar")
    public Jogo cadastrarJogo(@RequestBody Jogo jogo) {
        return jogoService.cadastrarJogo(jogo);
    }

    //Excluir
    @DeleteMapping("/excluir/{idJogo}")
    public void excluirJogo(@PathVariable Long idJogo) {
        jogoService.excluirJogo(idJogo);
    }

    //Editar

    @PutMapping("/editar/{idJogo}")
    public Jogo editarJogo(@PathVariable Long idJogo, @RequestBody Jogo jogo) {
        return jogoService.editarJogo(idJogo, jogo);
    }

    //Listar
    @GetMapping("/listar")
    public List<Jogo> listarJogos() {
        return jogoService.listarJogos();
    }

    @GetMapping("/buscarId/{idJogo}")
    public Jogo buscarJogoPorID(@PathVariable Long idJogo) {
        return jogoService.buscarJogoPorID(idJogo);
    }

    @GetMapping("/buscarNome/{nomeJogo}")
    public List<Jogo> buscarJogoPorNome(@PathVariable String nomeJogo) {
        return jogoService.buscarJogoPorNome(nomeJogo);
    }

    @GetMapping("/buscarGenero/{generoJogo}")
    public List<Jogo> buscarPessoaPorNome(@PathVariable String generoJogo) {
        return jogoService.buscarJogoPorGenero(generoJogo);
    }
}
