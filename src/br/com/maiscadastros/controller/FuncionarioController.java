package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.FuncionarioDao;
import br.com.maiscadastros.dto.FuncionarioDto;
import br.com.maiscadastros.model.Funcionario;

public class FuncionarioController
{
    public FuncionarioDto cadastrarFuncionario(Funcionario pFuncionario)
    {
        // Verificar as informações
        if (pFuncionario == null)
        {
            return new FuncionarioDto(false, "Tentativa de inclusão de funcionario nulo");
        }

        // Criando o objeto de persistência
        FuncionarioDao tDao = new FuncionarioDao();

        // Verificando se o funcionario já existe
        Funcionario tFuncionario = tDao.recoveryByCpf(pFuncionario.getCpf());
        if (tFuncionario != null)
        {
            return new FuncionarioDto(false, "Já existe funcionario com o cpf informado");
        }

        // Incluindo o funcionario
        tFuncionario = tDao.create(pFuncionario);
        if (tFuncionario == null)
        {
            return new FuncionarioDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Funcionario incluído com sucesso", tFuncionario);
    }

    public FuncionarioDto recuperarFuncionario(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new FuncionarioDto(false, "Identificador do Funcionario inválido");
        }

        // Criando o objeto de persistência
        FuncionarioDao tDao = new FuncionarioDao();

        // Recuperando o Funcionario
        Funcionario tFuncionario = tDao.recovery(pId);
        if (tFuncionario == null)
        {
            return new FuncionarioDto(false, "Não existe Funcionario com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Funcionario recuperado com sucesso", tFuncionario);
    }

    public FuncionarioDto atualizarFuncionario(Funcionario pFuncionario)
    {
        // Verificar as informações
        if (pFuncionario == null)
        {
            return new FuncionarioDto(false, "Tentativa de atualização de Funcionario nulo");
        }

        // Criando o objeto de persistência
        FuncionarioDao tDao = new FuncionarioDao();

        // Recuperando o Funcionario
        Funcionario tFuncionario = tDao.recovery(pFuncionario.getId());
        if (tFuncionario == null)
        {
            return new FuncionarioDto(false, "Não existe Funcionario com o identificador informado");
        }

        if (pFuncionario.getCpf() != tFuncionario.getCpf())
        {
            // Verificando se existe um Funcionario com o novo CPF
            tFuncionario = tDao.recoveryByCpf(pFuncionario.getCpf());
            if (tFuncionario != null)
            {
                return new FuncionarioDto(false, "Já existe Funcionario com o cpf informado");
            }
        }
        // Atualizando o Funcionario
        tFuncionario = tDao.update(pFuncionario);
        if (tFuncionario == null)
        {
            return new FuncionarioDto(false, "Não existe Funcionario com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Funcionario alterado com sucesso", tFuncionario);
    }

    public FuncionarioDto removeFuncionario(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new FuncionarioDto(false, "Identificador do Funcionario inválido");
        }

        // Criando o objeto de persistência
        FuncionarioDao tDao = new FuncionarioDao();

        // Incluindo o Funcionario
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new FuncionarioDto(true, "Funcionario removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new FuncionarioDto(false, "Erro no processo de remoção");
    }
    
    public FuncionarioDto pesquisarFuncionariosPorNome(String pNome)
    {
        // Criando a lista de retorno
        List<Funcionario> tLista = new ArrayList<>();

        // Criando o objeto de persistência
        FuncionarioDao tDao = new FuncionarioDao();

        // Recuperando o Funcionario
        tLista = tDao.searchByNome(pNome);

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Lista de Funcionarios recuperada com sucesso", tLista);
    }
    
    public FuncionarioDto pesquisar()
    {
        // Criando a lista de retorno
        List<Funcionario> tLista = new ArrayList<>();

        // Criando o objeto de persistência
        FuncionarioDao tDao = new FuncionarioDao();

        // Recuperando o Funcionario
        tLista = tDao.search();

        // Retornando o indicativo de sucesso
        return new FuncionarioDto(true, "Lista de Funcionarios recuperada com sucesso", tLista);
    }
}

