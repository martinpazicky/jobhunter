package jobhunter.controller.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class RecruiterTableItem {
    private int id;
    private String name;
    private String area;
    private int employeeAmount;
    private ImageView logo;

    public RecruiterTableItem(int id, String name, String area, int employeeAmount, Image logo) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.employeeAmount = employeeAmount;
        this.logo = new ImageView(logo);
        this.logo.setImage(logo);
        this.logo.setPreserveRatio(true);
        this.logo.setFitHeight(70);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public int getEmployeeAmount() {
        return employeeAmount;
    }

    public ImageView getLogo() {
        return logo;
    }
}
