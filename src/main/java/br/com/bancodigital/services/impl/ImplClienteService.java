package br.com.bancodigital.services.impl;

import br.com.bancodigital.domain.dao.interfaces.IClienteDao;
import br.com.bancodigital.domain.model.Cliente;
import br.com.bancodigital.exceptions.RegistroNaoEncontradoException;
import br.com.bancodigital.services.interfaces.IClienteService;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImplClienteService implements IClienteService {

  private final IClienteDao dao;

  @Override
  public Map<String, Cliente> listar() {
    return dao.listar();
  }

  @Override
  public void salvar(Cliente cliente) throws IllegalArgumentException {
    if (cliente == null) {
      throw new IllegalArgumentException("É obrigatório informar o cliente a ser cadastrado");
    }
    dao.salvar(cliente);
  }

  @Override
  public Cliente buscar(String identificador) throws RegistroNaoEncontradoException {
    if (identificador == null) {
      throw new IllegalArgumentException("É obrigatório informar o identificaor do Cliente");
    }
    Cliente cliente = dao.buscar(identificador);
    if (cliente == null) {
      throw new RegistroNaoEncontradoException("Cliente não encontrado");
    }
    return cliente;
  }
}
