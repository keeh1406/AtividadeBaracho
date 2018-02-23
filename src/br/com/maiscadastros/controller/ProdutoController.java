package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.ProdutoDao;
import br.com.maiscadastros.dto.ProdutoDto;
import br.com.maiscadastros.model.Produto;

public class ProdutoController 
{
    public ProdutoDto cadastrarProduto(Produto pProduto)
    {
        // Verificar as informações
        if (pProduto == null)
        {
            return new ProdutoDto(false, "Tentativa de inclusão de Produto nulo");
        }

        // Criando o objeto de persistência
        ProdutoDao tDao = new ProdutoDao();

        // Verificando se o produto já existe
        Produto tProduto = tDao.recoveryByNome(pProduto.getNome());
        if (tProduto != null)
        {
            return new ProdutoDto(false, "Já existe um produto com o nome informado");
        }

        // Incluindo o produto
        tProduto = tDao.create(pProduto);
        if (tProduto == null)
        {
            return new ProdutoDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Produto incluído com sucesso", tProduto);
    }

    public ProdutoDto recuperarProduto(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new ProdutoDto(false, "Identificador do Produto inválido");
        }

        // Criando o objeto de persistência
        ProdutoDao tDao = new ProdutoDao();

        // Recuperando o Produto
        Produto tProduto = tDao.recovery(pId);
        if (tProduto == null)
        {
            return new ProdutoDto(false, "Não existe Produto com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Produto recuperado com sucesso", tProduto);
    }

    public ProdutoDto atualizarProduto(Produto pProduto)
    {
        // Verificar as informações
        if (pProduto == null)
        {
            return new ProdutoDto(false, "Tentativa de atualização de Produto nulo");
        }

        // Criando o objeto de persistência
        ProdutoDao tDao = new ProdutoDao();

        // Verificando se existe um Produto com o novo Nome
        Produto tProduto = tDao.recoveryByNome(pProduto.getNome());
        if (tProduto != null)
        {
            return new ProdutoDto(false, "Já existe Produto com o nome informado");
        }

        // Atualizando o Produto
        tProduto = tDao.update(pProduto);
        if (tProduto == null)
        {
            return new ProdutoDto(false, "Não existe Produto com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Produto alterado com sucesso", tProduto);
    }

    public ProdutoDto removeProduto(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new ProdutoDto(false, "Identificador do Produto inválido");
        }

        // Criando o objeto de persistência
        ProdutoDao tDao = new ProdutoDao();

        // Incluindo o Produto
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new ProdutoDto(true, "Produto removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new ProdutoDto(false, "Erro no processo de remoção");
    }
    
    public ProdutoDto pesquisarProdutosPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<Produto> tLista = new ArrayList<>();

        // Criando o objeto de persistência
        ProdutoDao tDao = new ProdutoDao();

        // Recuperando o Produto
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Lista de Produtos recuperada com sucesso", tLista);
    }
    
    public ProdutoDto pesquisarProduto()
    {
        // Criando a lista de retorno
        List<Produto> tLista = new ArrayList<>();

        // Criando o objeto de persistência
        ProdutoDao tDao = new ProdutoDao();

        // Recuperando o Produto
        tLista = tDao.search();

        // Retornando o indicativo de sucesso
        return new ProdutoDto(true, "Lista de Produtos recuperada com sucesso", tLista);
    }
    
}
