package br.com.bancodigital.services.interfaces;

import br.com.bancodigital.domain.model.Conta;
import br.com.bancodigital.exceptions.RegistroNaoEncontradoException;
import java.util.List;
import java.util.Map;

public interface IContaService {
  Map<String, List<Conta>> listar();

  void salvar(String identificador, Conta conta) throws IllegalArgumentException;

  Conta buscar(String identificador, Integer numero) throws RegistroNaoEncontradoException;
}
