package br.com.alura.TabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collections;
import java.util.List;

public class ConverteDados implements  IConverteDados {
  ObjectMapper mapper = new ObjectMapper();
  ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

  @Override
  public <T> T obterDados(String json, Class<T> classe) {
    try {
      // Converte a string JSON em um objeto Java
      return mapper.readValue(json, classe);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public <T> List<T> obterLista(String json, Class<T> classe) {
    CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, classe);
    try {
      return mapper.readValue(json, lista);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  public String formattedJsonIntoString (Object object) {
    try {
      return writer.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
