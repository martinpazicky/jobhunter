package jobhunter.model.database;

/**
 * Class aggregates databases of different kind
 * @author martinpazicky
 */
public class Database {
    public static IdentifiableRepository recruiters = new IdentifiableRepository();
    public static IdentifiableRepository specialists = new IdentifiableRepository();
    public static IdentifiableRepository contracts = new IdentifiableRepository();
}
