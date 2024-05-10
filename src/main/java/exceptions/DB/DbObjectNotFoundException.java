package exceptions.DB;

import java.sql.SQLException;

public class DbObjectNotFoundException extends DbException{

    public DbObjectNotFoundException(SQLException message) {
        super(message);
    }
}
