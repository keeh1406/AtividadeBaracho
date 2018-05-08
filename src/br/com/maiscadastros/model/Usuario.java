package br.com.maiscadastros.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TABELA_USUARIO")
public abstract class Usuario
{
    // Atributos
	
	@Id
	@Column (name = "Codigo_Usuario")
	private int    id;
	
	@Column (name = "Email_Usuario")
	private String email;
	
	@Column (name = "Senha_Usuario")
	private String senha;
	
	@Column (name = "Nome_Usuario")
	private String nome;

    // Construtores
    public Usuario()
    {
        super();
    }

    public Usuario(int pId, String pEmail, String pSenha, String pNome)
    {
        super();
        setId(pId);
        setEmail(pEmail);
        setSenha(pSenha);
        setNome(pNome);
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String pEmail)
    {
        email = pEmail;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String pSenha)
    {
        senha = pSenha;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String pNome)
    {
        nome = pNome;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getEmail());
        tBuilder.append(", ");
        tBuilder.append(getSenha());
        tBuilder.append(", ");
        tBuilder.append(getNome());
        tBuilder.append("]");
        return tBuilder.toString();
    }

}
