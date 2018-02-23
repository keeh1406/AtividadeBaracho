package br.com.maiscadastros.model;

import java.time.LocalDate;

import br.com.maiscadastros.model.Usuario;

public class Funcionario extends Usuario
{
    // Atributos
    private LocalDate dataNascimento;
    private long      telefone;
    private long      cpf;
    private String    endereco;
    private boolean   flGerente;
    private int       idLoja;

    // Construtores
    public Funcionario()
    {
        super();
    }

    public Funcionario(int pId, String pNome, LocalDate pDataNascimento, long pTelefone, String pEmail, String pSenha, long pCpf, String pEndereco, Boolean pFlGerente, int pIdLoja)
    {
        super(pId, pEmail, pSenha, pNome);
        setDataNascimento(pDataNascimento);
        setTelefone(pTelefone);
        setCpf(pCpf);
        setEndereco(pEndereco);
        setFlGerente(pFlGerente);
        setIdLoja(pIdLoja);
    }

    // Métodos de acesso

    public LocalDate getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate pDataNascimento)
    {
        dataNascimento = pDataNascimento;
    }

    public long getTelefone()
    {
        return telefone;
    }

    public void setTelefone(long pTelefone)
    {
        telefone = pTelefone;
    }

    public long getCpf()
    {
        return cpf;
    }

    public void setCpf(long pCpf)
    {
        cpf = pCpf;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String pEndereco)
    {
        endereco = pEndereco;
    }

    public boolean getFlGerente()
    {
        return flGerente;
    }

    public void setFlGerente(Boolean pFlGerente)
    {
        flGerente = pFlGerente;
    }

    public int getIdLoja()
    {
        return idLoja;
    }

    public void setIdLoja(int pIdLoja)
    {
        idLoja = pIdLoja;
    }

    // Métodos gerais
    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append("[");
        tBuilder.append(super.toString());
        tBuilder.append(", ");
        tBuilder.append(getDataNascimento());
        tBuilder.append(", ");
        tBuilder.append(getTelefone());
        tBuilder.append(", ");
        tBuilder.append(getCpf());
        tBuilder.append(", ");
        tBuilder.append(getEndereco());
        tBuilder.append(", ");
        tBuilder.append(getFlGerente());
        tBuilder.append(", ");
        tBuilder.append(getIdLoja());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
