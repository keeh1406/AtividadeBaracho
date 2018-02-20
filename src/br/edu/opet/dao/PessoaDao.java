package br.edu.opet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.opet.util.ExceptionUtil;
import br.edu.opet.jdbc.Conexao;
import br.edu.opet.model.Pessoa;

public class PessoaDao 
{
    private String comandoCreate   = "INSERT INTO PESSOA "
            + "(ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, STATUS)"
            + "VALUES (Pessoa_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
private String comandoRecovery = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, STATUS "
            + "FROM PESSOA "
            + "WHERE ID = ?";
private String comandoRecoveryByCpf = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, STATUS "
            + "FROM PESSOA "
            + "WHERE CPF = ?";
private String comandoUpdate   = "UPDATE PESSOA "
            + "SET NOME = ?, "
            + "DATA_NASCIMENTO = ?, "
            + "TELEFONE = ?, "
            + "EMAIL = ?, "
            + "SENHA = ?, "
            + "CPF = ?, "
            + "ENDERECO = ?, "
            + "STATUS = ? "
            + "WHERE ID = ?";
private String comandoDelete   = "DELETE FROM PESSOA "
            + "WHERE ID = ?";
private String comandoSearch   = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, STATUS "
            + "FROM PESSOA";
private String comandoSearchByNome   = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, STATUS "
        + "FROM PESSOA "
		+ "WHERE UPPER(NOME) LIKE UPPER(?)";

public Pessoa create(Pessoa pPessoa)
{
try
{
    // Obter a conexão
    Connection tConexao = Conexao.getConexao();

    // Criar o comando
    PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

    // Preencher o comando
    int i = 1;
    tComandoJdbc.setString(i++, pPessoa.getNome());
    tComandoJdbc.setDate(i++, Date.valueOf(pPessoa.getDataNascimento()));
    tComandoJdbc.setLong(i++, pPessoa.getTelefone());
    tComandoJdbc.setString(i++, pPessoa.getEmail());
    tComandoJdbc.setString(i++, pPessoa.getSenha());
    tComandoJdbc.setLong(i++, pPessoa.getCpf());
    tComandoJdbc.setString(i++, pPessoa.getEndereco());
    tComandoJdbc.setString(i++, pPessoa.getStatus());

    // Executar o comando
    int tQtd = tComandoJdbc.executeUpdate();

    // Processar o resultado
    if (tQtd == 1)
    {
        // Copiando o parametro
        Pessoa tPessoa = pPessoa;

        // Recuperando o código gerado pelo banco de dados
        ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
        tRsChave.next();

        // Assinalar a chave primária gerada no objeto
        pPessoa.setId(tRsChave.getInt(1));

        // Liberar os recursos
        tRsChave.close();
        tComandoJdbc.close();

        // Retornando o objeto inserido
        return tPessoa;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do pessoa");
}

// Retorna null indicando algum erro de processamento
return null;
}

public Pessoa recovery(int pId)
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
        Pessoa tPessoa = recuperarPessoa(tResultSet);

        // Liberar os recursos
        tResultSet.close();
        tComandoJdbc.close();

        // Retornando o objeto inserido
        return tPessoa;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do pessoa");
}

// Retorna null indicando algum erro de processamento
return null;
}

public Pessoa recoveryByCpf(long pCpf)
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
        Pessoa tPessoa = recuperarPessoa(tResultSet);

        // Liberar os recursos
        tResultSet.close();
        tComandoJdbc.close();

        // Retornando o objeto inserido
        return tPessoa;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do pessoa");
}

// Retorna null indicando algum erro de processamento
return null;
}

public Pessoa update(Pessoa pPessoa)
{
try
{
    // Obter a conexão
    Connection tConexao = Conexao.getConexao();

    // Criar o comando
    PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

    // Preencher o comando
    int i = 1;
    tComandoJdbc.setString(i++, pPessoa.getNome());
    tComandoJdbc.setDate(i++, Date.valueOf(pPessoa.getDataNascimento()));
    tComandoJdbc.setLong(i++, pPessoa.getTelefone());
    tComandoJdbc.setString(i++, pPessoa.getEmail());
    tComandoJdbc.setString(i++, pPessoa.getSenha());
    tComandoJdbc.setLong(i++, pPessoa.getCpf());
    tComandoJdbc.setString(i++, pPessoa.getEndereco());
    tComandoJdbc.setString(i++, pPessoa.getStatus());
    tComandoJdbc.setInt(i++, pPessoa.getId());

    // Executar o comando
    int tQtd = tComandoJdbc.executeUpdate();

    // Processar o resultado
    if (tQtd == 1)
    {
        // Copiando o parametro
        Pessoa tPessoa = pPessoa;

        // Liberar os recursos
        tComandoJdbc.close();

        // Retornando o objeto inserido
        return tPessoa;
    }
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do pessoa");
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
    ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do pessoa");
}

// Retorna falso indicando algum erro de processamento
return false;
}

public List<Pessoa> search()
{
List<Pessoa> tLista = new ArrayList<>();

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
        Pessoa tPessoa = recuperarPessoa(tResultSet);

        // Adicionar o o bjeto na lista
        tLista.add(tPessoa);
    }

    // Liberar os recursos
    tResultSet.close();
    tComandoJdbc.close();
}
catch (SQLException tExcept)
{
    ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do pessoa");
}

// Retornando a lista de objetos
return tLista;
}

public List<Pessoa> searchByNome(String pNome)
{
    if (pNome == null || pNome.isEmpty())
        return search();

    List<Pessoa> tLista = new ArrayList<>();

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
            Pessoa tPessoa = recuperarPessoa(tResultSet);

            // Adicionar o o bjeto na lista
            tLista.add(tPessoa);
        }

        // Liberar os recursos
        tResultSet.close();
        tComandoJdbc.close();
    }
    catch (SQLException tExcept)
    {
        ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa dos Pessoas");
    }

    // Retornando a lista de objetos
    return tLista;
}

private Pessoa recuperarPessoa(ResultSet tResultSet) throws SQLException
{
Pessoa tPessoa = new Pessoa();

// Recuperando os dados do resultSet
tPessoa.setId(tResultSet.getInt("ID"));
tPessoa.setNome(tResultSet.getString("NOME"));
tPessoa.setDataNascimento(tResultSet.getDate("DATA_NASCIMENTO").toLocalDate());
tPessoa.setTelefone(tResultSet.getLong("TELEFONE"));
tPessoa.setEmail(tResultSet.getString("EMAIL"));
tPessoa.setSenha(tResultSet.getString("SENHA"));
tPessoa.setCpf(tResultSet.getLong("CPF"));
tPessoa.setEndereco(tResultSet.getString("ENDERECO"));
tPessoa.setStatus(tResultSet.getString("STATUS"));
return tPessoa;
}
}
