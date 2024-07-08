package br.com.alura.TabelaFipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {
  private ConverteDados converterDados = new ConverteDados();

  public String obterDados(String endereco) {
    HttpClient client = HttpClient.newHttpClient(); //Cria uma conexão HTTP
    HttpRequest request = HttpRequest.newBuilder() //Abre uma nova requisição
            .uri(URI.create(endereco)) //Com o endereço de URL especificado
            .build();
    HttpResponse<String> response = null; //Prepara o objeto para armazenar a resposta
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString()); //Efetua a requisição
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    String jsonResponse = response.body(); //Obtem o corpo da resposta e armazena a string do json
    Object jsonObject = converterDados.obterDados(jsonResponse, Object.class);
    String json = converterDados.formattedJsonIntoString(jsonObject);
    return json;
  }
}
