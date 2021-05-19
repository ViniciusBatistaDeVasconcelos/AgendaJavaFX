package agendajavafx.controller;

import agendajavafx.model.Contato;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManterContatoController implements Initializable {

    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldTelefone;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnSubmeter;

    private Stage estagioDialogo;
    private boolean buttonSubmeterClicked = false;
    private Contato contato;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.textFieldId.setEditable(false);
        this.textFieldId.setDisable(true);
    }

    public Stage getEstagioDialogo() {
        return estagioDialogo;
    }

    public void setEstagioDialogo(Stage estagioDialogo) {
        this.estagioDialogo = estagioDialogo;
    }

    public boolean isButtonSubmeterClicked() {
        return buttonSubmeterClicked;
    }

    public void setButtonSubmeterClicked(boolean buttonSubmeterClicked) {
        this.buttonSubmeterClicked = buttonSubmeterClicked;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;

        if(contato.getId() == 0){
         this.textFieldId.setText("");   
        }else{
         this.textFieldId.setText(String.valueOf(this.contato.getId()));   
        }
        this.textFieldNome.setText(this.contato.getNome());
        this.textFieldTelefone.setText(this.contato.getTelefone());
        this.textFieldEmail.setText(this.contato.getEmail());

    }

    @FXML
    public void handleCancelar() {
        getEstagioDialogo().close();
    }

    @FXML
    public void handleSubmeter() {
        if(!this.textFieldId.getText().isEmpty()){
         contato.setId(Integer.parseInt(this.textFieldId.getText()));   
        }
        contato.setNome(this.textFieldNome.getText());
        contato.setTelefone(this.textFieldTelefone.getText());
        contato.setEmail(this.textFieldEmail.getText());

        this.buttonSubmeterClicked = true;
        estagioDialogo.close();
    }

}
