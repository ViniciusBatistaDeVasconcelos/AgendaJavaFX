package agendajavafx.dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {

    private static BD instancia = new BD();

    public static BD getInstancia() {
        return instancia;
    }

    private BD() {
    }
    
    public Connection getConexao() throws ClassNotFoundException, SQLException{
        Connection conexao = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexao = DriverManager.getConnection("jdbc:mysql://localhost:3308/agenda?useUnicode=true&characterEncoding=UTF-8", "root", "");
        return conexao;
    }
}
