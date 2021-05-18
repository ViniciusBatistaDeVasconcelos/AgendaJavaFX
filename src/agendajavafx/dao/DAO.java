package agendajavafx.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

    public void fecharConexao(Connection conexao, Statement comando) throws SQLException {
        if (conexao != null) {
            conexao.close();
        }
        if (comando != null) {
            comando.close();
        }
    }
}
