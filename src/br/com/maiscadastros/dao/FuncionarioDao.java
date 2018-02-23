package br.com.maiscadastros.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.jdbc.Conexao;
import br.com.maiscadastros.model.Funcionario;
import br.com.maiscadastros.util.ExceptionUtil;

public class FuncionarioDao
{
    private String comandoCreate   = "INSERT INTO FUNCIONARIO "
                    + "(ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, FLGERENTE, ID_LOJA)"
                    + "VALUES (Loja_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String comandoRecovery = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, FLGERENTE, ID_LOJA "
                    + "FROM FUNCIONARIO "
                    + "WHERE ID = ?";
    private String comandoRecoveryByCpf = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, FLGERENTE, ID_LOJA "
                    + "FROM FUNCIONARIO "
                    + "WHERE CPF = ?";
    private String comandoUpdate   = "UPDATE FUNCIONARIO "
                    + "SET NOME = ?, "
                    + "DATA_NASCIMENTO = ?, "
                    + "TELEFONE = ?, "
                    + "EMAIL = ?, "
                    + "SENHA = ?, "
                    + "CPF = ?, "
                    + "ENDERECO = ?, "
                    + "FLGERENTE = ?, "
                    + "ID_LOJA = ? "
                    + "WHERE ID = ?";
    private String comandoDelete   = "DELETE FROM FUNCIONARIO "
                    + "WHERE ID = ?";
    private String comandoSearch   = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, FLGERENTE, ID_LOJA "
                    + "FROM FUNCIONARIO";
    private String comandoSearchByLoja   = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, FLGERENTE, ID_LOJA "
                    + "FROM FUNCIONARIO "
                    + "WHERE ID_LOJA = ?";
    private String comandoSearchByNome   = "SELECT ID, NOME, DATA_NASCIMENTO, TELEFONE, EMAIL, SENHA, CPF, ENDERECO, FLGERENTE, ID_LOJA "
            + "FROM FUNCIONARIO "
    		+ "WHERE UPPER(NOME) LIKE UPPER(?)";
    private String comandoCountByLoja   = "SELECT COUNT(ID_LOJA) "
                    + "FROM FUNCIONARIO "
                    + "WHERE ID_LOJA = ?";

    public Funcionario create(Funcionario pFuncionario)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pFuncionario.getNome());
            tComandoJdbc.setDate(i++, Date.valueOf(pFuncionario.getDataNascimento()));
            tComandoJdbc.setLong(i++, pFuncionario.getTelefone());
            tComandoJdbc.setString(i++, pFuncionario.getEmail());
            tComandoJdbc.setString(i++, pFuncionario.getSenha());
            tComandoJdbc.setLong(i++, pFuncionario.getCpf());
            tComandoJdbc.setString(i++, pFuncionario.getEndereco());
            tComandoJdbc.setBoolean(i++, pFuncionario.getFlGerente());
            tComandoJdbc.setInt(i++, pFuncionario.getIdLoja());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Funcionario tFuncionario = pFuncionario;

                // Recuperando o código gerado pelo banco de dados
                ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
                tRsChave.next();

                // Assinalar a chave primária gerada no objeto
                pFuncionario.setId(tRsChave.getInt(1));

                // Liberar os recursos
                tRsChave.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tFuncionario;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do funcionário");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Funcionario recovery(int pId)
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
                Funcionario tFuncionario = recuperarFuncionario(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tFuncionario;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do funcionário");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Funcionario recoveryByCpf(long pCpf)
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
                Funcionario tFuncionario = recuperarFuncionario(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tFuncionario;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do funcionário");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Funcionario update(Funcionario pFuncionario)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pFuncionario.getNome());
            tComandoJdbc.setDate(i++, Date.valueOf(pFuncionario.getDataNascimento()));
            tComandoJdbc.setLong(i++, pFuncionario.getTelefone());
            tComandoJdbc.setString(i++, pFuncionario.getEmail());
            tComandoJdbc.setString(i++, pFuncionario.getSenha());
            tComandoJdbc.setLong(i++, pFuncionario.getCpf());
            tComandoJdbc.setString(i++, pFuncionario.getEndereco());
            tComandoJdbc.setBoolean(i++, pFuncionario.getFlGerente());
            tComandoJdbc.setInt(i++, pFuncionario.getIdLoja());
            tComandoJdbc.setInt(i++, pFuncionario.getId());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Funcionario tFuncionario = pFuncionario;

                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tFuncionario;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do funcionário");
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
            ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção do funcionário");
        }

        // Retorna falso indicando algum erro de processamento
        return false;
    }

    public List<Funcionario> search()
    {
        List<Funcionario> tLista = new ArrayList<>();

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
                Funcionario tFuncionario = recuperarFuncionario(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tFuncionario);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do Funcionário");
        }

        // Retornando a lista de objetos
        return tLista;
    }
    
    public int countByLoja(int pIdLoja)
    {
        int tQtde = 0;

        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCountByLoja);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pIdLoja);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            tResultSet.next();
            tQtde = tResultSet.getInt(1);

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();

        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do funcionário");
        }

        // Retornando a lista de objetos
        return tQtde;
    }
    
    public List<Funcionario> searchByIdLoja(int pIdLoja)
    {
        List<Funcionario> tLista = new ArrayList<>();

        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByLoja);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pIdLoja);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Funcionario tFuncionario = recuperarFuncionario(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tFuncionario);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do Funcionário");
        }

        // Retornando a lista de objetos
        return tLista;
    }   
    
    public List<Funcionario> searchByNome(String pNome)
    {
        if (pNome == null || pNome.isEmpty())
            return search();

        List<Funcionario> tLista = new ArrayList<>();

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
                Funcionario tFuncionario = recuperarFuncionario(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tFuncionario);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa dos Funcionarios");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    private Funcionario recuperarFuncionario(ResultSet tResultSet) throws SQLException
    {
        Funcionario tFuncionario = new Funcionario();

        // Recuperando os dados do resultSet
        tFuncionario.setId(tResultSet.getInt("ID"));
        tFuncionario.setNome(tResultSet.getString("NOME"));
        tFuncionario.setDataNascimento(tResultSet.getDate("DATA_NASCIMENTO").toLocalDate());
        tFuncionario.setTelefone(tResultSet.getLong("TELEFONE"));
        tFuncionario.setEmail(tResultSet.getString("EMAIL"));
        tFuncionario.setSenha(tResultSet.getString("SENHA"));
        tFuncionario.setCpf(tResultSet.getLong("CPF"));
        tFuncionario.setEndereco(tResultSet.getString("ENDERECO"));
        tFuncionario.setFlGerente(tResultSet.getBoolean("FLGERENTE"));
        tFuncionario.setIdLoja(tResultSet.getInt("ID_LOJA"));
        return tFuncionario;
    }
}
