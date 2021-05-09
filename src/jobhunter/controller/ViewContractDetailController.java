package jobhunter.controller;

import jobhunter.controller.utils.Utility;
import jobhunter.model.Contract;
import jobhunter.model.Specialist;
import jobhunter.model.database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewContractDetailController implements Initializable {

    @FXML
    private Label recruiterName;
    @FXML
    private Label contractDate;
    @FXML
    private TableView<Specialist> specialistsTable;
    @FXML
    private TableColumn<Specialist,String> specialistTypeCol;
    @FXML
    private TableColumn<Specialist,String> specialistNameCol;


    public static int contractId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Contract contract = (Contract) Database.contracts.get(contractId);
        recruiterName.setText(contract.getRecruiter().getName());
        contractDate.setText(contract.getCreatedAt().toString());
        setTableView(contract.getSpecialists());
    }
    private void setTableView(List<Specialist> specialists) {
        specialistTypeCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.4));
        specialistNameCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.6));
        specialistTypeCol.setCellValueFactory(new PropertyValueFactory<>("specialistType"));
        specialistNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        Utility.refreshTable(specialists, specialistsTable);
    }

}
