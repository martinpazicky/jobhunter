package jobhunter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ImageView logoImage;

    @FXML
    public void handleAddNewRecruiterButton() {
        ScreenController.activate("newRecruiter");
    }

    @FXML
    public void handleAddNewSpecialistButton() {
        ScreenController.activate("newSpecialist");
    }

    @FXML
    public void handleManageContractsButton() {
        ScreenController.activate("manageContracts");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image image = new Image("jobhunter/resources/imgs/jobhunter.png");
        logoImage.setImage(image);
        logoImage.setPreserveRatio(true);
    }
}
