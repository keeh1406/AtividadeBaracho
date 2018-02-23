package br.com.maiscadastros.teste;

import java.time.LocalDate;
import br.com.maiscadastros.controller.ClienteController;
import br.com.maiscadastros.dao.ClienteDao;
import br.com.maiscadastros.dto.ClienteDto;
import br.com.maiscadastros.model.Cliente;

public class TesteClienteController 
{
    public static void main(String[] pArgs)
    {
        //
        // Teste
        //
    	
        // Criar um Cliente
    	Cliente tClienteA = new Cliente(0, "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767, "yerla@gmail.com", "123",37834534452L, "Paraná");
    	Cliente tClienteB = new Cliente(0, "Martinha", LocalDate.of(2002, 6, 4), 3732, "mar@gmail.com","456", 78945678915L, "Ponta Pequena");

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
        
        System.out.println();
        System.out.println("Incluindo o Cliente");
        Cliente tCliente2b = tClienteDao.create(tClienteB);
        if (tCliente2b != null)
            System.out.println("OK...... : " + tCliente2b);
        else
            System.out.println("ERRO.... : " + tCliente2b);

        // Criando o objeto de Controller
        ClienteController tController = new ClienteController();
        
        // Criar o Cliente
        System.out.println();
        System.out.println("Incluindo um Cliente via controller");
        ClienteDto tDto = tController.cadastrarCliente(tClienteB);
        if (tDto.isOk())
        {
        	
         // Recuperando o Cliente incluído para obter o ID gerado
            tClienteB = tDto.getCliente();
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tClienteB);
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Incluindo um Cliente nulo");
        tDto = tController.cadastrarCliente(null);
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
        System.out.println("Recuperando um Cliente via controller");
        tDto = tController.recuperarCliente(tClienteA.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getCliente());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }
        
        System.out.println();
        System.out.println("Recuperando um Cliente via controller");
        tDto = tController.recuperarCliente(tClienteB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getCliente());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando um Cliente com id inválido");
        tDto = tController.recuperarCliente(-32432);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando um Cliente não existente");
        tDto = tController.recuperarCliente(9999999);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Atualizar o Cliente
        tClienteB.setNome(tClienteA.getNome() + " Silvantino");
        tClienteB.setDataNascimento(LocalDate.of(2000, 1, 1));
        tClienteB.setTelefone(998975511);
        tClienteB.setEmail("silvantino@gmail.com");
        tClienteB.setSenha("789");
        tClienteB.setCpf(tCliente2a.getCpf());
        tClienteB.setEndereco("Pará");
        
        tClienteB.setNome(tClienteB.getNome() + " Silvantino");
        tClienteB.setDataNascimento(LocalDate.of(2000, 1, 1));
        tClienteB.setTelefone(998975511);
        tClienteB.setEmail("silvantino@gmail.com");
        tClienteB.setSenha("789");
        tClienteB.setCpf(tCliente2a.getCpf());
        tClienteB.setEndereco("Pará");

        System.out.println();
        System.out.println("Atualizando um Cliente para um cpf que existe");
        tDto = tController.atualizarCliente(tClienteB);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Acertando o CPF para a atualização
        tClienteB.setCpf(88888888888L);

        // Atualizando o Cliente
        System.out.println();
        System.out.println("Atualizando um Cliente via controller");
        tDto = tController.atualizarCliente(tClienteB);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getCliente());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Atualizando um Cliente nulo");
        tDto = tController.atualizarCliente(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        Cliente tClienteC = new Cliente(99999, "nono nono", LocalDate.now(), 1, "nono@gmail", "nono", 1L, "Curitiba");

        System.out.println();
        System.out.println("Atualizando um Cliente que não existe");
        tDto = tController.atualizarCliente(tClienteC);
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
        System.out.println("Recuperando um Cliente via controller");
        tDto = tController.recuperarCliente(tClienteB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getCliente());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Removendo o Cliente
        System.out.println();
        System.out.println("Removendo um Cliente via controller");
        tDto = tController.removeCliente(tClienteB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo um Cliente com id inválido");
        tDto = tController.removeCliente(-4);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo um Cliente com Venda");
        tDto = tController.removeCliente(tClienteA.getId());
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
        
        System.out.println();
        System.out.println("Removendo o Cliente");
        if (tClienteDao.delete(tCliente2b.getId()))
            System.out.println("OK...... : " + tCliente2b);
        else
            System.out.println("ERRO.... : " + tCliente2b);
    
}
}
