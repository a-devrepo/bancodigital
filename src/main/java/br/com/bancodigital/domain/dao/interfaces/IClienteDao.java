package br.com.bancodigital.domain.dao.interfaces;

import br.com.bancodigital.domain.model.Cliente;
import br.com.bancodigital.domain.model.Conta;
import java.util.List;
import java.util.Map;

public interface IClienteDao {
  Map<String, Cliente> listar();

  void salvar(Cliente cliente);

  Cliente buscar(String identificador);
}
