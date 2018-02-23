package br.com.maiscadastros.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import br.com.maiscadastros.dao.VendaDao;
import br.com.maiscadastros.dao.ClienteDao;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.dao.ProdutoDao;
import br.com.maiscadastros.model.Venda;
import br.com.maiscadastros.model.Cliente;
import br.com.maiscadastros.model.Loja;
import br.com.maiscadastros.model.Produto;

public class TesteVendaDao 
{
	   public static void main(String[] pArgs)
	    {
	        //
	        // Pré Teste
	        //
		   
		    // Criar uma Loja
		    Loja tLojaA = new Loja(0, "SuperViva", 3456767, "vivamais@gmail.com", 37834534452L, "Acre");
	        Loja tLojaB = new Loja(0, "Casa do 10", 3677676, "dezreais@gmail.com", 11111111111L, "Roraima");
	        
	        // Criando o objeto de persistência
	        LojaDao tDao = new LojaDao();

	        // Incluir o Loja
	        System.out.println();
	        System.out.println("Incluindo a Loja");
	        Loja tLoja2a = tDao.create(tLojaA);
	        if (tLoja2a != null)
	            System.out.println("OK...... : " + tLoja2a);
	        else
	            System.out.println("ERRO.... : " + tLoja2a);
	        Loja tLoja2b = tDao.create(tLojaB);
	        if (tLoja2b != null)
	            System.out.println("OK...... : " + tLoja2b);
	        else
	            System.out.println("ERRO.... : " + tLoja2b);
	        
	        // Criar um Produto
	        Produto tProdutoA = new Produto(0, "Bermuda Vermelha", "Jeans", LocalDate.of(1998, 6, 4), "King", tLoja2a.getId());
	        Produto tProdutoB = new Produto(0, "Bermuda Amarela", "Jeans", LocalDate.of(1998, 7, 4), "Kong", tLoja2b.getId());

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
	        Produto tProduto2b = tProdutoDao.create(tProdutoB);
	        if (tProduto2b != null)
	            System.out.println("OK...... : " + tProduto2b);
	        else
	            System.out.println("ERRO.... : " + tProduto2b);

	        // Criar um Cliente
	        Cliente tClienteA = new Cliente(0, "Estroncio Vago", LocalDate.of(2000, 6, 4), 3432, "estra@gmail.com", "eusei", 12345678915L, "Ponta Grossa");
	        Cliente tClienteB = new Cliente(0, "Martinha", LocalDate.of(2002, 6, 4), 3732, "mar@gmail.com", "tempoaotempo", 78945678915L, "Ponta Pequena");

	        // Criando o objeto de persistência
	        ClienteDao tClienteDao = new ClienteDao();

	        // Incluir o Cliente
	        System.out.println();
	        System.out.println("Incluindo Cliente");
	        Cliente tCliente2a = tClienteDao.create(tClienteA);
	        if (tCliente2a != null)
	            System.out.println("OK...... : " + tCliente2a);
	        else
	            System.out.println("ERRO.... : " + tCliente2a);
	        
	        Cliente tCliente2b = tClienteDao.create(tClienteB);
	        if (tCliente2b != null)
	            System.out.println("OK...... : " + tCliente2b);
	        else
	            System.out.println("ERRO.... : " + tCliente2b);

	        //
	        // Teste
	        //
	        // Criar uma Venda
	        
	        Venda tVendaA = new Venda(0, "Muitas Bermudas Vermelhas", 70, new BigDecimal ("1000.00"), LocalDate.of(2010, 7, 4), 902, tProduto2a.getId(), tCliente2a.getId());
	        Venda tVendaB = new Venda(0, "Muitas Bermudas Amarelas", 70, new BigDecimal ("2000.00"), LocalDate.of(2010, 6, 7), 854, tProduto2b.getId(), tCliente2b.getId());

	        // Criando o objeto de persistência
	        VendaDao tVendaDao = new VendaDao();

	        // Incluir a Venda
	        System.out.println();
	        System.out.println("Incluindo");
	        Venda tVenda2a = tVendaDao.create(tVendaA);
	        if (tVenda2a != null)
	            System.out.println("OK...... : " + tVenda2a);
	        else
	            System.out.println("ERRO.... : " + tVenda2a);
	        Venda tVenda2b = tVendaDao.create(tVendaB);
	        if (tVenda2b != null)
	            System.out.println("OK...... : " + tVenda2b);
	        else
	            System.out.println("ERRO.... : " + tVenda2b);

	        // Recuperando a Venda
	        System.out.println();
	        System.out.println("Recuperando");
	        Venda tVenda3a = tVendaDao.recovery(tVenda2a.getId());
	        if (tVenda3a != null)
	            System.out.println("OK...... : " + tVenda3a);
	        else
	            System.out.println("ERRO.... : " + tVenda3a);
	        Venda tVenda3b = tVendaDao.recovery(tVenda2b.getId());
	        if (tVenda3b != null)
	            System.out.println("OK...... : " + tVenda3b);
	        else
	            System.out.println("ERRO.... : " + tVenda3b);

	        // Atualizando a Venda
	        System.out.println();
	        System.out.println("Atualizando");
	        tVenda2a.setDescricao("Muitas Calças Vermelhas");
	        tVenda2a.setQuantidadeProduto(80);
	        tVenda2a.setValor(new BigDecimal("500.00"));
	        tVenda2a.setData(LocalDate.of(2009, 6, 4));
	        tVenda2a.setNotaFiscal(159357);
	        tVenda2a.setIdProduto(tProduto2b.getId());
	        tVenda2a.setIdCliente(tCliente2b.getId());
	        Venda tVenda4a = tVendaDao.update(tVenda2a);
	        if (tVenda4a != null)
	            System.out.println("OK...... : " + tVenda4a);
	        else
	            System.out.println("ERRO.... : " + tVenda4a);
	        tVenda2b.setDescricao("Muitas Calças Amarelas");
	        tVenda2b.setQuantidadeProduto(80);
	        tVenda2b.setValor(new BigDecimal("500.00"));
	        tVenda2b.setData(LocalDate.of(2014, 6, 4));
	        tVenda2b.setNotaFiscal(357159);
	        tVenda2b.setIdProduto(tProduto2a.getId());
	        tVenda2b.setIdCliente(tCliente2a.getId());
	        Venda tVenda4b = tVendaDao.update(tVenda2b);
	        if (tVenda4a != null)
	            System.out.println("OK...... : " + tVenda4b);
	        else
	            System.out.println("ERRO.... : " + tVenda4b);

	        // Recuperando a Venda
	        System.out.println();
	        System.out.println("Recuperando");
	        Venda tVenda5a = tVendaDao.recovery(tVenda2a.getId());
	        if (tVenda5a != null)
	            System.out.println("OK...... : " + tVenda5a);
	        else
	            System.out.println("ERRO.... : " + tVenda5a);
	        Venda tVenda5b = tVendaDao.recovery(tVenda2b.getId());
	        if (tVenda5b != null)
	            System.out.println("OK...... : " + tVenda5b);
	        else
	            System.out.println("ERRO.... : " + tVenda5b);

	        // Listar as Vendas
	        List<Venda> tLista = tVendaDao.search();
	        System.out.println();
	        System.out.println("Pesquisando");
	        for (Venda tVenda : tLista)
	        {
	            System.out.println("OK...... : " + tVenda);
	        }

	        // Listar as Vendas
	        tLista = tVendaDao.searchByIdProduto(tProduto2a.getId());
	        System.out.println();
	        System.out.println("Pesquisando por Produto");
	        for (Venda tVenda : tLista)
	        {
	            System.out.println("OK...... : " + tVenda);
	        }

	        // Contar as Vendas por Produto
	        int tQtde = tVendaDao.countByProduto(tProduto2a.getId());
	        System.out.println();
	        System.out.println("Contando Vendas por Produto");
	        System.out.println("OK...... : " + tQtde);
	    
	        // Remover a Venda
	        System.out.println();
	        System.out.println("Removendo");
	        if (tVendaDao.delete(tVenda2a.getId()))
	            System.out.println("OK...... : " + tVenda2a);
	        else
	            System.out.println("ERRO.... : " + tVenda2a);
	        if (tDao.delete(tVenda2b.getId()))
	            System.out.println("OK...... : " + tVenda2b);
	        else
	            System.out.println("ERRO.... : " + tVenda2b);

	        // Verificando se removeu
	        System.out.println();
	        System.out.println("Verificando a remoção");
	        if (tVendaDao.delete(tVenda2a.getId()))
	            System.out.println("ERRO.... : " + tVenda2a);
	        else
	            System.out.println("OK...... : " + tVenda2a);
	        if (tVendaDao.delete(tVenda2b.getId()))
	            System.out.println("ERRO.... : " + tVenda2b);
	        else
	            System.out.println("OK...... : " + tVenda2b);

	        //
	        // Pós teste
	        //
	        // Remover o Produto
	        System.out.println();
	        System.out.println("Removendo");
	        if (tProdutoDao.delete(tProduto2a.getId()))
	            System.out.println("OK...... : " + tProduto2a);
	        else
	            System.out.println("ERRO.... : " + tProduto2a);
	        if (tProdutoDao.delete(tProduto2b.getId()))
	            System.out.println("OK...... : " + tProduto2b);
	        else
	            System.out.println("ERRO.... : " + tProduto2b);

	        // Remover o Cliente
	        System.out.println();
	        System.out.println("Removendo");
	        if (tClienteDao.delete(tCliente2a.getId()))
	            System.out.println("OK...... : " + tCliente2a);
	        else
	            System.out.println("ERRO.... : " + tCliente2a);
	        if (tClienteDao.delete(tCliente2b.getId()))
	            System.out.println("OK...... : " + tCliente2b);
	        else
	            System.out.println("ERRO.... : " + tCliente2b);

	    }
}
