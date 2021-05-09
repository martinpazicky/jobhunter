package jobhunter.controller;

import jobhunter.controller.utils.Filter;
import jobhunter.controller.utils.Utility;
import jobhunter.model.*;
import jobhunter.model.database.Database;
import jobhunter.view.AlertBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class CreateContractController implements Initializable {
    @FXML
    private ComboBox<Recruiter> recruitersCB;
    @FXML
    private Label recruiterNameLabel;
    @FXML
    private Label recruiterAreaLabel;
    @FXML
    private Label recruiterEmployeesLabel;
    @FXML
    private HBox recruiterNameHB;
    @FXML
    private HBox recruiterAreaHB;
    @FXML
    private HBox recruiterEmployeesHB;
    @FXML
    private ImageView recruiterLogo;
    @FXML
    private TableView<Specialist> specialistsTable;
    @FXML
    private TableColumn<Specialist,String> specialistTypeCol;
    @FXML
    private TableColumn<Specialist,String> specialistNameCol;
    @FXML
    private TableColumn<Specialist,Double> specialistPriceCol;
    @FXML
    private TableColumn<Specialist,Double> specialistExperienceCol;
    @FXML
    private ComboBox<String> addedOrAllCB;
    @FXML
    private ComboBox<String> typeFilterCB;
    @FXML
    private TextField filterNameTF;

    private List<Specialist> specialistList = new ArrayList<>();
    private final String ALL = "Všetci";
    private final String ADDED = "Pridaní";
    private final String ADMIN = "Administrátor";
    private final String SECURITY = "Bezpečnostný konzultant";
    private final String PROGRAMMER = "Programátor";
    private Map<String,Class<? extends Specialist>> typeToClass = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTypeToClassMap();
        initializeRecruitersCB();
        initializeAddedOrAllCB();
        initializeTypeFilterCB();
        showRecruiterInfo(false);
        setTableView();
        readOnDoubleClick();
    }

    @FXML
    public void chooseRecruiter(){
        Recruiter r = recruitersCB.getSelectionModel().getSelectedItem();
        if (r != null) {
            recruiterNameLabel.setText(r.getName());
            recruiterAreaLabel.setText(r.getArea());
            recruiterEmployeesLabel.setText(String.valueOf(r.getEmployeeAmount()));
            recruiterLogo.setImage(r.getLogo());
            showRecruiterInfo(true);
        }
    }

    @FXML
    public void handleAddSpecialistButton(){
        int id = getSelectedTableItemId();
        Specialist s = (Specialist) Database.specialists.get(id);
        if(s.isAvailable()) {
            if(!specialistList.contains(s)) {
                specialistList.add(s);
                AlertBox.displaySuccess("", "Špecialista bol úspešne pridaný");
            }else {
                AlertBox.displayError("Chyba", "Špecialista už bol pridaný");
            }
        }else {
            AlertBox.displayError("Chyba", "Špecialista nie je dostupný");
        }
    }

    @FXML
    public void handleCreateContractButton(){
        try {
            Recruiter rec = recruitersCB.getSelectionModel().getSelectedItem();
            if (rec == null)
                throw new NullPointerException();
            List<Specialist> specs = new ArrayList<>(specialistList);
            if (specialistList.size() == 0){
                throw new IllegalArgumentException();
            }
            for (Specialist s : specs) {
                s.setAvailable(false);
            }
            Contract contract = Contract.getContract(rec, specs, LocalDate.now());
            Database.contracts.add(contract);
            clearAll();
            addedOrAllCB.getSelectionModel().select(ALL);
            typeFilterCB.getSelectionModel().select(ALL);
        }catch (NullPointerException e){
            AlertBox.displayError("Chyba", "Nebol zvolený žiadny zamestnávateľ");
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            AlertBox.displayError("Chyba","Zoznam špecialistov je prázdny");
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
    public void handleFilters(){
        List<Specialist> list = Utility.cast(Database.specialists.getItems());
        list = applyAddedOrAllFilter(list);
        list = applyTypeFilter(list);
        list = applyNameFilter(list);
        Utility.refreshTable(list,specialistsTable);
    }

    @FXML
    public void handleDefaultFiltersButton(){
        addedOrAllCB.getSelectionModel().select(ALL);
        typeFilterCB.getSelectionModel().select(ALL);
        filterNameTF.clear();
        handleFilters();
    }

    @FXML
    public void handleBackButton() {
        ScreenController.activate("manageContracts");
    }

    private List<Specialist> applyAddedOrAllFilter(List<Specialist> list){
        String option = addedOrAllCB.getSelectionModel().getSelectedItem();
        if (option.equals(ALL)){
            return list;
        } else {
            return specialistList;
        }
    }

    private List<Specialist> applyTypeFilter(List<Specialist> list){
        String option = typeFilterCB.getSelectionModel().getSelectedItem();
        if(option.equals(ALL)) {
            return list;
        }
        else {
            return Utility.cast(Filter.filterByType(list,typeToClass.get(option)));
        }
    }

    private List<Specialist> applyNameFilter(List<Specialist> list) {
        String name = filterNameTF.getText().toLowerCase();
        return Filter.filterByName(list,name);
    }

    private void initializeTypeToClassMap(){
        typeToClass.put(ADMIN,Administrator.class);
        typeToClass.put(SECURITY, SecurityConsultant.class);
        typeToClass.put(PROGRAMMER, Programmer.class);
    }

    private void showRecruiterInfo(boolean show){
        recruiterNameHB.setVisible(show);
        recruiterAreaHB.setVisible(show);
        recruiterEmployeesHB.setVisible(show);
    }

    private int getSelectedTableItemId(){
        TablePosition pos = specialistsTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        return specialistsTable.getItems().get(row).getId();
    }

    private void initializeRecruitersCB(){
        List<Recruiter> recruiters = Utility.cast(Database.recruiters.getItems());
        ObservableList<Recruiter> recruitersOL = FXCollections.observableArrayList(recruiters);
        recruitersCB.setItems(recruitersOL);
    }

    private void initializeAddedOrAllCB(){
        List<String> options = new ArrayList<>();
        options.add(ALL);
        options.add(ADDED);
        ObservableList<String> optionsOL = FXCollections.observableArrayList(options);
        addedOrAllCB.setItems(optionsOL);
        addedOrAllCB.getSelectionModel().select(ALL);
    }


    private void initializeTypeFilterCB(){
        List<String> options = new ArrayList<>();
        options.add(ALL);
        options.add(ADMIN);
        options.add(SECURITY);
        options.add(PROGRAMMER);
        ObservableList<String> optionsOL = FXCollections.observableArrayList(options);
        typeFilterCB.setItems(optionsOL);
        typeFilterCB.getSelectionModel().select(ALL);
    }

    private void clearAll(){
        recruiterNameLabel.setText(null);
        recruiterAreaLabel.setText(null);
        recruiterEmployeesLabel.setText(null);
        recruiterLogo.setImage(null);
        recruitersCB.getSelectionModel().clearSelection();
        specialistList.clear();
        filterNameTF.clear();
    }

    private void setTableView(){
        specialistTypeCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.25));
        specialistNameCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.4));
        specialistPriceCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.18));
        specialistExperienceCol.prefWidthProperty().bind(specialistsTable.widthProperty().multiply(0.17));
        specialistTypeCol.setCellValueFactory(new PropertyValueFactory<>("specialistType"));
        specialistNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        specialistPriceCol.setCellValueFactory(new PropertyValueFactory<>("manDay"));
        specialistExperienceCol.setCellValueFactory(new PropertyValueFactory<>("experience"));
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
