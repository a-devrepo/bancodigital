package br.com.bancodigital.view;

import br.com.bancodigital.domain.dao.impl.ImplClienteDao;
import br.com.bancodigital.domain.dao.impl.ImplContaDao;
import br.com.bancodigital.domain.dao.interfaces.IClienteDao;
import br.com.bancodigital.domain.dao.interfaces.IContaDao;
import br.com.bancodigital.domain.model.*;
import br.com.bancodigital.enums.Genero;
import br.com.bancodigital.enums.TipoCliente;
import br.com.bancodigital.exceptions.RegistroNaoEncontradoException;
import br.com.bancodigital.exceptions.SaldoInsuficienteException;
import br.com.bancodigital.exceptions.TipoNaoEncontradoException;
import br.com.bancodigital.exceptions.ValorInsuficienteException;
import br.com.bancodigital.services.impl.ImplClienteService;
import br.com.bancodigital.services.impl.ImplContaService;
import br.com.bancodigital.services.interfaces.IClienteService;
import br.com.bancodigital.services.interfaces.IContaService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuView {
  static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  static IClienteDao clienteDao = new ImplClienteDao();
  static IClienteService clienteService = new ImplClienteService(clienteDao);
  static IContaDao contaDao = new ImplContaDao();
  static IContaService contaService = new ImplContaService(contaDao);


  public static void mostrarMenuPrincipal(){
    System.out.println("=============BANCO DIGITAL============");
    System.out.println("Escolha uma das seguintes opções: ");
    System.out.println("1 - Cadastrar Cliente");
    System.out.println("2 - Cadastrar Conta");
    System.out.println("3 - Efetuar Depósito");
    System.out.println("4 - Efetuar Saque");
    System.out.println("5 - Efetuar Transferência");
    System.out.println("6 - Imprimir Extrato");
    System.out.println("7 - Sair");
  }

  public static void cadastrarCliente() throws IOException, TipoNaoEncontradoException {
    System.out.println("========Cadastro de Cliente=======");

    System.out.print("Nome: ");
    String nome = reader.readLine();
    System.out.print("Sobrenome: ");
    String sobrenome = reader.readLine();
    System.out.print("Gênero Masculino-M/Feminino-F/Outros-O: ");
    Genero genero = Genero.getByName(reader.readLine());
    System.out.print("Pessoa Fisica-F/Juridica-J: ");
    TipoCliente tipoCliente = TipoCliente.getByName(reader.readLine());
    System.out.print("Identificador CPF/CNPJ: ");
    String identificador = reader.readLine();

    System.out.println("=============Endereço============ ");
    System.out.print("Logradouro: ");
    String logradouro = reader.readLine();
    System.out.print("Número: ");
    String numero = reader.readLine();
    System.out.print("Complemento: ");
    String complemento = reader.readLine();
    System.out.print("Cidade: ");
    String cidade = reader.readLine();
    System.out.print("UF: ");
    String uf = reader.readLine();
    System.out.print("CEP: ");
    String cep = reader.readLine();

    Endereco endereco =
        Endereco.builder()
            .logradouro(logradouro)
            .numero(numero)
            .complemento(complemento)
            .cidade(cidade)
            .UF(uf)
            .cep(cep)
            .build();

    Cliente cliente = new Cliente(nome, sobrenome, genero, tipoCliente, identificador, endereco);
    clienteService.salvar(cliente);
    System.out.println("Cliente cadastrado com sucesso.\n");
  }

  public static void cadastrarConta()
      throws IOException, RegistroNaoEncontradoException, IllegalArgumentException {
    System.out.println("========Cadastro de Conta=========");
    System.out.print("Informe o identificador do Cliente: ");
    String identificador = reader.readLine();

    Cliente cliente = clienteService.buscar(identificador);
    System.out.println(cliente.getInformacoes());

    Conta conta = null;

    System.out.print("Tipo de Conta Corrente-1/Poupança-2: ");
    String tipo = reader.readLine();

    if (tipo.equalsIgnoreCase("1")) {
      conta = new ContaCorrente();
    } else if (tipo.equalsIgnoreCase("2")) {
      conta = new ContaPoupanca();
    }
    cliente.adicionarConta(conta);
    contaService.salvar(identificador, conta);

    System.out.println("Conta criada com sucesso");
    System.out.println("Agência: " + conta.getAgencia());
    System.out.println("Número: " + conta.getNumero());
  }

  public static void imprimirExtrato()
      throws IOException, RegistroNaoEncontradoException, IllegalArgumentException {
    System.out.println("============Extrato===============");
    System.out.print("Informe o identificador do Cliente: ");
    String identificador = reader.readLine();

    System.out.print("Informe o número da conta: ");
    Integer numeroConta = Integer.valueOf(reader.readLine());

    Conta conta = contaService.buscar(identificador, numeroConta);

    conta.imprimirExtrato();
  }

  public static void efetuarDeposito()
      throws IOException,
          RegistroNaoEncontradoException,
          IllegalArgumentException,
          ValorInsuficienteException {
    System.out.println("==============Depósito============");
    System.out.print("Informe o identificador do Cliente: ");
    String identificador = reader.readLine();

    System.out.print("Informe o número da conta: ");
    Integer numeroConta = Integer.parseInt(reader.readLine());

    Conta conta = contaService.buscar(identificador, numeroConta);

    System.out.print("Informe o valor do depósito: ");
    double valor = Double.parseDouble(reader.readLine());

    conta.depositar(valor);
    System.out.println("Depósito efetuado com sucesso.\n");
  }

  public static void efetuarSaque()
      throws IOException,
          RegistroNaoEncontradoException,
          IllegalArgumentException,
          SaldoInsuficienteException {
    System.out.println("===============Saque================");
    System.out.print("Informe o identificador do Cliente: ");
    String identificador = reader.readLine();

    System.out.print("Informe o número da conta: ");
    Integer numeroConta = Integer.valueOf(reader.readLine());

    Conta conta = contaService.buscar(identificador, numeroConta);

    System.out.print("Informe o valor do saque: ");
    double valor = Double.parseDouble(reader.readLine());

    conta.sacar(valor);
    System.out.println("Saque efetuado com sucesso.");
  }

  public static void efetuarTransferencia()
      throws IOException,
          RegistroNaoEncontradoException,
          IllegalArgumentException,
          SaldoInsuficienteException,
          ValorInsuficienteException {
    System.out.println("==========Transferência===========");
    System.out.print("Informe o identificador do Cliente: ");
    String identificador = reader.readLine();

    System.out.print("Informe o número da conta de origem: ");
    Integer numeroConta = Integer.valueOf(reader.readLine());

    Conta contaOrigem = contaService.buscar(identificador, numeroConta);

    System.out.print("Informe o número da conta de destino: ");
    Integer numeroContaDestino = Integer.valueOf(reader.readLine());

    ImplContaService service = (ImplContaService) contaService;
    Conta contaDestino = service.getContaByNumero(numeroContaDestino);

    System.out.print("Informe o valor: ");
    double valor = Double.parseDouble(reader.readLine());

    contaOrigem.transferir(valor, contaDestino);
    System.out.println("Transferência realizada com sucesso.\n");
  }
}
