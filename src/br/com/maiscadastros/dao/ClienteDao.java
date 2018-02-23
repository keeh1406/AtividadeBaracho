package br.com.maiscadastros.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.jdbc.Conexao;
import br.com.maiscadastros.model.Cliente;
import br.com.maiscadastros.util.ExceptionUtil;

public class ClienteDao 
{
    private String comandoCreate   = "INSERT INTO CLIENTE "
            + "(ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO)"
            + "VALUES (Cliente_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
private String comandoRecovery = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO "
            + "FROM CLIENTE "
            + "WHERE ID = ?";
private String comandoRecoveryByCpf = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO "
            + "FROM CLIENTE "
            + "WHERE CPF = ?";
private String comandoUpdate   = "UPDATE CLIENTE "
            + "SET NOME = ?, "
            + "DATA_NASCIMENTO = ?, "
            + "TELEFONE = ?, "
            + "EMAIL = ?, "
            + "SENHA = ?, "
            + "CPF = ?, "
            + "ENDERECO = ? "
            + "WHERE ID = ?";
private String comandoDelete   = "DELETE FROM CLIENTE "
            + "WHERE ID = ?";
private String comandoSearch   = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO "
            + "FROM CLIENTE";
private String comandoSearchByNome   = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO "
        + "FROM CLIENTE "
		+ "WHERE UPPER(NOME) LIKE UPPER(?)";

public Cliente create(Cliente pCliente)
{
try
{
    // Obter a conexão
    Connection tConexao = Conexao.getConexao();

    // Criar o comando
    PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

    // Preencher o comando
    int i = 1;
    tComandoJdbc.setString(i++, pCliente.getNome());
    tComandoJdbc.setDate(i++, Date.valueOf(pCliente.getDataNascimento()));
    tComandoJdbc.setLong(i++, pCliente.getTelefone());
    tComandoJdbc.setString(i++, pCliente.getEmail());
    tComandoJdbc.setString(i++, pCliente.getSenha());
    tComandoJdbc.setLong(i++, pCliente.getCpf());
    tComandoJdbc.setString(i++, pCliente.getEndereco());

    // Executar o comando
    int tQtd = tComandoJdbc.executeUpdate();

    // Processar o resultado
    if (tQtd == 1)
    {
        // Copiando o parametro
        Cliente tCliente = pCliente;

        // Recuperando o código gerado pelo banco de dados
        ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
        tRsChave.next();

        // Assinalar a chave primária gerada no objeto
        pCliente.setId(tRsChave.getInt(1));

        // Liberar os recursos
        tRsChave.close();
        tComandoJdbc.close();

        // Retornando o objeto inserido
        return tCliente;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do cliente");
}

// Retorna null indicando algum erro de processamento
return null;
}

public Cliente recovery(int pId)
{
try
{
    // Obter a conexão
    Connection tConexao = Conexao.getConexao();

    // Criar o comando
    PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecovery);

    // Preencher o comando
    int i = 1;
    tComandoJdbc.setInt(i++, pId);

    // Executar o comando
    ResultSet tResultSet = tComandoJdbc.executeQuery();

    // Processar o resultado
    if (tResultSet.next())
    {
        // Criando o objeto
        Cliente tCliente = recuperarCliente(tResultSet);

        // Liberar os recursos
        tResultSet.close();
        tComandoJdbc.close();

        // Retornando o objeto inserido
        return tCliente;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do cliente");
}

// Retorna null indicando algum erro de processamento
return null;
}

public Cliente recoveryByCpf(long pCpf)
{
try
{
    // Obter a conexão
    Connection tConexao = Conexao.getConexao();

    // Criar o comando
    PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecoveryByCpf);

    // Preencher o comando
    int i = 1;
    tComandoJdbc.setLong(i++, pCpf);

    // Executar o comando
    ResultSet tResultSet = tComandoJdbc.executeQuery();

    // Processar o resultado
    if (tResultSet.next())
    {
        // Criando o objeto
        Cliente tCliente = recuperarCliente(tResultSet);

        // Liberar os recursos
        tResultSet.close();
        tComandoJdbc.close();

        // Retornando o objeto inserido
        return tCliente;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do cliente");
}

// Retorna null indicando algum erro de processamento
return null;
}

public Cliente update(Cliente pCliente)
{
try
{
    // Obter a conexão
    Connection tConexao = Conexao.getConexao();

    // Criar o comando
    PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

    // Preencher o comando
    int i = 1;
    tComandoJdbc.setString(i++, pCliente.getNome());
    tComandoJdbc.setDate(i++, Date.valueOf(pCliente.getDataNascimento()));
    tComandoJdbc.setLong(i++, pCliente.getTelefone());
    tComandoJdbc.setString(i++, pCliente.getEmail());
    tComandoJdbc.setString(i++, pCliente.getSenha());
    tComandoJdbc.setLong(i++, pCliente.getCpf());
    tComandoJdbc.setString(i++, pCliente.getEndereco());
    tComandoJdbc.setInt(i++, pCliente.getId());

    // Executar o comando
    int tQtd = tComandoJdbc.executeUpdate();

    // Processar o resultado
    if (tQtd == 1)
    {
        // Copiando o parametro
        Cliente tCliente = pCliente;

        // Liberar os recursos
        tComandoJdbc.close();

        // Retornando o objeto inserido
        return tCliente;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do cliente");
}

// Retorna null indicando algum erro de processamento
return null;
}

public boolean delete(int pId)
{
try
{
    // Obter a conexão
    Connection tConexao = Conexao.getConexao();

    // Criar o comando
    PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoDelete);

    // Preencher o comando
    int i = 1;
    tComandoJdbc.setInt(i++, pId);

    // Executar o comando
    int tQtd = tComandoJdbc.executeUpdate();

    // Processar o resultado
    if (tQtd == 1)
    {
        // Liberar os recursos
        tComandoJdbc.close();

        // Retornando o indicativo de sucesso
        return true;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do cliente");
}

// Retorna falso indicando algum erro de processamento
return false;
}

public List<Cliente> search()
{
List<Cliente> tLista = new ArrayList<>();

try
{
    // Obter a conexão
    Connection tConexao = Conexao.getConexao();

    // Criar o comando
    PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearch);

    // Executar o comando
    ResultSet tResultSet = tComandoJdbc.executeQuery();

    // Processar o resultado
    while (tResultSet.next())
    {
        Cliente tCliente = recuperarCliente(tResultSet);

        // Adicionar o o bjeto na lista
        tLista.add(tCliente);
    }

    // Liberar os recursos
    tResultSet.close();
    tComandoJdbc.close();
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do cliente");
}

// Retornando a lista de objetos
return tLista;
}

public List<Cliente> searchByNome(String pNome)
{
    if (pNome == null || pNome.isEmpty())
        return search();

    List<Cliente> tLista = new ArrayList<>();

    try
    {
        // Preparando o nome para pesquisa
        String tNome = "%" + pNome + "%";

        // Obter a conexão
        Connection tConexao = Conexao.getConexao();

        // Criar o comando
        PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByNome);

        // Preencher o comando
        int i = 1;
        tComandoJdbc.setString(i++, tNome);

        // Executar o comando
        ResultSet tResultSet = tComandoJdbc.executeQuery();

        // Processar o resultado
        while (tResultSet.next())
        {
            Cliente tCliente = recuperarCliente(tResultSet);

            // Adicionar o o bjeto na lista
            tLista.add(tCliente);
        }

        // Liberar os recursos
        tResultSet.close();
        tComandoJdbc.close();
    }
    catch (SQLException tExcept)
    {
        ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa dos Clientes");
    }

    // Retornando a lista de objetos
    return tLista;
}

private Cliente recuperarCliente(ResultSet tResultSet) throws SQLException
{
Cliente tCliente = new Cliente();

// Recuperando os dados do resultSet
tCliente.setId(tResultSet.getInt("ID"));
tCliente.setNome(tResultSet.getString("NOME"));
tCliente.setDataNascimento(tResultSet.getDate("DATA_NASCIMENTO").toLocalDate());
tCliente.setTelefone(tResultSet.getLong("TELEFONE"));
tCliente.setEmail(tResultSet.getString("EMAIL"));
tCliente.setSenha(tResultSet.getString("SENHA"));
tCliente.setCpf(tResultSet.getLong("CPF"));
tCliente.setEndereco(tResultSet.getString("ENDERECO"));
return tCliente;
}
}
