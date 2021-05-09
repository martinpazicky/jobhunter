package jobhunter.controller.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class Utility {
    public static <T> List<T> cast(List list) {
        return list;
    }
    public static  <T> void refreshTable(List<T> list, TableView<T> tableView){
        ObservableList<T> toShow = FXCollections.observableArrayList(list);
        tableView.getItems().clear();
        tableView.getItems().addAll(toShow);
    }
}

