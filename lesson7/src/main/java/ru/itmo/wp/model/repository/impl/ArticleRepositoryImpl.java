package ru.itmo.wp.model.repository.impl;


import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.ArticleRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl extends BasicRepositoryImpl<Article> implements ArticleRepository {

    public Article toDomain(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {

        if (!resultSet.next()) {
            return null;
        }
        Article article = new Article();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    article.setId(resultSet.getLong(i));
                    break;
                case "userId":
                    article.setUserId(resultSet.getLong(i));
                    break;
                case "title":
                    article.setTitle(resultSet.getString(i));
                    break;
                case "text":
                    article.setText(resultSet.getString(i));
                    break;
                case "creationTime":
                    article.setCreationTime(resultSet.getTimestamp(i));
                    break;
                case "hidden":
                    article.setHidden(Article.Status.valueOf(resultSet.getString(i)));
                default:
                    // No operations.
            }
        }

        return article;
    }

    @Override
    protected void setSaveStatementParams(PreparedStatement statement, Article domain) throws SQLException {
        statement.setLong(1, domain.getUserId());
        statement.setString(2, domain.getTitle());
        statement.setString(3, domain.getText());
    }

    @Override
    protected String getSaveSql() {
        return "INSERT INTO Article (`userId`, `title`, `text`, `creationTime`) VALUES (?, ?, ?, NOW())";
    }

    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Article WHERE hidden=\"SHOW\" ORDER BY creationTime DESC")) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    Article article;
                    while ((article = toDomain(statement.getMetaData(), resultSet)) != null) {
                        articles.add(article);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.", e);
        }
        return articles;
    }

    public List<Article> findAllByUserId(long userId) {
        List<Article> articles = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Article WHERE userid=? ORDER BY creationTime DESC")) {
                statement.setLong(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    Article article;
                    while ((article = toDomain(statement.getMetaData(), resultSet)) != null) {
                        articles.add(article);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.", e);
        }
        return articles;
    }

    private static final String NAME = Article.class.getSimpleName();

    @Override
    protected String getSimpleName() {
        return NAME;
    }

    public void invertStatus(Article article) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE Article SET hidden=? WHERE id=?")) {
                statement.setString(1, article.getHidden().toString());
                statement.setLong(2, article.getId());
                if (statement.executeUpdate() != 1) {
                    throw new RepositoryException("Can't update Article.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't update Article.", e);
        }
    }
}
