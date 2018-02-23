package br.com.maiscadastros.teste;

import java.util.List;

import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.model.Loja;

public class TesteLojaDao 
{
    public static void main(String[] pArgs)
    {
        // Criar um Loja
        Loja tLojaA = new Loja(0, "SuperViva", 3456767, "vivamais@gmail.com", 37834534452L, "Acre");
        Loja tLojaB = new Loja(0, "Casa do 10", 3677676, "dezreais@gmail.com", 11111111111L, "Roraima");

        // Criando o objeto de persistência
        LojaDao tDao = new LojaDao();

        // Incluir o Loja
        System.out.println();
        System.out.println("Incluindo");
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

        // Recuperando o Loja
        System.out.println();
        System.out.println("Recuperando");
        Loja tLoja3a = tDao.recovery(tLoja2a.getId());
        if (tLoja3a != null)
            System.out.println("OK...... : " + tLoja3a);
        else
            System.out.println("ERRO.... : " + tLoja3a);
        Loja tLoja3b = tDao.recovery(tLoja2b.getId());
        if (tLoja3b != null)
            System.out.println("OK...... : " + tLoja3b);
        else
            System.out.println("ERRO.... : " + tLoja3b);

        // Atualizando o Loja
        System.out.println();
        System.out.println("Atualizando");
        tLoja2a.setNome(tLoja2a.getNome() + " Baratinhos");
        tLoja2a.setTelefone(985008888);
        tLoja2a.setEmail("baratodeais@outlook.com");
        tLoja2a.setCnpj(12345678901L);
        tLoja2a.setEndereco("Rio de Janeiro");
        Loja tLoja4a = tDao.update(tLoja2a);
        if (tLoja4a != null)
            System.out.println("OK...... : " + tLoja4a);
        else
            System.out.println("ERRO.... : " + tLoja4a);

        tLoja2b.setNome(tLoja2b.getNome() + " Descontos");
        tLoja2b.setTelefone(987886776);
        tLoja2b.setEmail("descontosdemontao@outlook.com");
        tLoja2b.setCnpj(22222222222L);
        tLoja2b.setEndereco("Bahia");
        Loja tLoja4b = tDao.update(tLoja2b);
        if (tLoja4a != null)
            System.out.println("OK...... : " + tLoja4b);
        else
            System.out.println("ERRO.... : " + tLoja4b);

        // Recuperando o Loja
        System.out.println();
        System.out.println("Recuperando");
        Loja tLoja5a = tDao.recovery(tLoja2a.getId());
        if (tLoja5a != null)
            System.out.println("OK...... : " + tLoja5a);
        else
            System.out.println("ERRO.... : " + tLoja5a);
        Loja tLoja5b = tDao.recovery(tLoja2b.getId());
        if (tLoja5b != null)
            System.out.println("OK...... : " + tLoja5b);
        else
            System.out.println("ERRO.... : " + tLoja5b);

        // Listar os Lojas
        List<Loja> tLista = tDao.search();
        System.out.println();
        System.out.println("Pesquisando");
        for (Loja tLoja : tLista)
        {
            System.out.println("OK...... : " + tLoja);
        }

        // Remover o Loja
        System.out.println();
        System.out.println("Removendo");
        if (tDao.delete(tLoja2a.getId()))
            System.out.println("OK...... : " + tLoja2a);
        else
            System.out.println("ERRO.... : " + tLoja2a);
        if (tDao.delete(tLoja2b.getId()))
            System.out.println("OK...... : " + tLoja2b);
        else
            System.out.println("ERRO.... : " + tLoja2b);

        // Verificando se removeu
        System.out.println();
        System.out.println("Verificando a remoção");
        if (tDao.delete(tLoja2a.getId()))
            System.out.println("ERRO.... : " + tLoja2a);
        else
            System.out.println("OK...... : " + tLoja2a);
        if (tDao.delete(tLoja2b.getId()))
            System.out.println("ERRO.... : " + tLoja2b);
        else
            System.out.println("OK...... : " + tLoja2b);
    }
}
