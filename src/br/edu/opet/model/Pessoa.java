package br.edu.opet.model;

import java.time.LocalDate;

import br.edu.opet.model.Usuario;

public class Pessoa extends Usuario
{
    // Atributos
    private LocalDate dataNascimento;
    private long      telefone;
    private long      cpf;
    private String    endereco;
    private String    status;

    // Construtores
    public Pessoa()
    {
        super();
    }

    public Pessoa(int pId, String pNome, LocalDate pDataNascimento, long pTelefone, String pEmail, String pSenha, long pCpf, String pEndereco, String pStatus)
    {
        super(pId, pEmail, pSenha, pNome);
        setDataNascimento(pDataNascimento);
        setTelefone(pTelefone);
        setCpf(pCpf);
        setEndereco(pEndereco);
        setStatus(pStatus);
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
    
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String pStatus)
    {
        status = pStatus;
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
        tBuilder.append(getStatus());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
