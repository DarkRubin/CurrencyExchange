package exceptions.DB;


import java.sql.SQLException;

public class DbConnectException extends DbException {

    public DbConnectException(SQLException message) {
        super(message);
    }
}
