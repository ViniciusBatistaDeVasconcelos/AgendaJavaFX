package agendajavafx.dao;

import agendajavafx.dao.database.BD;
import agendajavafx.model.Contato;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO extends DAO {

    private final String strGravar = "INSERT INTO contatos (nome, telefone, email) VALUES (?,?,?);";
    private final String strAlterar = "UPDATE contatos SET nome = ?, telefone = ?, email = ? WHERE id = ?;";
    private final String strExcluir = "DELETE FROM contatos WHERE id = ?;";
    private final String strObter = "SELECT * FROM contatos WHERE id = ?;";
    private final String strObterTodos = "SELECT * FROM contatos;";

    private static ContatoDAO instancia = new ContatoDAO();

    public static ContatoDAO getInstancia() {
        return instancia;
    }

    public void gravar(Contato contato) throws ClassNotFoundException, SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getInstancia().getConexao();
            comando = conexao.prepareStatement(strGravar);
            comando.setString(1, contato.getNome());
            comando.setString(2, contato.getTelefone());
            comando.setString(3, contato.getEmail());
            comando.executeUpdate();
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public void alterar(Contato contato) throws ClassNotFoundException, SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getInstancia().getConexao();
            comando = conexao.prepareStatement(strAlterar);
            comando.setString(1, contato.getNome());
            comando.setString(2, contato.getTelefone());
            comando.setString(3, contato.getEmail());
            comando.setInt(4, contato.getId());
            comando.executeUpdate();

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public void excluir(Contato contato) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            conexao = BD.getInstancia().getConexao();
            comando = conexao.prepareStatement(strExcluir);
            comando.setInt(1, contato.getId());
            comando.executeUpdate();

        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public Contato obter(int id) throws ClassNotFoundException, SQLException {
        Connection conexao = null;
        PreparedStatement comando = null;
        Contato contato = null;

        try {
            conexao = BD.getInstancia().getConexao();
            comando = conexao.prepareStatement(strObter);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                contato = instanciarContato(resultado);
            }
            return contato;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    public List<Contato> obterTodos() throws ClassNotFoundException, SQLException {

        Connection conexao = null;
        PreparedStatement comando = null;
        Contato contato = null;
        List<Contato> contatos = new ArrayList<>();

        try {
            conexao = BD.getInstancia().getConexao();
            comando = conexao.prepareStatement(strObterTodos);

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                contato = instanciarContato(resultado);
                contatos.add(contato);
            }
            return contatos;
        } finally {
            fecharConexao(conexao, comando);
        }
    }

    private Contato instanciarContato(ResultSet resultado) throws SQLException {

        int id = resultado.getInt("id");
        String nome = resultado.getString("nome");
        String telefone = resultado.getString("telefone");
        String email = resultado.getString("email");

        Contato contato = new Contato(id, nome, telefone, email);
        return contato;
    }
}
