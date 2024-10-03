package br.com.bancodigital.domain.dao.impl;

import br.com.bancodigital.domain.dao.interfaces.IClienteDao;
import br.com.bancodigital.domain.model.Cliente;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImplClienteDao implements IClienteDao {

  private final Map<String, Cliente> map;

  public ImplClienteDao() {
    this.map = new HashMap<>();
  }

  @Override
  public Map<String, Cliente> listar() {
    return Collections.unmodifiableMap(map);
  }

  @Override
  public void salvar(Cliente cliente) {
    map.put(cliente.getIdentificador(), cliente);
  }

  @Override
  public Cliente buscar(String identificador) {
    return map.get(identificador);
  }
}
