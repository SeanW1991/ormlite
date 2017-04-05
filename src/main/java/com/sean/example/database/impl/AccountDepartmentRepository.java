package com.sean.example.database.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.sean.example.database.DatabaseConstants;
import com.sean.example.database.DatabaseManager;
import com.sean.example.database.Repository;
import com.sean.example.model.Account;
import com.sean.example.model.AccountDepartment;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * @author Sean
 */
public final class AccountDepartmentRepository extends Repository<AccountDepartment> {

    /**
     * @see {@link Repository}.
     */
    public AccountDepartmentRepository(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public Optional<AccountDepartment> findByName(String name) {
        throw new UnsupportedOperationException("Method unspported for this repository");
    }

    public Optional<List<AccountDepartment>> findByAccountName(String name) throws SQLException {
        return Optional.ofNullable(dao().query(queryAccountDepartment(name)));
    }

    @Override
    public Dao<AccountDepartment, Integer> dao() {
        return databaseManager.getAccountDepartmentDao();
    }

    /**
     * Querys a {@link AccountDepartment} based on their account username.
     * @param username The username of the account.
     * @return The {@link AccountDepartment} based on the account username.
     * @throws SQLException The exception thrown if an error occurs.
     */
    private PreparedQuery<AccountDepartment> queryAccountDepartment(String username) throws SQLException {
        SelectArg accountIdArgument = new SelectArg(DatabaseConstants.ACCOUNT_NAME_COLUMN, username);

        QueryBuilder<Account, Integer> accountQuery = databaseManager.getAccountDao().queryBuilder();
        accountQuery.selectColumns(DatabaseConstants.ACCOUNT_NAME_COLUMN).where().eq(DatabaseConstants.ACCOUNT_NAME_COLUMN, accountIdArgument);

        QueryBuilder<AccountDepartment, Integer> accountDepartmentQuery = dao().queryBuilder();
        accountDepartmentQuery.where().in(DatabaseConstants.DEPARTMENT_ACCOUNT_NAME_COLUMN, accountQuery);

        return accountDepartmentQuery.prepare();
    }



}
