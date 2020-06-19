package com.rkubyshkin.sql;

import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionUtil {
    public ExceptionUtil() {

    }
    public static StorageException exceptionConvert(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
