package jobhunter.model;

import jobhunter.model.database.Identifiable;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Contract implements Identifiable {
    private int id;
    private Recruiter recruiter;
    private List<Specialist> specialists;
    private LocalDate createdAt;

    private Contract(Recruiter recruiter, List<Specialist> specialists, LocalDate createdAt) {
        this.recruiter = recruiter;
        this.specialists = specialists;
        this.createdAt = createdAt;
    }


    public static Contract getContract(Recruiter recruiter, List<Specialist> specialists, LocalDate createdAt){
        return new Contract(recruiter, specialists, createdAt);
    }

    public Recruiter getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    public List<Specialist> getSpecialists() {
        return specialists;
    }

    public void setSpecialists(List<Specialist> specialists) {
        this.specialists = specialists;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int ID) {
        this.id = ID;
    }
}
