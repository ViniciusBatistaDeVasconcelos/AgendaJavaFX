package agendajavafx.controller;

import agendajavafx.model.Contato;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
        labelId.setText(String.valueOf(contato.getId()));
        labelNome.setText(String.valueOf(contato.getNome()));
        labelTelefone.setText(String.valueOf(contato.getTelefone()));
        labelEmail.setText(String.valueOf(contato.getEmail()));
    }
}
