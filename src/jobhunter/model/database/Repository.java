package jobhunter.model.database;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of Repository design pattern
 * @param <T> Type of object that is stored in database
 * @author martinpazicky
 */

public abstract class Repository<T> {
    protected List<T> objectList = new ArrayList<>();

    /**
     * Adds object to database
     * @param o object to be added
     */
    public void add(T o)
    {
        objectList.add(o);
    }

    /**
     * Removes object from database
     * @param o object to be removed
     */
    public void remove(T o)
    {
        objectList.remove(o);
    }

    /**
     * Getter for {@link Repository#objectList}
     * @return {@link Repository#objectList}
     */
    public List<T> getItems()
    {
        return this.objectList;
    }

    public void print() {
        for (T o : objectList) {
            System.out.println(o);
        }
    }

}

