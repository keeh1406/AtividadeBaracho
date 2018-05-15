package br.com.maiscadastros.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TABELA_PRODUTO")
public class Produto
{
    // Atributos
	@Id
	@Column (name = "Codigo_Produto")
	private int         id;
	
	@Column (name = "Nome_Produto")
	private String      nome;
	
	@Column (name = "Descricao_Produto")
	private String      descricao;
	
	@Column (name = "DataValidade_Produto")
	private LocalDate   dataValidade;
	
	@ManyToOne
	@JoinColumn (name = "ID_Loja")
	private Loja loja;
	
	@ManyToOne
	@JoinColumn (name = "ID_Setor")
	private Setor setor;
	
	@ManyToOne
	@JoinColumn (name = "ID_Marca")
	private Marca marca;

 // Construtores
    public Produto()
    {
        super();
    }

    public Produto(int pId, String pNome, String pDescricao, LocalDate pDataValidade, Loja loja, Setor setor, Marca marca)
    {
        super();
        setId(pId);
        setNome(pNome);
        setDescricao(pDescricao);
        setDataValidade(pDataValidade);
        setLoja(loja);
        setSetor(setor);
        setMarca(marca);
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String pDescricao)
    {
        descricao = pDescricao;
    }

    public LocalDate getDataValidade()
    {
        return dataValidade;
    }

    public void setDataValidade(LocalDate pDataValidade)
    {
        dataValidade = pDataValidade;
    }

    public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}
    
    public Setor getSetor()
    {
        return setor;
    }

    public void setSetor(Setor setor)
    {
        this.setor = setor;
    }
    
    public Marca getMarca()
    {
        return marca;
    }

    public void setMarca(Marca marca)
    {
    	this.marca = marca;
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
        tBuilder.append(getDescricao());
        tBuilder.append(", ");
        tBuilder.append(getDataValidade());
        tBuilder.append(", ");
        tBuilder.append(getLoja());
        tBuilder.append(", ");
        tBuilder.append(getSetor());
        tBuilder.append(", ");
        tBuilder.append(getMarca());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
