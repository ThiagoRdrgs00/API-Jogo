package br.com.senai.Service;

import br.com.senai.Entity.Endereco;
import br.com.senai.Exception.EntityException;
import br.com.senai.Repository.EnderecoRepository;
import br.com.senai.dto.EnderecoViaCepDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public EnderecoViaCepDTO buscarEnderecoViaCep(String cep) {
        final String apiSrc = "http://viacep.com.br/ws/";
        final int statusOK = 200;

        try {
            URL url = new URL(apiSrc + cep + "/json");
            //URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != statusOK) {
                throw new RuntimeException("HTTP connection error! ("+ conexao.getResponseCode() +")");
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            String response , jsonViaCep = "";
            while ((response = bufferedReader.readLine()) != null) {
                jsonViaCep += response;
            }

            Gson gson = new Gson();
            EnderecoViaCepDTO endereco = gson.fromJson(jsonViaCep, EnderecoViaCepDTO.class);

            return endereco;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
