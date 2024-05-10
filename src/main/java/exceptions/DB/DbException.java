package exceptions.DB;

import java.sql.SQLException;

public abstract class DbException extends SQLException {
    protected final SQLException message;

    protected DbException(SQLException message) {
        this.message = message;
    }

}
