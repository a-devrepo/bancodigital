package br.com.bancodigital.domain.model.interfaces;

import br.com.bancodigital.domain.model.Conta;
import br.com.bancodigital.exceptions.SaldoInsuficienteException;
import br.com.bancodigital.exceptions.ValorInsuficienteException;

public interface IConta {
    void sacar(double valor) throws SaldoInsuficienteException;
    void depositar(double valor) throws ValorInsuficienteException;
    void transferir(double valor, Conta contaDestino) throws SaldoInsuficienteException, ValorInsuficienteException;
    void imprimirExtrato();
}
