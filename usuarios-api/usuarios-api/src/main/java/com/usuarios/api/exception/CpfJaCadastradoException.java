package com.usuarios.api.exception;

public class CpfJaCadastradoException extends RuntimeException{

    public CpfJaCadastradoException(String mensagem){
        super(mensagem);
    }
}
