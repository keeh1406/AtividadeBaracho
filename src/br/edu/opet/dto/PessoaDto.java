package br.edu.opet.dto;

import java.util.List;

import br.edu.opet.model.Pessoa;

public class PessoaDto
{
    private boolean           ok;
    private String            mensagem;
    private Pessoa           Pessoa;
    private List<Pessoa>     lista;


    public PessoaDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public PessoaDto(boolean pOk, String pMensagem, Pessoa pPessoa)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        Pessoa = pPessoa;
    }

    public PessoaDto(boolean pOk, String pMensagem, List<Pessoa> pLista)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        lista = pLista;
    }

    public boolean isOk()
    {
        return ok;
    }

    public void setOk(boolean pOk)
    {
        ok = pOk;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String pMensagem)
    {
        mensagem = pMensagem;
    }

    public Pessoa getPessoa()
    {
        return Pessoa;
    }

    public void setPessoa(Pessoa pPessoa)
    {
        Pessoa = pPessoa;
    }

    public List<Pessoa> getLista()
    {
        return lista;
    }

    public void setLista(List<Pessoa> pLista)
    {
        lista = pLista;
    }


}
