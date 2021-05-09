package jobhunter.model.database;

public class IdentifiableRepository extends Repository<Identifiable>{

    private int idCounter = 1;
    /**
     * Adds an object to database
     * Sets ID of the object
     * @param item Object to be added
     */
    public void add(Identifiable item)
    {
        super.add(item);
        item.setId(idCounter);
        idCounter++;
    }

    /**
     * Removes an object from database based on given ID
     * @param ID ID of object to be removed
     */
    public void remove(int ID)
    {
        Identifiable item = get(ID);
        super.remove(item);
    }

    /**
     * Gets object from database based on given ID
     * @param ID ID of desired object
     * @return desired object, null if no such an object exists
     */
    public Identifiable get(int ID)
    {
        for(Identifiable i : objectList)
        {
            if(i.getId() == ID)
                return i;
        }
        return null;
    }

}
