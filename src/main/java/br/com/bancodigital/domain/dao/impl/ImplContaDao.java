package br.com.bancodigital.domain.dao.impl;

import br.com.bancodigital.domain.dao.interfaces.IContaDao;
import br.com.bancodigital.domain.model.Conta;
import java.util.*;

public class ImplContaDao implements IContaDao {
  private Map<String, List<Conta>> map;

  public ImplContaDao() {
    this.map = new HashMap<>();
  }

  @Override
  public Map<String, List<Conta>> listar() {
    return Collections.unmodifiableMap(map);
  }

  @Override
  public void salvar(String identificador, Conta conta) {
    if (!map.containsKey(identificador)) {
      List<Conta> contas = new ArrayList<>();
      contas.add(conta);
      map.put(identificador, contas);
    } else {
      List<Conta> contas = map.get(identificador);
      contas.add(conta);
    }
  }

  @Override
  public Conta buscar(String identificador, Integer numero) {
    Conta conta = null;
    if (map.containsKey(identificador)) {
      List<Conta> contas = map.get(identificador);
      conta = contas.stream().filter(c -> c.getNumero() == numero).findFirst().orElse(null);
    }
    return conta;
  }
}
