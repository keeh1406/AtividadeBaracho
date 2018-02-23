package br.com.maiscadastros.dto;

import java.util.List;

import br.com.maiscadastros.model.Produto;

public class ProdutoDto 
{
    private boolean       ok;
    private String        mensagem;
    private Produto       Produto;
    private List<Produto> lista;


    public ProdutoDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public ProdutoDto(boolean pOk, String pMensagem, Produto pProduto)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        Produto = pProduto;
    }

    public ProdutoDto(boolean pOk, String pMensagem, List<Produto> pLista)
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

    public Produto getProduto()
    {
        return Produto;
    }

    public void setProduto(Produto pProduto)
    {
        Produto = pProduto;
    }

    public List<Produto> getLista()
    {
        return lista;
    }

    public void setLista(List<Produto> pLista)
    {
        lista = pLista;
    }
}
