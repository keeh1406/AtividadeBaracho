package br.com.maiscadastros.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda
{
 // Atributos
    private int         id;
    private String      descricao;
    private int         quantidadeProduto;
    private BigDecimal  valor;
    private LocalDate   data;
    private long        notaFiscal;
    private int         idProduto;
    private int         idCliente;

 // Construtores
    public Venda()
    {
        super();
    }

    public Venda(int pId, String pDescricao, int pQuantidadeProduto, BigDecimal pValor, LocalDate pData, long pNotaFiscal, int pIdProduto, int pIdCliente)
    {
        super();
        setId(pId);
        setDescricao(pDescricao);
        setQuantidadeProduto(pQuantidadeProduto);
        setValor(pValor);
        setData(pData);
        setNotaFiscal(pNotaFiscal);
        setIdProduto(pIdProduto);
        setIdCliente(pIdCliente);
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

    public int getIdProduto()
    {
        return idProduto;
    }

    public void setIdProduto(int pIdProduto)
    {
        idProduto = pIdProduto;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int pIdCliente)
    {
        idCliente = pIdCliente;
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
        tBuilder.append(getIdProduto());
        tBuilder.append(", ");
        tBuilder.append(getIdCliente());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
