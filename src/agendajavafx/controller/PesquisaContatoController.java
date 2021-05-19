package agendajavafx.controller;

import agendajavafx.dao.ContatoDAO;
import agendajavafx.model.Contato;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PesquisaContatoController implements Initializable {

    @FXML
    private TableView<Contato> tableViewContatos;
    @FXML
    private TableColumn<Contato, Integer> tableColumnContatosId;
    @FXML
    private TableColumn<Contato, String> tableColumnContatosNome;
    @FXML
    private Label labelId;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelTelefone;
    @FXML
    private Label labelEmail;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnExcluir;

    private List<Contato> listaContatos;
    private ObservableList<Contato> observableListContatos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            carregarTableView();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        // aciona diante das alterações nas linhas da tabela
        tableViewContatos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> clicarItemTableView(newValue)
        );
    }

    public void carregarTableView() throws ClassNotFoundException, SQLException {

        tableColumnContatosId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnContatosNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        listaContatos = Contato.obterTodos();

        observableListContatos = FXCollections.observableArrayList(listaContatos);
        tableViewContatos.setItems(observableListContatos);
    }

    public void clicarItemTableView(Contato contato) {
        if (contato != null) {
            labelId.setText(String.valueOf(contato.getId()));
            labelNome.setText(contato.getNome());
            labelTelefone.setText(contato.getTelefone());
            labelEmail.setText(contato.getEmail());
        }else{
            labelId.setText("--");
            labelNome.setText("--");
            labelTelefone.setText("--");
            labelEmail.setText("--");
        }
    }

    @FXML
    public void handleExcluirContato() throws ClassNotFoundException, SQLException {
        Contato contato = tableViewContatos.getSelectionModel().getSelectedItem();
        if (contato != null) {
            ContatoDAO.getInstancia().excluir(contato);
            carregarTableView();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É necessário que você escolhe um cliente da tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleAlterarContato() throws ClassNotFoundException, SQLException, IOException {
        Contato contato = tableViewContatos.getSelectionModel().getSelectedItem();
        if (contato != null) {
            if (mostrarFXMLAnchorPaneManterContato(contato)) {
                ContatoDAO.getInstancia().alterar(contato);
                carregarTableView();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("É necessário que você escolhe um cliente da tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleGravarContato() throws ClassNotFoundException, SQLException, IOException {
        Contato contato = new Contato();
        if (mostrarFXMLAnchorPaneManterContato(contato)) {
            ContatoDAO.getInstancia().gravar(contato);
            carregarTableView();

        }
    }

    public boolean mostrarFXMLAnchorPaneManterContato(Contato contato) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ManterContatoController.class.getResource("/agendajavafx/view/manterContato.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage estagioDialogo = new Stage();
        estagioDialogo.setTitle("Manter Contatos");

        Scene scene = new Scene(page);
        estagioDialogo.setScene(scene);

        ManterContatoController controller = loader.getController();
        controller.setEstagioDialogo(estagioDialogo);
        controller.setContato(contato);

        estagioDialogo.showAndWait();
        return controller.isButtonSubmeterClicked();
    }
}
