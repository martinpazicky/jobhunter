package jobhunter.model.database;

public interface Identifiable {
    /**
     * Getter for ID
     * @return ID
     */
    int getId();

    /**
     * Setter for ID
     * ID is set only once - in database
     * @param ID ID to be set
     */
    void setId(int ID);

}
