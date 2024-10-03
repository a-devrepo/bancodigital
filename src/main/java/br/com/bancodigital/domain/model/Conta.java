package br.com.bancodigital.domain.model;

import br.com.bancodigital.exceptions.SaldoInsuficienteException;
import br.com.bancodigital.exceptions.ValorInsuficienteException;
import br.com.bancodigital.domain.model.interfaces.IConta;
import lombok.Getter;

@Getter
public abstract class Conta implements IConta {

  private static final int AGENCIA_PADRAO = 1;
  private static int SEQUENCIAL = 1;

  protected int agencia;
  protected int numero;
  protected double saldo;

  public Conta() {
    this.agencia = Conta.AGENCIA_PADRAO;
    this.numero = SEQUENCIAL++;
  }

  @Override
  public void sacar(double valor) throws SaldoInsuficienteException {
    if (this.saldo <= 0.0) {
      throw new SaldoInsuficienteException("Saldo de conta insuficiente");
    }
    saldo -= valor;
  }

  @Override
  public void depositar(double valor) throws ValorInsuficienteException {
    if (valor <= 0.0) {
      throw new ValorInsuficienteException("O valor de depósito é insuficiente");
    }
    saldo += valor;
  }

  @Override
  public void transferir(double valor, Conta contaDestino)
      throws SaldoInsuficienteException, ValorInsuficienteException {
    this.sacar(valor);
    contaDestino.depositar(valor);
  }

  @Override
  public void imprimirExtrato() {
    System.out.println("==============Extrato================");
    System.out.println("Conta tipo: " + this.getClass().getSimpleName());
    System.out.println("Agência: " + this.getAgencia());
    System.out.println("Número: " + this.getNumero());
    System.out.println((String.format("Saldo: %.2f", this.getSaldo())));
  }
}
