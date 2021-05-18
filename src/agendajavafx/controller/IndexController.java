package agendajavafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class IndexController implements Initializable {

    
    @FXML
    private MenuItem menuContatosMinhaLista;
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @FXML
    public void handlePesquisaContato() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/agendajavafx/view/pesquisaContato.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
}
