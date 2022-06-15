package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.database.DatabaseUtils;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TalkRepositoryImpl extends BasicRepositoryImpl<Talk> {
    private final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    public void save(Talk talk) {
        save(talk,
                "INSERT INTO `Talk` (`sourceUserId`, `targetUserId`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())",
                Statement.RETURN_GENERATED_KEYS, Long.toString(talk.getSourceUserId()),
                Long.toString(talk.getTargetUserId()), talk.getText());
    }

    protected Talk toElement(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }
        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    talk.setId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    talk.setSourceUserId(resultSet.getLong(i));
                    break;
                case "targetUserId":
                    talk.setTargetUserId(resultSet.getLong(i));
                    break;
                case "creationTime":
                    talk.setCreationTime(resultSet.getTimestamp(i));
                    break;
                case "text":
                    talk.setText(resultSet.getString(i));
                default:
                    // No operations.
            }
        }

        return talk;
    }

    public List<Talk> findAllByUserId(long userId) {
        List<Talk> talks = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM `Talk` WHERE sourceUserId=? OR targetUserId=? ORDER BY creationTime DESC")) {
                statement.setString(1, Long.toString(userId));
                statement.setString(2, Long.toString(userId));
                try (ResultSet resultSet = statement.executeQuery()) {
                    Talk talk;
                    while ((talk = toElement(statement.getMetaData(), resultSet)) != null) {
                        talks.add(talk);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Talk.", e);
        }
        return talks;
    }
}
