package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
    T get(int id);
    T getFromResultSet(ResultSet result_set) throws SQLException;
    List<T> getAll();
    boolean insert(T t);
    boolean update(T t, String[] params);
    boolean delete(int id);
}
