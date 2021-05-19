package agendajavafx.model;

import agendajavafx.dao.ContatoDAO;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Button;

public class Contato {

    private int id;
    private String nome;
    private String telefone;
    private String email;
    private Button alterar;
    private Button excluir;

    public Contato(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Contato() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void gravar() throws ClassNotFoundException, SQLException {
        ContatoDAO.getInstancia().gravar(this);
    }

    public void alterar() throws ClassNotFoundException, SQLException {
        ContatoDAO.getInstancia().alterar(this);
    }

    public void excluir() throws ClassNotFoundException, SQLException {
        ContatoDAO.getInstancia().excluir(this);
    }

    public static Contato obter(int id) throws ClassNotFoundException, SQLException {
        return ContatoDAO.getInstancia().obter(id);
    }

    public static List<Contato> obterTodos() throws ClassNotFoundException, SQLException {
        return ContatoDAO.getInstancia().obterTodos();
    }
}
