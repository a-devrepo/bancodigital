package br.com.bancodigital.domain.model;

import br.com.bancodigital.enums.Genero;
import br.com.bancodigital.enums.TipoCliente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Cliente {

  @Getter private final String nome;
  @Getter private final String sobrenome;
  @Getter private final Genero genero;
  @Getter private final TipoCliente tipoCliente;
  @Getter private final String identificador;
  @Getter private final Endereco endereco;

  private List<Conta> contas;

  public void adicionarConta(Conta conta) throws IllegalArgumentException {
    if (conta == null) {
      throw new IllegalArgumentException("É obrigatório informar a Conta");
    }
    if (contas == null) {
      this.contas = new ArrayList<>();
    }
    contas.add(conta);
  }

  public String getInformacoes() {
    return "Nome: "
        + nome
        + " "
        + sobrenome
        + ", Identificador: "
        + identificador
        + ", Gênero: "
        + genero
        + ", Tipo: "
        + tipoCliente;
  }

  public List<Conta> getContas() {
    return Collections.unmodifiableList(contas);
  }
}
