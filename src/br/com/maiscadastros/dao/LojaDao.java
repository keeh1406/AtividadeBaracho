package br.com.maiscadastros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.jdbc.Conexao;
import br.com.maiscadastros.model.Loja;
import br.com.maiscadastros.util.ExceptionUtil;

public class LojaDao
{
    private String comandoCreate   = "INSERT INTO LOJA "
                    + "(ID, NOME, TELEFONE, EMAIL, CNPJ, ENDERECO ) "
                    + "VALUES (CONSULTA_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
    private String comandoRecovery = "SELECT ID, NOME, TELEFONE, EMAIL, CNPJ, ENDERECO "
                    + "FROM LOJA "
                    + "WHERE ID = ?";
    private String comandoRecoveryByCnpj = "SELECT ID, NOME, TELEFONE, EMAIL, CNPJ, ENDERECO "
            + "FROM LOJA "
            + "WHERE CNPJ = ?";
    private String comandoUpdate   = "UPDATE LOJA "
                    + "SET NOME = ?, "
                    + "TELEFONE = ?, "
                    + "EMAIL = ?, "
                    + "CNPJ = ?, "
                    + "ENDERECO = ? "
                    + "WHERE ID = ?";
    private String comandoDelete   = "DELETE FROM LOJA "
                    + "WHERE ID = ?";
    private String comandoSearch   = "SELECT ID, NOME, TELEFONE, EMAIL, CNPJ, ENDERECO "
                    + "FROM LOJA";
    private String comandoSearchByNome = "SELECT ID, NOME, TELEFONE, EMAIL, CNPJ, ENDERECO "
            + "FROM LOJA "
    		+ "WHERE UPPER(NOME) LIKE UPPER(?)";

    public Loja create(Loja pLoja)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });


            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pLoja.getNome());
            tComandoJdbc.setLong(i++, pLoja.getTelefone());
            tComandoJdbc.setString(i++, pLoja.getEmail());
            tComandoJdbc.setLong(i++, pLoja.getCnpj());
            tComandoJdbc.setString(i++, pLoja.getEndereco());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Loja tLoja = pLoja;

                // Recuperando o código gerado pelo banco de dados
                ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
                tRsChave.next();

                // Assinalar a chave primária gerada no objeto
                pLoja.setId(tRsChave.getInt(1));

                // Liberar os recursos
                tRsChave.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tLoja;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da loja");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Loja recovery(int pId)
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
                Loja tLoja = recuperarLoja(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tLoja;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da loja");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }
    
    public Loja recoveryByCnpj(long pCnpj)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecoveryByCnpj);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setLong(i++, pCnpj);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            if (tResultSet.next())
            {
                // Criando o objeto
                Loja tLoja = recuperarLoja(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tLoja;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da loja");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Loja update(Loja pLoja)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pLoja.getNome());
            tComandoJdbc.setLong(i++, pLoja.getTelefone());
            tComandoJdbc.setString(i++, pLoja.getEmail());
            tComandoJdbc.setLong(i++, pLoja.getCnpj());
            tComandoJdbc.setString(i++, pLoja.getEndereco());
            tComandoJdbc.setInt(i++, pLoja.getId());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Loja tLoja = pLoja;

                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tLoja;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da loja");
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
            ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção da loja");
        }

        // Retorna falso indicando algum erro de processamento
        return false;
    }

    public List<Loja> search()
    {
        List<Loja> tLista = new ArrayList<>();

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
                Loja tLoja = recuperarLoja(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tLoja);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da loja");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    private Loja recuperarLoja(ResultSet tResultSet) throws SQLException
    {
        Loja tLoja = new Loja();

        // Recuperando os dados do resultSet
        tLoja.setId(tResultSet.getInt("ID"));
        tLoja.setNome(tResultSet.getString("NOME"));
        tLoja.setTelefone(tResultSet.getLong("TELEFONE"));
        tLoja.setEmail(tResultSet.getString("EMAIL"));
        tLoja.setCnpj(tResultSet.getLong("CNPJ"));
        tLoja.setEndereco(tResultSet.getString("ENDERECO"));
        return tLoja;
    }
    public List<Loja> searchByNome(String pNome)
    {
        if (pNome == null || pNome.isEmpty())
            return search();

        List<Loja> tLista = new ArrayList<>();

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
                Loja tLoja = recuperarLoja(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tLoja);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa dos Lojas");
        }

        // Retornando a lista de objetos
        return tLista;
    }
}
