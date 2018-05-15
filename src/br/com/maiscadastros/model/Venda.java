package br.com.maiscadastros.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TABELA_VENDA")
public class Venda
{
 // Atributos
	@Id
	@Column (name = "Codigo_Venda")
	private int         id;
	
	@Column (name = "Descricao_Venda")
	private String      descricao;
	
	@Column (name = "QuantidadeProduto_Venda")
	private int         quantidadeProduto;
	
	@Column (name = "Valor_Venda")
	private BigDecimal  valor;
	
	@Column (name = "Data_Venda")
	private LocalDate   data;
	
	@Column (name = "NotaFiscal_Venda")
	private long        notaFiscal;
	
	@ManyToOne
	@JoinColumn (name = "ID_Produto")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn (name = "ID_Cliente")
	private Cliente cliente;
	

 // Construtores
    public Venda()
    {
        super();
    }

    public Venda(int pId, String pDescricao, int pQuantidadeProduto, BigDecimal pValor, LocalDate pData, long pNotaFiscal, Produto produto, Cliente cliente)
    {
        super();
        setId(pId);
        setDescricao(pDescricao);
        setQuantidadeProduto(pQuantidadeProduto);
        setValor(pValor);
        setData(pData);
        setNotaFiscal(pNotaFiscal);
        setProduto(produto);
        setCliente(cliente);
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

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String pDescricao)
    {
        descricao = pDescricao;
    }

    public int getQuantidadeProduto()
    {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int pQuantidadeProduto)
    {
        quantidadeProduto = pQuantidadeProduto;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal pValor)
    {
        valor = pValor;
    }

    public LocalDate getData()
    {
        return data;
    }

    public void setData(LocalDate pData)
    {
        data = pData;
    }
    
    public long getNotaFiscal()
    {
        return notaFiscal;
    }

    public void setNotaFiscal(long pNotaFiscal)
    {
        notaFiscal = pNotaFiscal;
    }

    public Produto getProduto()
    {
        return produto;
    }

    public void setProduto(Produto produto)
    {
        this.produto = produto;
    }

    public Cliente getCliente()
    {
        return cliente;
    }

    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }

    // Métodos gerais
    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append("[");
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getDescricao());
        tBuilder.append(", ");
        tBuilder.append(getQuantidadeProduto());
        tBuilder.append(", ");
        tBuilder.append(getValor());
        tBuilder.append(", ");
        tBuilder.append(getData());
        tBuilder.append(", ");
        tBuilder.append(getNotaFiscal());
        tBuilder.append(", ");
        tBuilder.append(getProduto());
        tBuilder.append(", ");
        tBuilder.append(getCliente());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
