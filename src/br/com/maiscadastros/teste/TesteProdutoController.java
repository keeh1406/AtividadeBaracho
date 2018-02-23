package br.com.maiscadastros.teste;

import java.time.LocalDate;
import br.com.maiscadastros.controller.ProdutoController;
import br.com.maiscadastros.dao.ProdutoDao;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.dto.ProdutoDto;
import br.com.maiscadastros.model.Produto;
import br.com.maiscadastros.model.Loja;

public class TesteProdutoController 
{
    public static void main(String[] pArgs)
    {
    	//Pré Teste
    	//
    	//
    	// Criar uma Loja
        Loja tLojaA = new Loja(0, "Super Descontos", 123456789, "dez@gmail.com", 123456789, "Paraíba");

        // Criando o objeto de persistência
        LojaDao tLojaDao = new LojaDao();

        // Incluir a Loja
        System.out.println();
        System.out.println("Incluindo a Loja");
        Loja tLoja2a = tLojaDao.create(tLojaA);
        if (tLoja2a != null)
            System.out.println("OK...... : " + tLoja2a);
        else
            System.out.println("ERRO.... : " + tLoja2a);
    	
        // Criar um Produto
        Produto tProdutoA = new Produto(0, "Bermuda Vermelha", "Jeans", LocalDate.of(2017, 8, 29), "Beauty", tLoja2a.getId());
 
        // Criando o objeto de persistência
        ProdutoDao tProdutoDao = new ProdutoDao();

        // Incluir o Produto
        System.out.println();
        System.out.println("Incluindo o Produto");
        Produto tProduto2a = tProdutoDao.create(tProdutoA);
        if (tProduto2a != null)
            System.out.println("OK...... : " + tProduto2a);
        else
            System.out.println("ERRO.... : " + tProduto2a);

        //
        // Teste
        //

        // Criando o objeto de Controller
        ProdutoController tController = new ProdutoController();

        // Criar um Produto
        Produto tProdutoB = new Produto(0, "Bermuda Amarela", "Social", LocalDate.of(2008, 8, 29), "Viva", tLoja2a.getId());
        
        // Criar o Produto
        System.out.println();
        System.out.println("Incluindo um Produto via controller");
        ProdutoDto tDto = tController.cadastrarProduto(tProdutoB);
        if (tDto.isOk())
        {
            // Recuperando o Produto incluído para obter o ID gerado
            tProdutoB = tDto.getProduto();
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tProdutoB);
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Incluindo um Produto nulo");
        tDto = tController.cadastrarProduto(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar o Produto
        System.out.println();
        System.out.println("Recuperando um Produto via controller");
        tDto = tController.recuperarProduto(tProdutoB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getProduto());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando um Produto com id inválido");
        tDto = tController.recuperarProduto(-32432);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando um Produto não existente");
        tDto = tController.recuperarProduto(9999999);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Atualizar o Produto
        tProdutoB.setNome(tProdutoB.getNome() + " Kond");
        tProdutoB.setDescricao("silvantino@gmail.com");
        tProdutoB.setDataValidade(LocalDate.of(2016, 8, 29));
        tProdutoB.setMarca("Kond");
        tProdutoB.setIdLoja(tLoja2a.getId());

        // Atualizando o Produto
        System.out.println();
        System.out.println("Atualizando um Produto via controller");
        tDto = tController.atualizarProduto(tProdutoB);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getProduto());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Atualizando um Produto nulo");
        tDto = tController.atualizarProduto(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        Produto tProdutoC = new Produto(99999, "nono nono", "nono", LocalDate.now(), "nono", tLoja2a.getId());

        System.out.println();
        System.out.println("Atualizando um Produto que não existe");
        tDto = tController.atualizarProduto(tProdutoC);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar o Produto
        System.out.println();
        System.out.println("Recuperando um Produto via controller");
        tDto = tController.recuperarProduto(tProdutoB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getProduto());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Removendo o Produto
        System.out.println();
        System.out.println("Removendo um Produto via controller");
        tDto = tController.removeProduto(tProdutoB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo um Produto com id inválido");
        tDto = tController.removeProduto(-4);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo um Produto com Loja");
        tDto = tController.removeProduto(tProdutoA.getId());
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }


        //
        // Pós teste
        //
        // Remover a Loja
        System.out.println();
        System.out.println("Removendo a Venda");
        if (tLojaDao.delete(tLoja2a.getId()))
            System.out.println("OK...... : " + tLoja2a);
        else
            System.out.println("ERRO.... : " + tLoja2a);

    
}
    
}