package jobhunter.model;

import jobhunter.model.database.Identifiable;

import java.util.List;

public abstract class Specialist implements Identifiable {
    private int id;
    private String name;
    private double manDay;
    private int experience;
    private String education;
    private List<String> certificates;
    private boolean isAvailable;
    protected String specialistType;

    protected Specialist(String name, double manDay, int experience, String education, List<String> certificates) {
        this.name = name;
        this.manDay = manDay;
        this.experience = experience;
        this.education = education;
        this.certificates = certificates;
        this.isAvailable = true;
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

    public double getManDay() {
        return manDay;
    }

    public void setManDay(double manDay) {
        this.manDay = manDay;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public void setCertificate(List<String> certificates) {
        this.certificates = certificates;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getSpecialistType() {
        return specialistType;
    }

    public void setSpecialistType(String specialistType) {
        this.specialistType = specialistType;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString(){
        return  this.specialistType + " " + this.name;
    }
}
