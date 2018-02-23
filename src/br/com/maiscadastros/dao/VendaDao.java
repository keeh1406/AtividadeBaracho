package br.com.maiscadastros.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.maiscadastros.model.Venda;
import br.com.maiscadastros.jdbc.Conexao;
import br.com.maiscadastros.util.ExceptionUtil;

public class VendaDao
{
    private String comandoCreate   = "INSERT INTO VENDA "
                    + "(ID, DESCRICAO, QUANTIDADEPRODUTO, VALOR, DATA, NOTAFISCAL, ID_PRODUTO, ID_CLIENTE )"
                    + "VALUES (VENDA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
    private String comandoRecovery = "SELECT ID, DESCRICAO, QUANTIDADEPRODUTO, VALOR, DATA, NOTAFISCAL, ID_PRODUTO, ID_CLIENTE "
                    + "FROM VENDA "
                    + "WHERE ID = ?";
    private String comandoRecoveryByNotaFiscal = "SELECT ID, DESCRICAO, QUANTIDADEPRODUTO, VALOR, DATA, NOTAFISCAL, ID_PRODUTO, ID_CLIENTE "
                    + "FROM VENDA "
                    + "WHERE NOTAFISCAL = ?";
    private String comandoUpdate   = "UPDATE VENDA "
                    + "SET DESCRICAO = ?, "
                    + "QUANTIDADEPRODUTO = ?, "
                    + "VALOR = ?, "
                    + "DATA = ?, "
                    + "NOTAFISCAL = ?, "
                    + "ID_PRODUTO = ?, "
                    + "ID_CLIENTE = ? "
                    + "WHERE ID = ?";
    private String comandoDelete   = "DELETE FROM VENDA "
                    + "WHERE ID = ?";
    private String comandoSearch   = "SELECT ID, DESCRICAO, QUANTIDADEPRODUTO, VALOR, DATA, NOTAFISCAL, ID_PRODUTO, ID_CLIENTE "
                    + "FROM VENDA";
    private String comandoSearchByProduto   = "SELECT ID, DESCRICAO, QUANTIDADEPRODUTO, VALOR, DATA, NOTAFISCAL, ID_PRODUTO, ID_CLIENTE "
                    + "FROM VENDA "
                    + "WHERE ID_PRODUTO = ?";
    private String comandoSearchByCliente   = "SELECT ID, DESCRICAO, QUANTIDADEPRODUTO, VALOR, DATA, NOTAFISCAL, ID_PRODUTO, ID_CLIENTE "
                    + "FROM VENDA "
                    + "WHERE ID_CLIENTE = ?";
    private String comandoSearchByDescricao   = "SELECT ID, DESCRICAO, QUANTIDADEPRODUTO, VALOR, DATA, NOTAFISCAL, ID_PRODUTO, ID_CLIENTE "
            + "FROM VENDA "
    		+ "WHERE UPPER(DESCRICAO) LIKE UPPER(?)";
    private String comandoCountByProduto   = "SELECT COUNT(ID_PRODUTO) "
                    + "FROM VENDA "
                    + "WHERE ID_PRODUTO = ?";

    public Venda create(Venda pVenda)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCreate, new String[] { "ID" });


            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pVenda.getDescricao());
            tComandoJdbc.setInt(i++, pVenda.getQuantidadeProduto());
            tComandoJdbc.setBigDecimal(i++, pVenda.getValor());
            tComandoJdbc.setDate(i++, Date.valueOf(pVenda.getData()));
            tComandoJdbc.setLong(i++, pVenda.getNotaFiscal());
            tComandoJdbc.setInt(i++, pVenda.getIdProduto());
            tComandoJdbc.setInt(i++, pVenda.getIdCliente());
            
            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Venda tVenda = pVenda;

                // Recuperando o código gerado pelo banco de dados
                ResultSet tRsChave = tComandoJdbc.getGeneratedKeys();
                tRsChave.next();

                // Assinalar a chave primária gerada no objeto
                pVenda.setId(tRsChave.getInt(1));

                // Liberar os recursos
                tRsChave.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tVenda;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Venda");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Venda recovery(int pId)
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
                Venda tVenda = recuperarVenda(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tVenda;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Venda");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }
    
    public Venda recoveryByNotaFiscal(long pNotaFiscal)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoRecoveryByNotaFiscal);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setLong(i++, pNotaFiscal);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            if (tResultSet.next())
            {
                // Criando o objeto
                Venda tVenda = recuperarVenda(tResultSet);

                // Liberar os recursos
                tResultSet.close();
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tVenda;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Venda");
        }

        // Retorna null indicando algum erro de processamento
        return null;
    }

    public Venda update(Venda pVenda)
    {
        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoUpdate);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, pVenda.getDescricao());
            tComandoJdbc.setInt(i++, pVenda.getQuantidadeProduto());
            tComandoJdbc.setBigDecimal(i++, pVenda.getValor());
            tComandoJdbc.setDate(i++, Date.valueOf(pVenda.getData()));
            tComandoJdbc.setLong(i++, pVenda.getNotaFiscal());
            tComandoJdbc.setInt(i++, pVenda.getIdProduto());
            tComandoJdbc.setInt(i++, pVenda.getIdCliente());
            tComandoJdbc.setInt(i++, pVenda.getId());

            // Executar o comando
            int tQtd = tComandoJdbc.executeUpdate();

            // Processar o resultado
            if (tQtd == 1)
            {
                // Copiando o parametro
                Venda tVenda = pVenda;

                // Liberar os recursos
                tComandoJdbc.close();

                // Retornando o objeto inserido
                return tVenda;
            }
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Venda");
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
            ExceptionUtil.mostrarErro(tExcept, "Problemas na remoção da Venda");
        }

        // Retorna falso indicando algum erro de processamento
        return false;
    }

    public List<Venda> search()
    {
        List<Venda> tLista = new ArrayList<>();

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
                Venda tVenda = recuperarVenda(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tVenda);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da Venda");
        }

        // Retornando a lista de objetos
        return tLista;
    }
    
    public int countByProduto(int pIdProduto)
    {
        int tQtde = 0;

        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoCountByProduto);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pIdProduto);

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
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação da venda");
        }

        // Retornando a lista de objetos
        return tQtde;
    }
    
    public List<Venda> searchByIdProduto(int pIdProduto)
    {
        List<Venda> tLista = new ArrayList<>();

        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByProduto);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pIdProduto);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Venda tVenda = recuperarVenda(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tVenda);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do Venda");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    public List<Venda> searchByIdCliente(int pIdCliente)
    {
        List<Venda> tLista = new ArrayList<>();

        try
        {
            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByCliente);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setInt(i++, pIdCliente);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Venda tVenda = recuperarVenda(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tVenda);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na criação do Venda");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    private Venda recuperarVenda(ResultSet tResultSet) throws SQLException
    {
        Venda tVenda = new Venda();

        // Recuperando os dados do resultSet
        tVenda.setId(tResultSet.getInt("ID"));
        tVenda.setDescricao(tResultSet.getString("DESCRICAO"));
        tVenda.setQuantidadeProduto(tResultSet.getInt("QUANTIDADEPRODUTO"));
        tVenda.setValor(tResultSet.getBigDecimal("VALOR"));
        tVenda.setData(tResultSet.getDate("DATA").toLocalDate());  
        tVenda.setNotaFiscal(tResultSet.getLong("NOTAFISCAL"));
        tVenda.setIdProduto(tResultSet.getInt("ID_PRODUTO"));
        tVenda.setIdCliente(tResultSet.getInt("ID_CLIENTE"));
        return tVenda;
    }
    public List<Venda> searchByDescricao(String pDescricao)
    {
        if (pDescricao == null || pDescricao.isEmpty())
            return search();

        List<Venda> tLista = new ArrayList<>();

        try
        {
            // Preparando o nome para pesquisa
            String tDescricao = "%" + pDescricao + "%";

            // Obter a conexão
            Connection tConexao = Conexao.getConexao();

            // Criar o comando
            PreparedStatement tComandoJdbc = tConexao.prepareStatement(comandoSearchByDescricao);

            // Preencher o comando
            int i = 1;
            tComandoJdbc.setString(i++, tDescricao);

            // Executar o comando
            ResultSet tResultSet = tComandoJdbc.executeQuery();

            // Processar o resultado
            while (tResultSet.next())
            {
                Venda tVenda = recuperarVenda(tResultSet);

                // Adicionar o o bjeto na lista
                tLista.add(tVenda);
            }

            // Liberar os recursos
            tResultSet.close();
            tComandoJdbc.close();
        }
        catch (SQLException tExcept)
        {
            ExceptionUtil.mostrarErro(tExcept, "Problemas na pesquisa das Vendas");
        }

        // Retornando a lista de objetos
        return tLista;
    }
}
