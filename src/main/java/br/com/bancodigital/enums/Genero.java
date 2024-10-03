package br.com.bancodigital.enums;

import br.com.bancodigital.exceptions.TipoNaoEncontradoException;

import java.util.Objects;

public enum Genero {
    MASCULINO("M"),
    FEMININO("F"),
    OUTROS("O");

    private String genero;

    private Genero(String genero){
        this.genero = genero;
    }

    public static Genero getByName(String name) throws TipoNaoEncontradoException {
        Genero genero = null;
        for(Genero value: Genero.values()){
            if(value.genero.equalsIgnoreCase(name)){
                genero = value;
            }
        }
        if(Objects.isNull(genero)){
            throw new TipoNaoEncontradoException("Gênero não encontrado");
        }
        return genero;
    }
}
