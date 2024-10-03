package br.com.bancodigital.enums;

import br.com.bancodigital.exceptions.TipoNaoEncontradoException;
import java.util.Objects;

public enum OpcoesMenu {
  CADASTRO_CLIENTE("1"),
  CADASTRO_CONTA("2"),
  DEPOSITO("3"),
  SAQUE("4"),
  TRANSFERENCIA("5"),
  EXTRATO("6"),
  SAIR("7");

  private String valor;

  OpcoesMenu(String valor) {
    this.valor = valor;
  }

  public static OpcoesMenu getByName(String name) throws TipoNaoEncontradoException {
    OpcoesMenu opcao = null;
    for (OpcoesMenu value : OpcoesMenu.values()) {
      if (value.valor.equalsIgnoreCase(name)) {
        opcao = value;
      }
    }
    if (Objects.isNull(opcao)) {
      throw new TipoNaoEncontradoException("Opção não encontrada");
    }
    return opcao;
  }
}
