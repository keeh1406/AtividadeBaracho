package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.LojaDao;
import br.com.maiscadastros.dto.LojaDto;
import br.com.maiscadastros.model.Loja;

public class LojaController 
{
    public LojaDto cadastrarLoja(Loja pLoja)
    {
        // Verificar as informações
        if (pLoja == null)
        {
            return new LojaDto(false, "Tentativa de inclusão de Loja nulo");
        }

        // Criando o objeto de persistência
        LojaDao tDao = new LojaDao();

        // Verificando se a loja já existe
        Loja tLoja = tDao.recoveryByCnpj(pLoja.getCnpj());
        if (tLoja != null)
        {
            return new LojaDto(false, "Já existe Loja com o cnpj informado");
        }

        // Incluindo a loja
        tLoja = tDao.create(pLoja);
        if (tLoja == null)
        {
            return new LojaDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Loja incluída com sucesso", tLoja);
    }

    public LojaDto recuperarLoja(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new LojaDto(false, "Identificador do Loja inválido");
        }

        // Criando o objeto de persistência
        LojaDao tDao = new LojaDao();

        // Recuperando o Loja
        Loja tLoja = tDao.recovery(pId);
        if (tLoja == null)
        {
            return new LojaDto(false, "Não existe Loja com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Loja recuperado com sucesso", tLoja);
    }

    public LojaDto atualizarLoja(Loja pLoja)
    {
        // Verificar as informações
        if (pLoja == null)
        {
            return new LojaDto(false, "Tentativa de atualização de Loja nulo");
        }

        // Criando o objeto de persistência
        LojaDao tDao = new LojaDao();

        // Recuperando o Loja
        Loja tLoja = tDao.recovery(pLoja.getId());
        if (tLoja == null)
        {
            return new LojaDto(false, "Não existe Loja com o identificador informado");
        }

        if (pLoja.getCnpj() != tLoja.getCnpj())
        {
            // Verificando se existe um Loja com o novo CPF
            tLoja = tDao.recoveryByCnpj(pLoja.getCnpj());
            if (tLoja != null)
            {
                return new LojaDto(false, "Já existe Loja com o cpf informado");
            }
        }

        // Atualizando o Loja
        tLoja = tDao.update(pLoja);
        if (tLoja == null)
        {
            return new LojaDto(false, "Não existe Loja com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Loja alterado com sucesso", tLoja);
    }

    public LojaDto removeLoja(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new LojaDto(false, "Identificador do Loja inválido");
        }

        // Criando o objeto de persistência
        LojaDao tDao = new LojaDao();

        // Incluindo o Loja
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new LojaDto(true, "Loja removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new LojaDto(false, "Erro no processo de remoção");
    }
    public LojaDto pesquisarLojasPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<Loja> tLista = new ArrayList<>();

        // Criando o objeto de persistência
        LojaDao tDao = new LojaDao();

        // Recuperando o Loja
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Lista de Lojas recuperada com sucesso", tLista);
    }
    
    public LojaDto pesquisarLoja()
    {
        // Criando a lista de retorno
        List<Loja> tLista = new ArrayList<>();

        // Criando o objeto de persistência
        LojaDao tDao = new LojaDao();

        // Recuperando o Loja
        tLista = tDao.search();

        // Retornando o indicativo de sucesso
        return new LojaDto(true, "Lista de Lojas recuperada com sucesso", tLista);
    }
}
