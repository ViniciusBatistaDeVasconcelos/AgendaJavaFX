package agendajavafx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/index.fxml"));
        Scene scene = new Scene(root); // instância a cena

        stage.setScene(scene); // seta a cena
        stage.setTitle("Sistema de Agendas - Vinícius Vasconcelos"); // título da tela principal
        stage.setResizable(false); // desabilitar que o usuário mude o tamanho da tela
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
