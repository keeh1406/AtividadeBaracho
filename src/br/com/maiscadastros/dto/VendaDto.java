package br.com.maiscadastros.dto;

import java.util.List;

import br.com.maiscadastros.model.Venda;

public class VendaDto 
{
    private boolean     ok;
    private String      mensagem;
    private Venda       Venda;
    private List<Venda> lista;


    public VendaDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public VendaDto(boolean pOk, String pMensagem, Venda pVenda)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        Venda = pVenda;
    }

    public VendaDto(boolean pOk, String pMensagem, List<Venda> pLista)
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

    public Venda getVenda()
    {
        return Venda;
    }

    public void setVenda(Venda pVenda)
    {
        Venda = pVenda;
    }

    public List<Venda> getLista()
    {
        return lista;
    }

    public void setLista(List<Venda> pLista)
    {
        lista = pLista;
    }
}
