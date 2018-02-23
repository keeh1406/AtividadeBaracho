package br.com.maiscadastros.model;

import java.time.LocalDate;

public class Produto
{
    // Atributos
    private int         id;
    private String      nome;
    private String      descricao;
    private LocalDate   dataValidade;
    private String      marca;
    private int         idLoja;

 // Construtores
    public Produto()
    {
        super();
    }

    public Produto(int pId, String pNome, String pDescricao, LocalDate pDataValidade, String pMarca, int pIdLoja)
    {
        super();
        setId(pId);
        setNome(pNome);
        setDescricao(pDescricao);
        setDataValidade(pDataValidade);
        setMarca(pMarca);
        setIdLoja(pIdLoja);
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

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String pMarca)
    {
        marca = pMarca;
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
        tBuilder.append(getId());
        tBuilder.append(", ");
        tBuilder.append(getNome());
        tBuilder.append(", ");
        tBuilder.append(getDescricao());
        tBuilder.append(", ");
        tBuilder.append(getDataValidade());
        tBuilder.append(", ");
        tBuilder.append(getMarca());
        tBuilder.append(", ");
        tBuilder.append(getIdLoja());
        tBuilder.append("]");
        return tBuilder.toString();
    }
}
