package br.com.fiap.universidade_fiap.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.fiap.universidade_fiap.dto.ViaCepResponse;
import br.com.fiap.universidade_fiap.model.Endereco;

@Service
public class ViaCep {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://viacep.com.br/ws/{cep}/json/";

    public Endereco buscarEnderecoPorCep(String cep) {
        try {
            ViaCepResponse response = restTemplate.getForObject(URL, ViaCepResponse.class, cep);
            if (response != null) {
                Endereco endereco = new Endereco();
                endereco.setCep(response.getCep());
                endereco.setLogradouro(response.getLogradouro());
                endereco.setComplemento(response.getComplemento());
                endereco.setBairro(response.getBairro());
                endereco.setLocalidade(response.getLocalidade());
                endereco.setUf(response.getUf());
                endereco.setEstado(response.getUf());
                endereco.setRegiao("Sudeste");
                return endereco;
            }
        } catch (Exception e) {
            System.out.println("DEBUG - Erro ao consultar ViaCep: " + e.getMessage());
        }
        return null;
    }
}
