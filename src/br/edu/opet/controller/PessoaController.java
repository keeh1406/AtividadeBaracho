package br.edu.opet.controller;

import java.util.ArrayList;
import java.util.List;
import br.edu.opet.dao.PessoaDao;
import br.edu.opet.dto.PessoaDto;
import br.edu.opet.model.Pessoa;

public class PessoaController 
{
    public PessoaDto cadastrarPessoa(Pessoa pPessoa)
    {
        // Verificar as informa��es
        if (pPessoa == null)
        {
            return new PessoaDto(false, "Tentativa de inclus�o de pessoa nulo");
        }

        // Criando o objeto de persist�ncia
        PessoaDao tDao = new PessoaDao();

        // Verificando se o pessoa j� existe
        Pessoa tPessoa = tDao.recoveryByCpf(pPessoa.getCpf());
        if (tPessoa != null)
        {
            return new PessoaDto(false, "J� existe um Pessoa com o cpf informado");
        }

        // Incluindo o pessoa
        tPessoa = tDao.create(pPessoa);
        if (tPessoa == null)
        {
            return new PessoaDto(false, "Erro no processo de inclus�o");
        }

        // Retornando o indicativo de sucesso
        return new PessoaDto(true, "Pessoa inclu�do com sucesso", tPessoa);
    }

    public PessoaDto recuperarPessoa(int pId)
    {
        // Verificar as informa��es
        if (pId <=0)
        {
            return new PessoaDto(false, "Identificador do pessoa inv�lido");
        }

        // Criando o objeto de persist�ncia
        PessoaDao tDao = new PessoaDao();

        // Recuperando o Pessoa
        Pessoa tPessoa = tDao.recovery(pId);
        if (tPessoa == null)
        {
            return new PessoaDto(false, "N�o existe pessoa com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new PessoaDto(true, "Pessoa recuperado com sucesso", tPessoa);
    }

    public PessoaDto atualizarPessoa(Pessoa pPessoa)
    {
        // Verificar as informa��es
        if (pPessoa == null)
        {
            return new PessoaDto(false, "Tentativa de atualiza��o de pessoa nulo");
        }

        // Criando o objeto de persist�ncia
        PessoaDao tDao = new PessoaDao();

        // Recuperando o Pessoa
        Pessoa tPessoa = tDao.recovery(pPessoa.getId());
        if (tPessoa == null)
        {
            return new PessoaDto(false, "N�o existe Pessoa com o identificador informado");
        }

        if (pPessoa.getCpf() != tPessoa.getCpf())
        {
            // Verificando se existe um Pessoa com o novo CPF
            tPessoa = tDao.recoveryByCpf(pPessoa.getCpf());
            if (tPessoa != null)
            {
                return new PessoaDto(false, "J� existe Pessoa com o cpf informado");
            }
        }

        // Atualizando o Pessoa
        tPessoa = tDao.update(pPessoa);
        if (tPessoa == null)
        {
            return new PessoaDto(false, "N�o existe pessoa com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new PessoaDto(true, "Pessoa alterado com sucesso", tPessoa);
    }

    public PessoaDto removePessoa(int pId)
    {
        // Verificar as informa��es
        if (pId <=0)
        {
            return new PessoaDto(false, "Identificador do pessoa inv�lido");
        }

        // Criando o objeto de persist�ncia
        PessoaDao tDao = new PessoaDao();

        // Incluindo o Pessoa
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new PessoaDto(true, "Pessoa removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new PessoaDto(false, "Erro no processo de remo��o");
    }

public PessoaDto pesquisarPessoasPorNome(String pNome)
{
    // Criando a lista de retorno
    List<Pessoa> tLista = new ArrayList<>();

    // Criando o objeto de persist�ncia
    PessoaDao tDao = new PessoaDao();

    // Recuperando o Pessoa
    tLista = tDao.searchByNome(pNome);

    // Retornando o indicativo de sucesso
    return new PessoaDto(true, "Lista de Pessoas recuperada com sucesso", tLista);
}

public PessoaDto pesquisarPessoa()
{
    // Criando a lista de retorno
    List<Pessoa> tLista = new ArrayList<>();

    // Criando o objeto de persist�ncia
    PessoaDao tDao = new PessoaDao();

    // Recuperando o Pessoa
    tLista = tDao.search();

    // Retornando o indicativo de sucesso
    return new PessoaDto(true, "Lista de Pessoas recuperada com sucesso", tLista);
}
}
