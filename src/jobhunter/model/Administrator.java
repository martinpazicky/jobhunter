package jobhunter.model;

import java.util.List;

public class Administrator extends Specialist {
    private String appType;
    private String preferredPlatform;

    private Administrator(String name, double manDay, int experience, String education,
                          List<String> certificate, String appType, String preferredPlatform) {
        super(name, manDay, experience, education, certificate);
        this.appType = appType;
        this.preferredPlatform = preferredPlatform;
        this.specialistType = "Administrátor";
    }

    public static Administrator getAdministrator(String name, double manDay, int experience, String education,
                                                 List<String> certificate, String appType, String preferredPlatform){
        return new Administrator(name, manDay, experience, education, certificate, appType,preferredPlatform);
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getPreferredPlatform() {
        return preferredPlatform;
    }

    public void setPreferredPlatform(String preferredPlatform) {
        this.preferredPlatform = preferredPlatform;
    }
}
