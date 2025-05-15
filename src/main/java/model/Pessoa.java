package model;

import java.io.Serializable;
import java.util.Date;

public abstract class Pessoa implements Serializable {
    protected String nome;
    protected Date dataNascimento;

    public Pessoa(String nome, Date dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
}
