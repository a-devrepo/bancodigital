package br.com.bancodigital.services.interfaces;

import br.com.bancodigital.domain.model.Cliente;
import br.com.bancodigital.exceptions.RegistroNaoEncontradoException;
import java.util.Map;

public interface IClienteService {
  Map<String, Cliente> listar();

  void salvar(Cliente cliente);

  Cliente buscar(String identificador) throws RegistroNaoEncontradoException;
}
