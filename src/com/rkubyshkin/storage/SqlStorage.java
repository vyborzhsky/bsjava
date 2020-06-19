package com.rkubyshkin.storage;

import com.rkubyshkin.exception.ExistStorageException;
import com.rkubyshkin.exception.NotExistStorageException;
import com.rkubyshkin.exception.StorageException;
import com.rkubyshkin.model.Person;
import com.rkubyshkin.sql.ConnectionFactory;
import com.rkubyshkin.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String databaseUrl, String databaseUser, String databasePassword) {
        sqlHelper  = new SqlHelper(() -> DriverManager.getConnection(databaseUrl, databaseUser, databasePassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM person");
    }

    @Override
    public Person get(String uid) {
        return sqlHelper.execute("SELECT * FROM person p WHERE p.uid = ?", ps-> {
            ps.setString(1, uid);
            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) {
                throw new NotExistStorageException(uid);
            }
            Person p = new Person(uid, resultSet.getString("full_name"));
            return p;
        });
    }

    @Override
    public void save(Person r) {
        sqlHelper.<Void>execute("INSERT INTO person (uid, full_name) VALUES (?,?)", ps-> {
            ps.setString(1, r.getUid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
    }



    @Override
    public void delete(String uid) {
        sqlHelper.execute("DELETE FROM person p WHERE p.uid = ?", ps-> {
            ps.setString(1, uid);
            if(ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uid);
            };
            return null;
        });
    }

    @Override
    public void update(Person r) {
        sqlHelper.execute("UPDATE person SET full_name = ? WHERE uid = ?", ps-> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUid());
            if(ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUid());
            };
            return null;
        });
    }

    @Override
    public List<Person> getAllSorted() {
        return sqlHelper.execute("SELECT uid AS UID, full_name AS FULLNAME FROM person p ORDER BY FULLNAME,UID", ps-> {
            ResultSet resultSet = ps.executeQuery();
            List<Person> list = new ArrayList<>();
            while (resultSet.next()) {
                String uid = resultSet.getString("UID");
                String full_name = resultSet.getString("FULLNAME");
                Person p = new Person(uid, full_name);
                list.add(p);}
            return list;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(*) FROM person", ps-> {
           ResultSet rs = ps.executeQuery();
           return rs.next() ? rs.getInt(1) : 0;
        });
    }
}
