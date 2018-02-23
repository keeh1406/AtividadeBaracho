package br.com.maiscadastros.teste;

import java.time.LocalDate;
import br.com.maiscadastros.controller.FuncionarioController;
import br.com.maiscadastros.dao.FuncionarioDao;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.dto.FuncionarioDto;
import br.com.maiscadastros.model.Funcionario;
import br.com.maiscadastros.model.Loja;

public class TesteFuncionarioController 
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
    	
        // Criar um Funcionario
    	Funcionario tFuncionarioA = new Funcionario(0, "Ana Carvalho", LocalDate.of(1978, 8, 29), 753456986, "aninha@gmail,com", "abcde", 12345678912L, "Paraná", false, tLoja2a.getId());
 
        // Criando o objeto de persistência
        FuncionarioDao tFuncionarioDao = new FuncionarioDao();

        // Incluir o Funcionario
        System.out.println();
        System.out.println("Incluindo o Funcionario");
        Funcionario tFuncionario2a = tFuncionarioDao.create(tFuncionarioA);
        if (tFuncionario2a != null)
            System.out.println("OK...... : " + tFuncionario2a);
        else
            System.out.println("ERRO.... : " + tFuncionario2a);

        //
        // Teste
        //

        // Criando o objeto de Controller
        FuncionarioController tController = new FuncionarioController();

        // Criar um Funcionario
        Funcionario tFuncionarioB = new Funcionario(0, "Amilton", LocalDate.of(1998, 8, 29), 987654321, "amilton@gmail,com", "efghi",98765432175L, "Portugal", true, tLoja2a.getId());
        
        // Criar o Funcionario
        System.out.println();
        System.out.println("Incluindo um Funcionario via controller");
        FuncionarioDto tDto = tController.cadastrarFuncionario(tFuncionarioB);
        if (tDto.isOk())
        {
            // Recuperando o Funcionario incluído para obter o ID gerado
            tFuncionarioB = tDto.getFuncionario();
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tFuncionarioB);
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Incluindo um Funcionario nulo");
        tDto = tController.cadastrarFuncionario(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar o Funcionario
        System.out.println();
        System.out.println("Recuperando um Funcionario via controller");
        tDto = tController.recuperarFuncionario(tFuncionarioB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getFuncionario());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando um Funcionario com id inválido");
        tDto = tController.recuperarFuncionario(-32432);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Recuperando um Funcionario não existente");
        tDto = tController.recuperarFuncionario(9999999);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Atualizar o Funcionario
        tFuncionarioB.setNome(tFuncionarioB.getNome() + " Silvantino");
        tFuncionarioB.setDataNascimento(LocalDate.of(1978, 7, 29));
        tFuncionarioB.setTelefone(998975511);
        tFuncionarioB.setEmail("silvantino@gmail.com");
        tFuncionarioB.setCpf(tFuncionario2a.getCpf());
        tFuncionarioB.setEndereco("Pará");
        tFuncionarioB.setFlGerente(false);

        System.out.println();
        System.out.println("Atualizando um Funcionario para um cpf que existe");
        tDto = tController.atualizarFuncionario(tFuncionarioB);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Acertando o CPF para a atualização
        tFuncionarioB.setCpf(88888888888L);

        // Atualizando o Funcionario
        System.out.println();
        System.out.println("Atualizando um Funcionario via controller");
        tDto = tController.atualizarFuncionario(tFuncionarioB);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getFuncionario());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Atualizando um Funcionario nulo");
        tDto = tController.atualizarFuncionario(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        Funcionario tFuncionarioC = new Funcionario(99999, "nono nono", LocalDate.now(), 1, "nono@gmail","nono", 1L, "Curitiba", true, tLoja2a.getId());

        System.out.println();
        System.out.println("Atualizando um Funcionario que não existe");
        tDto = tController.atualizarFuncionario(tFuncionarioC);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Recuperar o Funcionario
        System.out.println();
        System.out.println("Recuperando um Funcionario via controller");
        tDto = tController.recuperarFuncionario(tFuncionarioB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getFuncionario());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Removendo o Funcionario
        System.out.println();
        System.out.println("Removendo um Funcionario via controller");
        tDto = tController.removeFuncionario(tFuncionarioB.getId());
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo um Funcionario com id inválido");
        tDto = tController.removeFuncionario(-4);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        System.out.println();
        System.out.println("Removendo um Funcionario com Venda");
        tDto = tController.removeFuncionario(tFuncionarioA.getId());
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
