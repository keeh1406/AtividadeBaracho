package br.com.maiscadastros.model;

public class Loja
{
    // Atributos
    private int        id;
    private String     nome;
    private long       telefone;
    private String     email;
    private long       cnpj;
    private String     endereco;

    // Construtores
    public Loja()
    {
        super();
    }

    public Loja(int pId, String pNome, long pTelefone, String pEmail, long pCnpj, String pEndereco)
    {
        super();
        setId(pId);
        setNome(pNome);
        setTelefone(pTelefone);
        setEmail(pEmail);
        setCnpj(pCnpj);
        setEndereco(pEndereco);
    }

    // Métodos de acesso
    public int getId()
    {
        return id;
    }

    public void setId(int pId)
    {
        id = pId;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    public long getTelefone()
    {
        return telefone;
    }

    public void setTelefone(long pTelefone)
    {
        telefone = pTelefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String pEmail)
    {
        email = pEmail;
    }

    public long getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(long pCnpj)
    {
        cnpj = pCnpj;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String pEndereco)
    {
        endereco = pEndereco;
    }

    // Métodos gerais
    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append("[");
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getNome());
        tBuilder.append(", ");
        tBuilder.append(getTelefone());
        tBuilder.append(", ");
        tBuilder.append(getEmail());
        tBuilder.append(", ");
        tBuilder.append(getCnpj());
        tBuilder.append(", ");
        tBuilder.append(getEndereco());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
