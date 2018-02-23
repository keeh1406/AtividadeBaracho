package br.com.maiscadastros.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.dao.ClienteDao;
import br.com.maiscadastros.dto.ClienteDto;
import br.com.maiscadastros.model.Cliente;

public class ClienteController 
{
    public ClienteDto cadastrarCliente(Cliente pCliente)
    {
        // Verificar as informações
        if (pCliente == null)
        {
            return new ClienteDto(false, "Tentativa de inclusão de cliente nulo");
        }

        // Criando o objeto de persistência
        ClienteDao tDao = new ClienteDao();

        // Verificando se o cliente já existe
        Cliente tCliente = tDao.recoveryByCpf(pCliente.getCpf());
        if (tCliente != null)
        {
            return new ClienteDto(false, "Já existe um Cliente com o cpf informado");
        }

        // Incluindo o cliente
        tCliente = tDao.create(pCliente);
        if (tCliente == null)
        {
            return new ClienteDto(false, "Erro no processo de inclusão");
        }

        // Retornando o indicativo de sucesso
        return new ClienteDto(true, "Cliente incluído com sucesso", tCliente);
    }

    public ClienteDto recuperarCliente(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new ClienteDto(false, "Identificador do cliente inválido");
        }

        // Criando o objeto de persistência
        ClienteDao tDao = new ClienteDao();

        // Recuperando o Cliente
        Cliente tCliente = tDao.recovery(pId);
        if (tCliente == null)
        {
            return new ClienteDto(false, "Não existe cliente com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new ClienteDto(true, "Cliente recuperado com sucesso", tCliente);
    }

    public ClienteDto atualizarCliente(Cliente pCliente)
    {
        // Verificar as informações
        if (pCliente == null)
        {
            return new ClienteDto(false, "Tentativa de atualização de cliente nulo");
        }

        // Criando o objeto de persistência
        ClienteDao tDao = new ClienteDao();

        // Recuperando o Cliente
        Cliente tCliente = tDao.recovery(pCliente.getId());
        if (tCliente == null)
        {
            return new ClienteDto(false, "Não existe Cliente com o identificador informado");
        }

        if (pCliente.getCpf() != tCliente.getCpf())
        {
            // Verificando se existe um Cliente com o novo CPF
            tCliente = tDao.recoveryByCpf(pCliente.getCpf());
            if (tCliente != null)
            {
                return new ClienteDto(false, "Já existe Cliente com o cpf informado");
            }
        }

        // Atualizando o Cliente
        tCliente = tDao.update(pCliente);
        if (tCliente == null)
        {
            return new ClienteDto(false, "Não existe cliente com o identificador informado");
        }

        // Retornando o indicativo de sucesso
        return new ClienteDto(true, "Cliente alterado com sucesso", tCliente);
    }

    public ClienteDto removeCliente(int pId)
    {
        // Verificar as informações
        if (pId <=0)
        {
            return new ClienteDto(false, "Identificador do cliente inválido");
        }

        // Criando o objeto de persistência
        ClienteDao tDao = new ClienteDao();

        // Incluindo o Cliente
        if (tDao.delete(pId))
        {
            // Retornando o indicativo de sucesso
            return new ClienteDto(true, "Cliente removido com sucesso");
        }

        // Retornando o indicativo de erro
        return new ClienteDto(false, "Erro no processo de remoção");
    }

public ClienteDto pesquisarClientesPorNome(String pNome)
{
    // Criando a lista de retorno
    List<Cliente> tLista = new ArrayList<>();

    // Criando o objeto de persistência
    ClienteDao tDao = new ClienteDao();

    // Recuperando o Cliente
    tLista = tDao.searchByNome(pNome);

    // Retornando o indicativo de sucesso
    return new ClienteDto(true, "Lista de Clientes recuperada com sucesso", tLista);
}

public ClienteDto pesquisarCliente()
{
    // Criando a lista de retorno
    List<Cliente> tLista = new ArrayList<>();

    // Criando o objeto de persistência
    ClienteDao tDao = new ClienteDao();

    // Recuperando o Cliente
    tLista = tDao.search();

    // Retornando o indicativo de sucesso
    return new ClienteDto(true, "Lista de Clientes recuperada com sucesso", tLista);
}
}
