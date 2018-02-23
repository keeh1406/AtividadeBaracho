package br.com.maiscadastros.dto;

import java.util.List;

import br.com.maiscadastros.model.Loja;

public class LojaDto 
{
    private boolean    ok;
    private String     mensagem;
    private Loja       Loja;
    private List<Loja> lista;


    public LojaDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public LojaDto(boolean pOk, String pMensagem, Loja pLoja)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        Loja = pLoja;
    }

    public LojaDto(boolean pOk, String pMensagem, List<Loja> pLista)
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

    public Loja getLoja()
    {
        return Loja;
    }

    public void setLoja(Loja pLoja)
    {
        Loja = pLoja;
    }

    public List<Loja> getLista()
    {
        return lista;
    }

    public void setLista(List<Loja> pLista)
    {
        lista = pLista;
    }

}
