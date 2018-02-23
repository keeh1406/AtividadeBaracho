package br.com.maiscadastros.teste;

import br.com.maiscadastros.controller.LojaController;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.dto.LojaDto;
import br.com.maiscadastros.model.Loja;

public class TesteLojaController 
{
    public static void main(String[] pArgs)
    {
        //
        // Pré Teste
        //
        // Criar um Loja
        Loja tLojaA = new Loja(0, "Moda Feminina", 3456767, "mf@gmail.com", 37834534452L, "Rio Grande do Sul");

        // Criando o objeto de persistência
        LojaDao tDao = new LojaDao();

        // Incluir o Loja
        System.out.println();
        System.out.println("Incluindo um Loja");
        Loja tLoja2a = tDao.create(tLojaA);
        if (tLoja2a != null)
            System.out.println("OK...... : " + tLoja2a);
        else
            System.out.println("ERRO.... : " + tLoja2a);

        //
        // Teste
        //
        // Criar um Loja
        Loja tLojaB = new Loja(0, "Moda Masculina", 3677676, "mm@gmail.com", 11111111111L, "Brasília");

        // Criando o objeto de Controller
        LojaController tController = new LojaController();

        // Incluindo o Loja
        System.out.println();
        System.out.println("Incluindo um Loja via controller");
        LojaDto tDto = tController.cadastrarLoja(tLojaB);
        if (tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
            System.out.println("           " + tDto.getLoja());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Incluindo o Loja
        System.out.println();
        System.out.println("Incluindo um Loja nulo");
        tDto = tController.cadastrarLoja(null);
        if (!tDto.isOk())
        {
            System.out.println("OK...... : " + tDto.getMensagem());
        }
        else
        {
            System.out.println("ERRO.... : " + tDto.getMensagem());
        }

        // Incluindo o Loja
        System.out.println();
        System.out.println("Incluindo um Loja já existente");
        tDto = tController.cadastrarLoja(tLojaA);
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
        // Remover o Loja
        System.out.println();
        System.out.println("Removendo um Loja");
        if (tDao.delete(tLoja2a.getId()))
            System.out.println("OK...... : " + tLoja2a);
        else
            System.out.println("ERRO.... : " + tLoja2a);
    }
}
