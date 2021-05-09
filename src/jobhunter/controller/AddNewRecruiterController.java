package jobhunter.controller;

import jobhunter.controller.utils.RecruiterTableItem;
import jobhunter.controller.utils.Utility;
import jobhunter.model.Recruiter;
import jobhunter.model.database.Database;
import jobhunter.view.AlertBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewRecruiterController implements Initializable {

    @FXML
    private TextField recruiterName;
    @FXML
    private TextField recruiterArea;
    @FXML
    private TextField recruiterEmployees;
    @FXML
    private TextField recruiterLogoFilePath;
    @FXML
    private TableView<RecruiterTableItem> recruitersTable;
    @FXML
    private TableColumn<RecruiterTableItem,ImageView> idCol;
    @FXML
    private TableColumn<RecruiterTableItem,ImageView> logoCol;
    @FXML
    private TableColumn<RecruiterTableItem,String> nameCol;
    @FXML
    private TableColumn<RecruiterTableItem,String> areaCol;
    @FXML
    private TableColumn<RecruiterTableItem,Integer> employeesCol;
    private File chosenFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recruiterLogoFilePath.setEditable(false);
        setTableView();
    }

    @FXML
    public void handleAddRecruiterButton(){
        Image logo = null;
        try {
             logo = new Image(chosenFile.toURI().toString());
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        try {
            String name = recruiterName.getText();
            String area = recruiterArea.getText();
            checkForRequiredFields(name,area);
            int employees = Integer.parseInt(recruiterEmployees.getText());
            Recruiter recruiter = Recruiter.getRecruiter(name, area, employees, logo);
            Database.recruiters.add(recruiter);
            clearAll();
            chosenFile = null;
        }catch (NullPointerException e){
            AlertBox.displayError("Chyba","Vyplňte všetky povinné polia");
            e.printStackTrace();
        }catch (NumberFormatException e){
            AlertBox.displayError("Chyba","Počet zamestnancov musí byť číslo");
            e.printStackTrace();
        }
        refreshTable();
    }

    @FXML
    public void handleChooseFileButton(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "(*.jpg, *.png)", "*.jpg","*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        chosenFile = fileChooser.showOpenDialog(ScreenController.stage);
        recruiterLogoFilePath.setText(chosenFile.getPath());
    }

    @FXML
    public void handleBackButton() {
        ScreenController.activate("home");
    }

    private void checkForRequiredFields(String name, String area){
        if(name.equals("") || area.equals(""))
            throw new NullPointerException();
    }
    private void setTableView(){
        idCol.prefWidthProperty().bind(recruitersTable.widthProperty().multiply(0.1));
        logoCol.prefWidthProperty().bind(recruitersTable.widthProperty().multiply(0.32));
        nameCol.prefWidthProperty().bind(recruitersTable.widthProperty().multiply(0.21));
        areaCol.prefWidthProperty().bind(recruitersTable.widthProperty().multiply(0.18));
        employeesCol.prefWidthProperty().bind(recruitersTable.widthProperty().multiply(0.19));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        logoCol.setCellValueFactory(new PropertyValueFactory<>("logo"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        areaCol.setCellValueFactory(new PropertyValueFactory<>("area"));
        employeesCol.setCellValueFactory(new PropertyValueFactory<>("employeeAmount"));
        refreshTable();
    }

    private void refreshTable(){
        List<RecruiterTableItem> rows = new ArrayList<>();
        List<Recruiter> recruitersTemp = Utility.cast(Database.recruiters.getItems());
        for (Recruiter r : recruitersTemp) {
            rows.add(new RecruiterTableItem(r.getId(), r.getName(), r.getArea(), r.getEmployeeAmount(), r.getLogo()));
        }
        Utility.refreshTable(rows,recruitersTable);
    }

    private void clearAll(){
        recruiterName.clear();
        recruiterArea.clear();
        recruiterEmployees.clear();
        recruiterLogoFilePath.clear();
    }


}
