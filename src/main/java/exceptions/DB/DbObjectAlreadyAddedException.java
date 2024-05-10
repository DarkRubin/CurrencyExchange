package exceptions.DB;

import java.sql.SQLException;

public class DbObjectAlreadyAddedException extends DbException{

    public DbObjectAlreadyAddedException(SQLException message) {
        super(message);
    }
}
