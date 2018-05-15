package br.com.maiscadastros.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.maiscadastros.model.Usuario;

@Entity
@Table(name = "TABELA_CLIENTE")
public class Cliente extends Usuario
{
    // Atributos
	@Column (name = "DataNascimento_Cliente")
	private LocalDate dataNascimento;
	
	@Column (name = "Telefone_Cliente")
	private long      telefone;
	
	@Column (name = "CPF_Cliente")
	private long      cpf;
	
	@ManyToOne
	@JoinColumn (name = "Endereco_Cliente")
	private String    endereco;

    // Construtores
    public Cliente()
    {
        super();
    }

    public Cliente(int pId, String pNome, LocalDate pDataNascimento, long pTelefone, String pEmail, String pSenha, long pCpf, String pEndereco)
    {
        super(pId, pEmail, pSenha, pNome);
        setDataNascimento(pDataNascimento);
        setTelefone(pTelefone);
        setCpf(pCpf);
        setEndereco(pEndereco);
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
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
