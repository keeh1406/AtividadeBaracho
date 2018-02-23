package br.com.maiscadastros.dto;

import java.util.List;

import br.com.maiscadastros.model.Cliente;

public class ClienteDto
{
    private boolean           ok;
    private String            mensagem;
    private Cliente           Cliente;
    private List<Cliente>     lista;


    public ClienteDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public ClienteDto(boolean pOk, String pMensagem, Cliente pCliente)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        Cliente = pCliente;
    }

    public ClienteDto(boolean pOk, String pMensagem, List<Cliente> pLista)
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

    public Cliente getCliente()
    {
        return Cliente;
    }

    public void setCliente(Cliente pCliente)
    {
        Cliente = pCliente;
    }

    public List<Cliente> getLista()
    {
        return lista;
    }

    public void setLista(List<Cliente> pLista)
    {
        lista = pLista;
    }


}
