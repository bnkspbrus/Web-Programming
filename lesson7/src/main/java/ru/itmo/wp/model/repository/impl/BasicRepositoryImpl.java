package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.BasicDomain;
import ru.itmo.wp.model.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.*;

public abstract class BasicRepositoryImpl<T extends BasicDomain> {
    protected final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    public T find(long id) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    String.format("SELECT * FROM %s WHERE id=?", getSimpleName()))) {
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return toDomain(statement.getMetaData(), resultSet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(String.format("Can't find %s.", getSimpleName()), e);
        }
    }

    protected abstract String getSimpleName();

    protected abstract T toDomain(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException;

    public void save(T domain) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(getSaveSql(),
                    Statement.RETURN_GENERATED_KEYS)) {
                setSaveStatementParams(statement, domain);
                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException(String.format("Can't save %s.", getSimpleName()));
                } else {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        domain.setId(generatedKeys.getLong(1));
                        domain.setCreationTime(find(domain.getId()).getCreationTime());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(String.format("Can't save %s.", getSimpleName()), e);
        }
    }

    protected abstract void setSaveStatementParams(PreparedStatement statement, T domain) throws SQLException;

    protected abstract String getSaveSql();
}
