package br.com.maiscadastros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TABELA_LOJA")
public class Loja
{
    // Atributos
	
	@Id
	@Column (name = "Codigo_Loja")
	private int        id;
	
	@Column (name = "Nome_Loja")
	private String     nome;
	
	@Column (name = "Telefone_Loja")
	private long       telefone;
	
	@Column (name = "Email_loja")
	private String     email;
	
	@Column (name = "CNPJ_Loja")
	private long       cnpj;
	
	@ManyToOne
	@JoinColumn (name = "Endereco_Loja")
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
