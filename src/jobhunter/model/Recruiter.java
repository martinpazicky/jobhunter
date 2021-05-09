package jobhunter.model;

import jobhunter.model.database.Identifiable;
import javafx.scene.image.Image;


public class Recruiter implements Identifiable {
    private int id;
    private String name;
    private String area;
    private int employeeAmount;
    private Image logo;


    private Recruiter(String name, String area, int employeeAmount, Image logo) {
        this.name = name;
        this.area = area;
        this.employeeAmount = employeeAmount;
        this.logo = logo;
    }

    private Recruiter(String name, String area, int employeeAmount) {
        this.name = name;
        this.area = area;
        this.employeeAmount = employeeAmount;
    }


    public static Recruiter getRecruiter(String name, String area, int employeeAmount, Image logo){
        return new Recruiter(name, area, employeeAmount,logo);
    }

    public static Recruiter getRecruiter(String name, String area, int employeeAmount){
        return new Recruiter(name, area, employeeAmount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getEmployeeAmount() {
        return employeeAmount;
    }

    public void setEmployeeAmount(int employeeAmount) {
        this.employeeAmount = employeeAmount;
    }

    public Image getLogo() {
        return logo;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
