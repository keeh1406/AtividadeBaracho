package br.com.maiscadastros.teste;

import java.math.BigDecimal;
import java.time.LocalDate;
import br.com.maiscadastros.controller.VendaController;
import br.com.maiscadastros.dao.ClienteDao;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.dao.ProdutoDao;
import br.com.maiscadastros.dto.VendaDto;
import br.com.maiscadastros.model.Cliente;
import br.com.maiscadastros.model.Loja;
import br.com.maiscadastros.model.Produto;
import br.com.maiscadastros.model.Venda;

public class TesteVendaController 
{
    public static void main(String[] pArgs)
    {
        //
        // Pré Teste
        //
        // Criar um Cliente
    	Cliente tClienteA = new Cliente(0, "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767, "yerla@gmail.com","uvwyz", 37834534452L, "Paraná");

        // Criando o objeto de persistência
        ClienteDao tClienteDao = new ClienteDao();

        // Incluir o Cliente
        System.out.println();
        System.out.println("Incluindo o Cliente");
        Cliente tCliente2a = tClienteDao.create(tClienteA);
        if (tCliente2a != null)
            System.out.println("OK...... : " + tCliente2a);
        else
            System.out.println("ERRO.... : " + tCliente2a);
        
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
        VendaController tController = new VendaController();

        // Criar uma Venda
        Venda tVendaB = new Venda(0, "Muitas Bermudas Vermelhas", 70, new BigDecimal ("1000.00"), LocalDate.of(2015, 8, 29), 902, tProduto2a.getId(), tCliente2a.getId());
        
        // Criar a Venda
        System.out.println();
        System.out.println("Incluindo uma Venda via controller");
        VendaDto tDto = tController.cadastrarVenda(tVendaB);
        if (tDto.isOk())
        {
            // Recuperando o Venda incluído para obter o ID gerado
            tVendaB = tDto.getVenda();
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tVendaB);
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Incluindo uma Venda nula");
        tDto = tController.cadastrarVenda(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar a Venda
        System.out.println();
        System.out.println("Recuperando uma Venda via controller");
        tDto = tController.recuperarVenda(tVendaB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getVenda());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando uma Venda com id inválido");
        tDto = tController.recuperarVenda(-32432);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando uma Venda não existente");
        tDto = tController.recuperarVenda(9999999);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Atualizar a Venda
        tVendaB.setDescricao("Bermudas Vermelhas de Varios Tamanhos");
        tVendaB.setQuantidadeProduto(4);
        tVendaB.setValor(new BigDecimal (100.00));
        tVendaB.setData(LocalDate.of(2014, 8, 29));
        tVendaB.setNotaFiscal(1597536847);
        tVendaB.setIdProduto(tProduto2a.getId());
        tVendaB.setIdCliente(tCliente2a.getId());


        // Atualizando a Venda
        System.out.println();
        System.out.println("Atualizando uma Venda via controller");
        tDto = tController.atualizarVenda(tVendaB);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getVenda());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Atualizando uma Venda nulo");
        tDto = tController.atualizarVenda(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        Venda tVendaC = new Venda(99999, "nono nono", 0, new BigDecimal(0), LocalDate.now(), 1, tProduto2a.getId(), tCliente2a.getId());

        System.out.println();
        System.out.println("Atualizando uma Venda que não existe");
        tDto = tController.atualizarVenda(tVendaC);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar o Cliente
        System.out.println();
        System.out.println("Recuperando uma Venda via controller");
        tDto = tController.recuperarVenda(tVendaB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getVenda());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Removendo a Venda
        System.out.println();
        System.out.println("Removendo uma Venda via controller");
        tDto = tController.removeVenda(tVendaB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo uma Venda com id inválido");
        tDto = tController.removeVenda(-4);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo uma Venda");
        tDto = tController.removeVenda(tVendaB.getId());
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
        // Remover o Cliente
        System.out.println();
        System.out.println("Removendo o Cliente");
        if (tClienteDao.delete(tCliente2a.getId()))
            System.out.println("OK...... : " + tCliente2a);
        else
            System.out.println("ERRO.... : " + tCliente2a);

        // Remover o Produto
        System.out.println();
        System.out.println("Removendo o Produto");
        if (tProdutoDao.delete(tProduto2a.getId()))
            System.out.println("OK...... : " + tProduto2a);
        else
            System.out.println("ERRO.... : " + tProduto2a);    	
    }
}