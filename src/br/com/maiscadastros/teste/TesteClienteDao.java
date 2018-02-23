package br.com.maiscadastros.teste;

import java.time.LocalDate;
import java.util.List;
import br.com.maiscadastros.dao.ClienteDao;
import br.com.maiscadastros.model.Cliente;

public class TesteClienteDao
{
    public static void main(String[] pArgs)
    {
        // Criar um Cliente
        Cliente tClienteA = new Cliente(0, "Yerlandia Westrocia", LocalDate.of(1978, 8, 29), 3456767, "yerla@gmail.com", "159", 37834534452L, "Paraná");
        Cliente tClienteB = new Cliente(0, "Restronco Geudulto", LocalDate.of(1999, 8, 29), 3677676, "restro@gmail.com", "753",11111111111L, "Santa Catarina");

        // Criando o objeto de persistência
        ClienteDao tDao = new ClienteDao();

        // Incluir o Cliente
        System.out.println();
        System.out.println("Incluindo");
        Cliente tCliente2a = tDao.create(tClienteA);
        if (tCliente2a != null)
            System.out.println("OK...... : " + tCliente2a);
        else
            System.out.println("ERRO.... : " + tCliente2a);
        Cliente tCliente2b = tDao.create(tClienteB);
        if (tCliente2b != null)
            System.out.println("OK...... : " + tCliente2b);
        else
            System.out.println("ERRO.... : " + tCliente2b);

        // Recuperando o Cliente
        System.out.println();
        System.out.println("Recuperando");
        Cliente tCliente3a = tDao.recovery(tCliente2a.getId());
        if (tCliente3a != null)
            System.out.println("OK...... : " + tCliente3a);
        else
            System.out.println("ERRO.... : " + tCliente3a);
        Cliente tCliente3b = tDao.recovery(tCliente2b.getId());
        if (tCliente3b != null)
            System.out.println("OK...... : " + tCliente3b);
        else
            System.out.println("ERRO.... : " + tCliente3b);

        // Atualizando o Cliente
        System.out.println();
        System.out.println("Atualizando");
        tCliente2a.setNome(tCliente2a.getNome() + " Sildacio");
        tCliente2a.setDataNascimento(LocalDate.of(1998, 8, 29));
        tCliente2a.setTelefone(985008888);
        tCliente2a.setEmail("silda@outlook.com");
        tCliente2a.setSenha("357");
        tCliente2a.setCpf(12345678901L);
        tCliente2a.setEndereco("Rio de Janeiro");
        Cliente tCliente4a = tDao.update(tCliente2a);
        if (tCliente4a != null)
            System.out.println("OK...... : " + tCliente4a);
        else
            System.out.println("ERRO.... : " + tCliente4a);

        tCliente2b.setNome(tCliente2b.getNome() + " Sundreco");
        tCliente2b.setDataNascimento(LocalDate.of(1988, 8, 29));
        tCliente2b.setTelefone(987886776);
        tCliente2b.setEmail("troncio@outlook.com");
        tCliente2b.setSenha("7861");
        tCliente2b.setCpf(22222222222L);
        tCliente2b.setEndereco("Bahia");
        Cliente tCliente4b = tDao.update(tCliente2b);
        if (tCliente4a != null)
            System.out.println("OK...... : " + tCliente4b);
        else
            System.out.println("ERRO.... : " + tCliente4b);

        // Recuperando o Cliente
        System.out.println();
        System.out.println("Recuperando");
        Cliente tCliente5a = tDao.recovery(tCliente2a.getId());
        if (tCliente5a != null)
            System.out.println("OK...... : " + tCliente5a);
        else
            System.out.println("ERRO.... : " + tCliente5a);
        Cliente tCliente5b = tDao.recovery(tCliente2b.getId());
        if (tCliente5b != null)
            System.out.println("OK...... : " + tCliente5b);
        else
            System.out.println("ERRO.... : " + tCliente5b);

        // Listar os Clientes
        List<Cliente> tLista = tDao.search();
        System.out.println();
        System.out.println("Pesquisando");
        for (Cliente tCliente : tLista)
        {
            System.out.println("OK...... : " + tCliente);
        }

        // Remover o Cliente
        System.out.println();
        System.out.println("Removendo");
        if (tDao.delete(tCliente2a.getId()))
            System.out.println("OK...... : " + tCliente2a);
        else
            System.out.println("ERRO.... : " + tCliente2a);
        if (tDao.delete(tCliente2b.getId()))
            System.out.println("OK...... : " + tCliente2b);
        else
            System.out.println("ERRO.... : " + tCliente2b);

        // Verificando se removeu
        System.out.println();
        System.out.println("Verificando a remoção");
        if (tDao.delete(tCliente2a.getId()))
            System.out.println("ERRO.... : " + tCliente2a);
        else
            System.out.println("OK...... : " + tCliente2a);
        if (tDao.delete(tCliente2b.getId()))
            System.out.println("ERRO.... : " + tCliente2b);
        else
            System.out.println("OK...... : " + tCliente2b);
    }
}
