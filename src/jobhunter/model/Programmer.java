package jobhunter.model;

import java.util.List;

public class Programmer extends Specialist {
    private String language;

    private Programmer(String name, double manDay, int experience, String education, List<String> certificate, String language) {
        super(name, manDay, experience, education, certificate);
        this.language = language;
        this.specialistType = "Programátor";
    }

    public static Programmer getProgrammer(String name, double manDay, int experience, String education,
                                           List<String> certificate, String language){
        return new Programmer(name, manDay, experience, education, certificate, language);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
