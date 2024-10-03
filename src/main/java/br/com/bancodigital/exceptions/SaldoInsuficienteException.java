package br.com.bancodigital.exceptions;

public class SaldoInsuficienteException extends Exception {
  public SaldoInsuficienteException(String message) {
    super(message);
  }
}
