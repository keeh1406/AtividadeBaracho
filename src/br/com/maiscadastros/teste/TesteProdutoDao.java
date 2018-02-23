package br.com.maiscadastros.teste;

import java.time.LocalDate;
import java.util.List;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.dao.ProdutoDao;
import br.com.maiscadastros.model.Loja;
import br.com.maiscadastros.model.Produto;

public class TesteProdutoDao 
{
    public static void main(String[] pArgs)
    {
        //
        // Pré Teste
        //
        // Criar um Loja
        Loja tLojaA = new Loja(0, "Super Descontos", 123456789, "dez@gmail.com", 123456789, "Paraíba");
        Loja tLojaB = new Loja(0, "Bolsas.com", 987456321, "bolsas@gmail.com", 987456123, "Praia Mansa");

        // Criando o objeto de persistência
        LojaDao tLojaDao = new LojaDao();

        // Incluir o Loja
        System.out.println();
        System.out.println("Incluindo o Loja");
        Loja tLoja2a = tLojaDao.create(tLojaA);
        if (tLoja2a != null)
            System.out.println("OK...... : " + tLoja2a);
        else
            System.out.println("ERRO.... : " + tLoja2a);
        Loja tLoja2b = tLojaDao.create(tLojaB);
        if (tLoja2b != null)
            System.out.println("OK...... : " + tLoja2b);
        else
            System.out.println("ERRO.... : " + tLoja2b);

        //
        // Teste
        //
        // Criar uma Produto
        
        Produto tProdutoA = new Produto(0, "Bermuda Vermelha", "Jeans", LocalDate.of(2010, 6, 7), "Beauty", tLoja2a.getId());
        Produto tProdutoB = new Produto(0, "Bermuda Amarela", "Social", LocalDate.of(2010, 6, 25), "Viva", tLoja2b.getId());

        // Criando o objeto de persistência
        ProdutoDao tDao = new ProdutoDao();

        // Incluir a Produto
        System.out.println();
        System.out.println("Incluindo");
        Produto tProduto2a = tDao.create(tProdutoA);
        if (tProduto2a != null)
            System.out.println("OK...... : " + tProduto2a);
        else
            System.out.println("ERRO.... : " + tProduto2a);
        Produto tProduto2b = tDao.create(tProdutoB);
        if (tProduto2b != null)
            System.out.println("OK...... : " + tProduto2b);
        else
            System.out.println("ERRO.... : " + tProduto2b);

        // Recuperando a Produto
        System.out.println();
        System.out.println("Recuperando");
        Produto tProduto3a = tDao.recovery(tProduto2a.getId());
        if (tProduto3a != null)
            System.out.println("OK...... : " + tProduto3a);
        else
            System.out.println("ERRO.... : " + tProduto3a);
        Produto tProduto3b = tDao.recovery(tProduto2b.getId());
        if (tProduto3b != null)
            System.out.println("OK...... : " + tProduto3b);
        else
            System.out.println("ERRO.... : " + tProduto3b);

        // Atualizando a Produto
        System.out.println();
        System.out.println("Atualizando");
        tProduto2a.setNome("Calça Vermelha");
        tProduto2a.setDescricao("Skinne");
        tProduto2a.setDataValidade(LocalDate.of(2009, 6, 4));
        tProduto2a.setMarca("Popai");
        tProduto2a.setIdLoja(tLoja2b.getId());
        Produto tProduto4a = tDao.update(tProduto2a);
        if (tProduto4a != null)
            System.out.println("OK...... : " + tProduto4a);
        else
            System.out.println("ERRO.... : " + tProduto4a);
        tProduto2a.setNome("Calça Amarela");
        tProduto2a.setDescricao("Furada");
        tProduto2a.setDataValidade(LocalDate.of(2009, 10, 4));
        tProduto2a.setMarca("Mamãe");
        tProduto2a.setIdLoja(tLoja2b.getId());
        Produto tProduto4b = tDao.update(tProduto2b);
        if (tProduto4a != null)
            System.out.println("OK...... : " + tProduto4b);
        else
            System.out.println("ERRO.... : " + tProduto4b);

        // Recuperando a Produto
        System.out.println();
        System.out.println("Recuperando");
        Produto tProduto5a = tDao.recovery(tProduto2a.getId());
        if (tProduto5a != null)
            System.out.println("OK...... : " + tProduto5a);
        else
            System.out.println("ERRO.... : " + tProduto5a);
        Produto tProduto5b = tDao.recovery(tProduto2b.getId());
        if (tProduto5b != null)
            System.out.println("OK...... : " + tProduto5b);
        else
            System.out.println("ERRO.... : " + tProduto5b);

        // Listar as Produtos
        List<Produto> tLista = tDao.search();
        System.out.println();
        System.out.println("Pesquisando");
        for (Produto tProduto : tLista)
        {
            System.out.println("OK...... : " + tProduto);
        }

        // Listar as Produtos
        tLista = tDao.searchByIdLoja(tLoja2a.getId());
        System.out.println();
        System.out.println("Pesquisando por Loja");
        for (Produto tProduto : tLista)
        {
            System.out.println("OK...... : " + tProduto);
        }

        // Contar as Produtos por Loja
        int tQtde = tDao.countByLoja(tLoja2a.getId());
        System.out.println();
        System.out.println("Contando Produtos por Loja");
        System.out.println("OK...... : " + tQtde);

        // Remover a Produto
        System.out.println();
        System.out.println("Removendo");
        if (tDao.delete(tProduto2a.getId()))
            System.out.println("OK...... : " + tProduto2a);
        else
            System.out.println("ERRO.... : " + tProduto2a);
        if (tDao.delete(tProduto2b.getId()))
            System.out.println("OK...... : " + tProduto2b);
        else
            System.out.println("ERRO.... : " + tProduto2b);

        // Verificando se removeu
        System.out.println();
        System.out.println("Verificando a remoção");
        if (tDao.delete(tProduto2a.getId()))
            System.out.println("ERRO.... : " + tProduto2a);
        else
            System.out.println("OK...... : " + tProduto2a);
        if (tDao.delete(tProduto2b.getId()))
            System.out.println("ERRO.... : " + tProduto2b);
        else
            System.out.println("OK...... : " + tProduto2b);

        //
        // Pós teste
        //
        // Remover o Loja
        System.out.println();
        System.out.println("Removendo");
        if (tLojaDao.delete(tLoja2a.getId()))
            System.out.println("OK...... : " + tLoja2a);
        else
            System.out.println("ERRO.... : " + tLoja2a);
        if (tLojaDao.delete(tLoja2b.getId()))
            System.out.println("OK...... : " + tLoja2b);
        else
            System.out.println("ERRO.... : " + tLoja2b);

    }
}
