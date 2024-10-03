package br.com.bancodigital.enums;

import br.com.bancodigital.exceptions.TipoNaoEncontradoException;

import java.util.Objects;

public enum TipoCliente {
    PESSOA_FISICA("F"),
    PESSOA_JURIDICA("J");

    private String tipo;

    TipoCliente(String tipo){
        this.tipo = tipo;
    }

    public static TipoCliente getByName(String name) throws TipoNaoEncontradoException {
        TipoCliente tipo = null;
        for(TipoCliente tipoCliente: TipoCliente.values()){
            if(tipoCliente.tipo.equalsIgnoreCase(name)){
                tipo = tipoCliente;
            }
        }
        if(Objects.isNull(tipo)){
            throw new TipoNaoEncontradoException("Tipo de Cliente não encontrado");
        }
        return tipo;
    }
}
