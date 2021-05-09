package jobhunter.controller;

import jobhunter.controller.utils.Utility;
import jobhunter.model.Contract;
import jobhunter.model.Specialist;
import jobhunter.model.database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ManageContractsController implements Initializable {
    @FXML
    TableView<Contract> contractsTable;
    @FXML
    TableColumn<Contract, String> recruiterCol;
    @FXML
    TableColumn<Contract, Integer> idCol;
    @FXML
    TableColumn<Contract, LocalDate> createdAtCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableView();
        readOnDoubleClick();
    }

    @FXML
    public void handleDeleteButton(){
        int id = getSelectedTableItemId();
        Contract contract = (Contract) Database.contracts.get(id);
        for(Specialist s : contract.getSpecialists()){
            s.setAvailable(true);
        }
        Database.contracts.remove(id);
        Utility.refreshTable(Utility.cast(Database.contracts.getItems()),contractsTable);
    }

    @FXML
    public void handleCreateContractButton() {
        ScreenController.activate("createContract");
    }

    @FXML
    public void handleViewButton(){
        ViewContractDetailController.contractId = getSelectedTableItemId();
        ScreenController.activateInNewWindow("contractDetail", 800,500);
        ViewContractDetailController.contractId = 0;
    }

    private void setTableView(){
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        recruiterCol.setCellValueFactory(new PropertyValueFactory<>("recruiter"));
        createdAtCol.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        idCol.prefWidthProperty().bind(contractsTable.widthProperty().multiply(0.1));
        recruiterCol.prefWidthProperty().bind(contractsTable.widthProperty().multiply(0.5));
        createdAtCol.prefWidthProperty().bind(contractsTable.widthProperty().multiply(0.4));
        Utility.refreshTable(Utility.cast(Database.contracts.getItems()),contractsTable);
    }

    private int getSelectedTableItemId(){
        TablePosition pos = contractsTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        return contractsTable.getItems().get(row).getId();
    }

    @FXML
    public void handleBackButton() {
        ScreenController.activate("home");
    }

    private void readOnDoubleClick()
    {
        contractsTable.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                handleViewButton();
            }
        });
    }
}
