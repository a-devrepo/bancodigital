package br.com.bancodigital.domain.dao.interfaces;

import br.com.bancodigital.domain.model.Conta;
import java.util.List;
import java.util.Map;

public interface IContaDao {
  Map<String, List<Conta>> listar();

  void salvar(String identificador, Conta conta);

  Conta buscar(String identificador, Integer numero);
}
