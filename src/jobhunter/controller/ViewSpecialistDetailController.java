package jobhunter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jobhunter.model.Administrator;
import jobhunter.model.Programmer;
import jobhunter.model.SecurityConsultant;
import jobhunter.model.Specialist;
import jobhunter.model.database.Database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewSpecialistDetailController implements Initializable {

    @FXML
    private Label specialistIdL;
    @FXML
    private Label specialistName;
    @FXML
    private Label specialistExperience;
    @FXML
    private Label specialistAvailability;
    @FXML
    private Label specialistEducation;
    @FXML
    private Label specialistType;
    @FXML
    private Label isAuditor;
    @FXML
    private Label programmerType;
    @FXML
    private Label adminType;
    @FXML
    private Label adminPlatform;
    @FXML
    private HBox securityExtra;
    @FXML
    private HBox programmerExtra;
    @FXML
    private VBox adminExtra;
    @FXML
    private ListView<String> specialistCertificates;

    private final String YES = "Áno";
    private final String NO = "Nie";
    public static int specialistId;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hideExtraFields();
        Specialist specialist = (Specialist) Database.specialists.get(specialistId);
        setListView(specialist.getCertificates());
        specialistIdL.setText(String.valueOf(specialistId));
        specialistName.setText(specialist.getName());
        specialistExperience.setText(String.valueOf(specialist.getExperience()));
        String availability = specialist.isAvailable() ? YES : NO;
        specialistAvailability.setText(availability);
        specialistEducation.setText(specialist.getEducation());
        specialistType.setText(specialist.getSpecialistType());
        if(specialist instanceof SecurityConsultant){
            SecurityConsultant sc = (SecurityConsultant)specialist;
            securityExtra.setVisible(true);
            String auditor =  sc.isNBUAuditor() ? YES : NO;
            isAuditor.setText(auditor);
        }
        if(specialist instanceof Programmer){
            Programmer pr = (Programmer) specialist;
            programmerExtra.setVisible(true);
            programmerType.setText(pr.getLanguage());
        }
        if(specialist instanceof Administrator){
            Administrator admin = (Administrator)specialist;
            adminExtra.setVisible(true);
            adminPlatform.setText(admin.getPreferredPlatform());
            adminType.setText(admin.getAppType());
        }

    }

    private void setListView(List<String> certificates){
        ObservableList<String> toShow = FXCollections.observableArrayList(certificates);
        specialistCertificates.getItems().clear();
        specialistCertificates.getItems().addAll(toShow);
    }

    private void hideExtraFields(){
        adminExtra.setVisible(false);
        programmerExtra.setVisible(false);
        securityExtra.setVisible(false);
    }
}
