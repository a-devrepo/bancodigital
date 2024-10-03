package br.com.bancodigital.services.impl;

import br.com.bancodigital.domain.model.Conta;
import br.com.bancodigital.domain.dao.interfaces.IContaDao;
import br.com.bancodigital.exceptions.RegistroNaoEncontradoException;
import br.com.bancodigital.services.interfaces.IContaService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImplContaService implements IContaService {

  private final IContaDao dao;

  @Override
  public Map<String, List<Conta>> listar() {
    return dao.listar();
  }

  @Override
  public void salvar(String identificador, Conta conta) throws IllegalArgumentException {
    if(identificador == null){
      throw new IllegalArgumentException("É obrigatório informar o identificador do Cliente");
    }
    if(conta == null){
      throw new IllegalArgumentException("É obrigatório informar a Conta");
    }
    dao.salvar(identificador, conta);
  }

  @Override
  public Conta buscar(String identificador, Integer numero) throws RegistroNaoEncontradoException {
    Conta conta = dao.buscar(identificador, numero);
    if(conta == null){
      throw new RegistroNaoEncontradoException("Conta inexistente");
    }
    return conta;
  }

  public Conta getContaByNumero(Integer numero) throws RegistroNaoEncontradoException {
    Conta contaDestino = listar()
            .values()
            .stream()
            .flatMap(Collection::stream)
            .filter(conta -> conta.getNumero() == numero)
            .findFirst().orElseThrow(() -> new RegistroNaoEncontradoException("Conta de destino não encontrada"));
    return contaDestino;
  }
}
