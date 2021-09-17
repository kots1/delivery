package com.delivery.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EntityMapper<T> {
    T mapRow(ResultSet rs) throws SQLException;
}
