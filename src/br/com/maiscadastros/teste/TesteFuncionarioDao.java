package br.com.maiscadastros.teste;

import java.time.LocalDate;
import java.util.List;

import br.com.maiscadastros.dao.FuncionarioDao;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.model.Funcionario;
import br.com.maiscadastros.model.Loja;

public class TesteFuncionarioDao 
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
        // Criar uma Funcionario
        
        Funcionario tFuncionarioA = new Funcionario(0, "Ana Carvalho", LocalDate.of(2010, 6, 7), 753456986, "aninha@gmail,com", "jklmn",12345678912L, "Paraná", false, tLoja2a.getId());
        Funcionario tFuncionarioB = new Funcionario(0, "Amilton", LocalDate.of(2010, 10, 1), 987654321, "amilton@gmail,com","opqrs", 98765432175L, "Portugal", true, tLoja2b.getId());

        // Criando o objeto de persistência
        FuncionarioDao tDao = new FuncionarioDao();

        // Incluir a Funcionario
        System.out.println();
        System.out.println("Incluindo");
        Funcionario tFuncionario2a = tDao.create(tFuncionarioA);
        if (tFuncionario2a != null)
            System.out.println("OK...... : " + tFuncionario2a);
        else
            System.out.println("ERRO.... : " + tFuncionario2a);
        Funcionario tFuncionario2b = tDao.create(tFuncionarioB);
        if (tFuncionario2b != null)
            System.out.println("OK...... : " + tFuncionario2b);
        else
            System.out.println("ERRO.... : " + tFuncionario2b);

        // Recuperando a Funcionario
        System.out.println();
        System.out.println("Recuperando");
        Funcionario tFuncionario3a = tDao.recovery(tFuncionario2a.getId());
        if (tFuncionario3a != null)
            System.out.println("OK...... : " + tFuncionario3a);
        else
            System.out.println("ERRO.... : " + tFuncionario3a);
        Funcionario tFuncionario3b = tDao.recovery(tFuncionario2b.getId());
        if (tFuncionario3b != null)
            System.out.println("OK...... : " + tFuncionario3b);
        else
            System.out.println("ERRO.... : " + tFuncionario3b);

        // Atualizando a Funcionario
        System.out.println();
        System.out.println("Atualizando");
        tFuncionario2a.setNome("Ana Nascimento");
        tFuncionario2a.setDataNascimento(LocalDate.of(2009, 6, 4));
        tFuncionario2a.setTelefone(753159654);
        tFuncionario2a.setEmail("ana@gmail.com");
        tFuncionario2a.setCpf(75315965482L);
        tFuncionario2a.setEndereco("Dublin");
        tFuncionario2a.setFlGerente(true);
        tFuncionario2a.setIdLoja(tLoja2b.getId());
        Funcionario tFuncionario4a = tDao.update(tFuncionario2a);
        if (tFuncionario4a != null)
            System.out.println("OK...... : " + tFuncionario4a);
        else
            System.out.println("ERRO.... : " + tFuncionario4a);
        tFuncionario2b.setNome("Amilton Neves");
        tFuncionario2b.setDataNascimento(LocalDate.of(2005, 6, 4));
        tFuncionario2b.setTelefone(357258654);
        tFuncionario2b.setEmail("amiltinho@gmail.com");
        tFuncionario2b.setCpf(85245695175L);
        tFuncionario2b.setEndereco("França");
        tFuncionario2b.setFlGerente(false);
        Funcionario tFuncionario4b = tDao.update(tFuncionario2b);
        if (tFuncionario4a != null)
            System.out.println("OK...... : " + tFuncionario4b);
        else
            System.out.println("ERRO.... : " + tFuncionario4b);

        // Recuperando a Funcionario
        System.out.println();
        System.out.println("Recuperando");
        Funcionario tFuncionario5a = tDao.recovery(tFuncionario2a.getId());
        if (tFuncionario5a != null)
            System.out.println("OK...... : " + tFuncionario5a);
        else
            System.out.println("ERRO.... : " + tFuncionario5a);
        Funcionario tFuncionario5b = tDao.recovery(tFuncionario2b.getId());
        if (tFuncionario5b != null)
            System.out.println("OK...... : " + tFuncionario5b);
        else
            System.out.println("ERRO.... : " + tFuncionario5b);

        // Listar as Funcionarios
        List<Funcionario> tLista = tDao.search();
        System.out.println();
        System.out.println("Pesquisando");
        for (Funcionario tFuncionario : tLista)
        {
            System.out.println("OK...... : " + tFuncionario);
        }

        // Listar as Funcionarios
        tLista = tDao.searchByIdLoja(tLoja2a.getId());
        System.out.println();
        System.out.println("Pesquisando por Loja");
        for (Funcionario tFuncionario : tLista)
        {
            System.out.println("OK...... : " + tFuncionario);
        }

        // Contar as Funcionarios
        int tQtde = tDao.countByLoja(tLoja2a.getId());
        System.out.println();
        System.out.println("Contando Funcionarios por Loja");
        System.out.println("OK...... : " + tQtde);

        // Remover a Funcionario
        System.out.println();
        System.out.println("Removendo");
        if (tDao.delete(tFuncionario2a.getId()))
            System.out.println("OK...... : " + tFuncionario2a);
        else
            System.out.println("ERRO.... : " + tFuncionario2a);
        if (tDao.delete(tFuncionario2b.getId()))
            System.out.println("OK...... : " + tFuncionario2b);
        else
            System.out.println("ERRO.... : " + tFuncionario2b);

        // Verificando se removeu
        System.out.println();
        System.out.println("Verificando a remoção");
        if (tDao.delete(tFuncionario2a.getId()))
            System.out.println("ERRO.... : " + tFuncionario2a);
        else
            System.out.println("OK...... : " + tFuncionario2a);
        if (tDao.delete(tFuncionario2b.getId()))
            System.out.println("ERRO.... : " + tFuncionario2b);
        else
            System.out.println("OK...... : " + tFuncionario2b);

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
