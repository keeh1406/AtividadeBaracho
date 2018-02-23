package br.com.maiscadastros.dto;

import java.util.List;

import br.com.maiscadastros.model.Funcionario;

public class FuncionarioDto
{
    private boolean           ok;
    private String            mensagem;
    private Funcionario       funcionario;
    private List<Funcionario> lista;


    public FuncionarioDto(boolean pOk, String pMensagem)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public FuncionarioDto(boolean pOk, String pMensagem, Funcionario pFuncionario)
    {
        super();
        ok = pOk;
        mensagem = pMensagem;
        funcionario = pFuncionario;
    }

    public FuncionarioDto(boolean pOk, String pMensagem, List<Funcionario> pLista)
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

    public Funcionario getFuncionario()
    {
        return funcionario;
    }

    public void setFuncionario(Funcionario pFuncionario)
    {
        funcionario = pFuncionario;
    }

    public List<Funcionario> getLista()
    {
        return lista;
    }

    public void setLista(List<Funcionario> pLista)
    {
        lista = pLista;
    }

}
