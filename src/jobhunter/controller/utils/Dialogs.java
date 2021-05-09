package jobhunter.controller.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Dialogs {


    public static void openDialogWindow(String fxml, int width, int height){
        final Stage dialog = new Stage();
        try {
            Parent root = FXMLLoader.load(Dialogs.class.getResource(fxml));
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.getIcons().add(new Image("invoice_system/images/logo.png"));
            Scene dialogScene = new Scene(root, width, height);
            dialog.setMaxWidth(width);
            dialog.setMaxHeight(height);
            dialog.setMinWidth(width);
            dialog.setMinHeight(height);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void openDialogWindow(FXMLLoader loader){
        final Stage dialog = new Stage();
        try {
            Parent root =  loader.load();
            dialog.initModality(Modality.APPLICATION_MODAL);
            Scene dialogScene = new Scene(root, 400, 300);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
