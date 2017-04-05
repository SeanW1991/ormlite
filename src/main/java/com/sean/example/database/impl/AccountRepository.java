package com.sean.example.database.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.sean.example.database.DatabaseConstants;
import com.sean.example.database.DatabaseManager;
import com.sean.example.database.Repository;
import com.sean.example.model.Account;

import java.sql.SQLException;
import java.util.Optional;

/**
 * @author Sean
 */
public final class AccountRepository extends Repository<Account> {

    /**
     * see {@link Repository} constructor.
     */
    public AccountRepository(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public Optional<Account> findByName(String username) throws SQLException {
        return Optional.ofNullable(dao().queryForFirst(queryByUsername(new SelectArg(DatabaseConstants.ACCOUNT_NAME_COLUMN, username))));
    }

    @Override
    public Dao<Account, Integer> dao() {
        return databaseManager.getAccountDao();
    }

    /**
     * The {@link PreparedQuery} for selecting a {@link Account} table by its username.
     * @param username The {@link SelectArg} for the name.
     * @return A new secure {@link PreparedQuery}.
     * @throws SQLException The sql exception when an error occurs.
     */
    private PreparedQuery<Account> queryByUsername(SelectArg username) throws SQLException {
        QueryBuilder<Account, Integer> nameQuery = dao().queryBuilder();
        nameQuery.where().eq(DatabaseConstants.ACCOUNT_NAME_COLUMN, username);
        return nameQuery.prepare();
    }

}
