package jobhunter.model;

import java.util.List;

public class SecurityConsultant extends Specialist{

    private boolean isNBUAuditor;

    private SecurityConsultant(String name, double manDay, int experience, String education, List<String> certificate, boolean isNBUAuditor) {
        super(name, manDay, experience, education, certificate);
        this.isNBUAuditor = isNBUAuditor;
        this.specialistType = "Bezp. konzultant";
    }

    public static SecurityConsultant getConsultant(String name, double manDay, int experience, String education,
                                                   List<String> certificate, boolean isNBUAuditor){
        return  new SecurityConsultant(name, manDay, experience, education, certificate, isNBUAuditor);
    }

    public boolean isNBUAuditor() {
        return isNBUAuditor;
    }
}
