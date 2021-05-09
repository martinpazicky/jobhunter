package jobhunter.controller;

import jobhunter.controller.utils.Filter;
import jobhunter.model.*;
import jobhunter.model.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main extends Application {

    private static void  addEntities(){
        String name = "IBM";
        String area = "IT";
        int employees = 250;
        Image logo = new Image("jobhunter/resources/imgs/ibm.png");
        String name2 = "Apple";
        String area2 = "IT";
        int employees2 = 180;
        Image logo2 = new Image("jobhunter/resources/imgs/apple.png");
        Recruiter recruiter = Recruiter.getRecruiter(name, area, employees,logo);
        Database.recruiters.add(recruiter);
        Recruiter recruiter2 = Recruiter.getRecruiter(name2, area2, employees2,logo2);
        Database.recruiters.add(recruiter2);
        List<String> cert = new ArrayList<>();
        cert.add("Oracle Java");
        cert.add("Java Pro");
        List<String> cert2 = new ArrayList<>();
        cert2.add("C++ Pro");
        List<String> cert3 = new ArrayList<>();
        cert3.add("Admin Pro");
        cert3.add("Admin Pro2");
        cert3.add("Admin Expert");
        List<String> cert4 = new ArrayList<>();
        cert4.add("SECURITY EXPERT");
        Programmer programmer = Programmer.getProgrammer("Martin Pažický", 70,2,
                "FIIT STU", cert, "Java");
        Programmer programmer2 = Programmer.getProgrammer("Jozef Buban", 80,4,
                "FIIT STU", cert2, "C,C++,Java");
        Programmer programmer3 = Programmer.getProgrammer("Patrícia Palová", 55,1,
                "TUKE", cert2, "PHP,HTML,CSS,JS");
        Administrator admin = Administrator.getAdministrator("Jano Žbirka",60,3,
                "FEI STU",cert3,"app","x");
        Administrator admin2 = Administrator.getAdministrator("Miloš Peterka",80,5,
                "FEI STU",cert3,"neviem","x");
        SecurityConsultant sec = SecurityConsultant.getConsultant("Martin Kováč", 125, 7,
                "FIIT STU",cert4,true);
        SecurityConsultant sec2 = SecurityConsultant.getConsultant("Jozef Buxanto", 70, 1,
                "CVUT PRAHA",cert4,false);

        Database.specialists.add(programmer);
        Database.specialists.add(programmer2);
        Database.specialists.add(programmer3);
        Database.specialists.add(admin);
        Database.specialists.add(admin2);
        Database.specialists.add(sec);
        Database.specialists.add(sec2);
    }

    private void initializeScreenController(Stage primaryStage, Scene scene){
        ScreenController.stage = primaryStage;
        ScreenController.main = scene;
        ScreenController.addScreen("home", "/jobhunter/view/home.fxml");
        ScreenController.addScreen("newRecruiter", "/jobhunter/view/addNewRecruiter.fxml");
        ScreenController.addScreen("newSpecialist", "/jobhunter/view/addNewSpecialist.fxml");
        ScreenController.addScreen("createContract", "/jobhunter/view/createContract.fxml");
        ScreenController.addScreen("manageContracts", "/jobhunter/view/manageContracts.fxml");
        ScreenController.addScreen("contractDetail", "/jobhunter/view/viewContractDetail.fxml");
        ScreenController.addScreen("specialistDetail", "/jobhunter/view/viewSpecialistDetail.fxml");
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane root = FXMLLoader.load(getClass().getResource("/jobhunter/view/home.fxml"));
        primaryStage.setTitle("JobHunter");
        Scene scene = new Scene(root, 1060, 700);
        initializeScreenController(primaryStage,scene);
        scene.getStylesheets().add("jobhunter/resources/css/style.css");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("jobhunter/resources/imgs/jhlogo.png"));
        primaryStage.setMinWidth(1100);
        primaryStage.setMinHeight(700);
        primaryStage.show();
    }

    public static void main(String[] args) {
        addEntities();
        launch(args);
    }
}
