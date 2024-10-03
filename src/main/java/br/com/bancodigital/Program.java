package br.com.bancodigital;

import br.com.bancodigital.enums.OpcoesMenu;
import br.com.bancodigital.exceptions.RegistroNaoEncontradoException;
import br.com.bancodigital.exceptions.SaldoInsuficienteException;
import br.com.bancodigital.exceptions.TipoNaoEncontradoException;
import br.com.bancodigital.exceptions.ValorInsuficienteException;
import br.com.bancodigital.view.MenuView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Program {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public static void main(String[] args) {
    OpcoesMenu opcao = OpcoesMenu.CADASTRO_CLIENTE;

    while (opcao != OpcoesMenu.SAIR) {

      try {
        MenuView.mostrarMenuPrincipal();
        opcao = OpcoesMenu.getByName(reader.readLine());
        switch (opcao) {
          case CADASTRO_CLIENTE -> MenuView.cadastrarCliente();
          case CADASTRO_CONTA -> MenuView.cadastrarConta();
          case EXTRATO -> MenuView.imprimirExtrato();
          case DEPOSITO -> MenuView.efetuarDeposito();
          case SAQUE -> MenuView.efetuarSaque();
          case TRANSFERENCIA -> MenuView.efetuarTransferencia();
          case SAIR -> sair();
        }
      } catch (IOException
          | TipoNaoEncontradoException
          | RegistroNaoEncontradoException
          | ValorInsuficienteException
          | SaldoInsuficienteException e) {
        System.out.println("Erro: " + e.getMessage());
      }
    }
  }

  private static void sair() {
    System.out.println("Saindo...");
    System.exit(0);
  }
}
