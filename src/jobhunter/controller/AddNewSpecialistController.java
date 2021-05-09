package jobhunter.controller;

import jobhunter.controller.utils.Utility;
import jobhunter.model.*;
import jobhunter.model.database.Database;
import jobhunter.view.AlertBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewSpecialistController implements Initializable {
    @FXML
    private RadioButton programmerRadio;
    @FXML
    private RadioButton administratorRadio;
    @FXML
    private ToggleGroup specialistTypeGroup;
    @FXML
    private RadioButton auditorYesRadio;
    @FXML
    private RadioButton auditorNoRadio;
    @FXML
    private RadioButton securityConsultantRadio;
    @FXML
    private ToggleGroup isAuditorGroup;
    @FXML
    private TextField specialistName;
    @FXML
    private TextField specialistPractice;
    @FXML
    private TextField specialistManDay;
    @FXML
    private TextField specialistEducation;
    @FXML
    private TextField specialistCertificates;
    @FXML
    private TextField programmerType;
    @FXML
    private TextField adminType;
    @FXML
    private TextField adminPlatform;
    @FXML
    private VBox consultantExtraFieldGroup;
    @FXML
    private VBox programmerExtraFieldGroup;
    @FXML
    private VBox adminExtraFieldGroup;
    @FXML
    private TableView<Specialist> specialistsTable;
    @FXML
    private TableColumn<Specialist,Integer> specialistIdCol;
    @FXML
    private TableColumn<Specialist,String> specialistTypeCol;
    @FXML
    private TableColumn<Specialist,String> specialistNameCol;
    @FXML
    private Label certificatesCount;

    private List<String> certificates = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeSpecialistRadioGroup();
        hideSpecificFields();
        setTableView();
        readOnDoubleClick();
    }

    @FXML
    public void handleAddCertificateButton(){
        String certificate = specialistCertificates.getText();
        certificates.add(certificate);
        String count = "(" + certificates.size() + ")";
        certificatesCount.setText(count);
        specialistCertificates.clear();
    }

    @FXML
    public void handleAddSpecialistButton(){
        RadioButton selected = (RadioButton)specialistTypeGroup.getSelectedToggle();
        if(selected == null){
            AlertBox.displayError("Chyba","Vyberte typ špecialistu");
            return;
        }

        try {
            String name = specialistName.getText();
            String education = specialistEducation.getText();
            checkRequiredFields(name,education);
            int experience = Integer.parseInt(specialistPractice.getText());
            double manDay = Double.parseDouble(specialistManDay.getText());
            if (selected == administratorRadio) {
                addNewAdmin(name, manDay, experience, education, certificates);
            } else if (selected == programmerRadio) {
                addNewProgrammer(name, manDay, experience, education, certificates);
            } else if (selected == securityConsultantRadio) {
                addNewSecurityConsultant(name, manDay, experience, education, certificates);
            }
            clearAll();
            certificates.clear();
            String count = "(" + certificates.size() + ")";
            certificatesCount.setText(count);
            Utility.refreshTable(Utility.cast(Database.specialists.getItems()),specialistsTable);
        }catch (NullPointerException e){
            AlertBox.displayError("Chyba","Vyplňte všetky povinné polia");
            e.printStackTrace();
        }catch (NumberFormatException e){
            AlertBox.displayError("Chyba","Prax a cena práce musia byť čísla");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewSpecialistDetailButton(){
        ViewSpecialistDetailController.specialistId = getSelectedTableItemId();
        ScreenController.activateInNewWindow("specialistDetail",950,700);
        ViewSpecialistDetailController.specialistId = 0;
    }

    @FXML
    public void handleBackButton() {
        ScreenController.activate("home");
    }

    private void clearAll(){
        specialistName.clear();
        specialistEducation.clear();
        specialistCertificates.clear();
        specialistPractice.clear();
        specialistManDay.clear();
        programmerType.clear();
        adminType.clear();
        adminPlatform.clear();
    }

    private int getSelectedTableItemId(){
        TablePosition pos = specialistsTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        return specialistsTable.getItems().get(row).getId();
    }

    private void checkRequiredFields(String name, String education){
        if(name.equals("") || education.equals("")) {
            throw new NullPointerException();
        }
    }


    private void addNewAdmin(String name, double manDay, int experience, String education, List<String> certificates){
        String appType = adminType.getText();
        String platform = adminPlatform.getText();
        if(appType.equals("") || platform.equals("")) {
            throw new NullPointerException();
        }
        Administrator a = Administrator.getAdministrator(name,manDay,experience,education,
                new ArrayList<>(certificates),appType,platform);
        Database.specialists.add(a);
    }

    private void addNewProgrammer(String name, double manDay, int experience, String education, List<String> certificates){
        String type = programmerType.getText();
        if(type.equals("")) {
            throw new NullPointerException();
        }
        Programmer pr = Programmer.getProgrammer(name,manDay,experience,education,new ArrayList<>(certificates),type);
        Database.specialists.add(pr);
    }

    private void addNewSecurityConsultant(String name, double manDay, int experience, String education, List<String> certificates){
        RadioButton isAuditorAns = (RadioButton)isAuditorGroup.getSelectedToggle();
        if(isAuditorAns == null){
            throw new NullPointerException();
        }
        boolean isAuditor = false;
        if(isAuditorAns == auditorYesRadio)
            isAuditor = true;
        SecurityConsultant sec = SecurityConsultant.getConsultant(name,manDay,experience,education,
                new ArrayList<>(certificates),isAuditor);
        Database.specialists.add(sec);
    }


    private void initializeSpecialistRadioGroup() {
        specialistTypeGroup.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton selected = (RadioButton) specialistTypeGroup.getSelectedToggle();
            hideSpecificFields();
            if (selected == administratorRadio) {
                adminExtraFieldGroup.setVisible(true);
            } else if (selected == securityConsultantRadio) {
                consultantExtraFieldGroup.setVisible(true);
            } else if (selected == programmerRadio) {
                programmerExtraFieldGroup.setVisible(true);
            }
        });
    }

    private void hideSpecificFields(){
        adminExtraFieldGroup.setVisible(false);
        programmerExtraFieldGroup.setVisible(false);
        consultantExtraFieldGroup.setVisible(false);
    }

    private void setTableView(){
        specialistIdCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.1));
        specialistTypeCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.4));
        specialistNameCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.5));
        specialistIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        specialistTypeCol.setCellValueFactory(new PropertyValueFactory<>("specialistType"));
        specialistNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        Utility.refreshTable(Utility.cast(Database.specialists.getItems()),specialistsTable);
    }

    private void readOnDoubleClick()
    {
        specialistsTable.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2) {
                handleViewSpecialistDetailButton();
            }
        });
    }

}
