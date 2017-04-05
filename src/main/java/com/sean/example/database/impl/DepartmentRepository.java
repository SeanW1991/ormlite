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
import com.sean.example.model.Department;
import io.reactivex.Flowable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * @author Sean
 */
public final class DepartmentRepository extends Repository<Department> {

    /**
     * The error message when a username does not exist.
     */
    private static final String INVALID_USERNAME_OPTIONAL = "Username %s does not exist";

    /**
     * see {@link Repository} constructor.
     */
    public DepartmentRepository(DatabaseManager databaseManager) {
        super(databaseManager);
    }

    @Override
    public Optional<Department> findByName(String name) throws SQLException {
        return Optional.ofNullable(dao().queryForFirst(queryByName(new SelectArg(DatabaseConstants.DEPARTMENT_TABLE_NAME_COLUMN, name))));
    }

    /**
     * Finds the {@link List} of {@link Department}s assigned to a {@link Account} based on the username.
     * @param username The username of the {@link Account}.
     * @return An {@link Optional} wrapping a {@link List} of {@link Department}s.
     * @throws SQLException The exception thrown if an sql error occurs.
     */
    public List<Department> findDepartmentsByUsername(String username) throws SQLException {
        return dao().query(queryDepartmentByAccount(username));
    }

    @Override
    public Dao<Department, Integer> dao() {
        return databaseManager.getDepartmentDao();
    }

    /**
     * Finds a {@link List} of {@link Department}s based on an {@link Account} username.
     * @param username The username of the account.
     * @return A {@link Flowable} wrapping the results.
     */
    public Flowable<List<Department>> findDepartmentByUsernameAsFlowable(String username) {
        return Flowable.fromCallable(new Callable<List<Department>>() {
            @Override
            public List<Department> call() throws Exception {
                return DepartmentRepository.this.findDepartmentsByUsername(username);
            }
        });
    }

    /**
     * The {@link PreparedQuery} for selecting a {@link Department} table by its name.
     * @param name The {@link SelectArg} for the name.
     * @return A new secure {@link PreparedQuery}.
     * @throws SQLException The sql exception when an error occurs.
     */
    private PreparedQuery<Department> queryByName(SelectArg name) throws SQLException {
        QueryBuilder<Department, Integer> nameQuery = dao().queryBuilder();
        nameQuery.where().eq(DatabaseConstants.DEPARTMENT_TABLE_NAME_COLUMN, name);
        return nameQuery.prepare();
    }

    /**
     * Queries the database to select all the {@link Department}s related to a {@link Account} based on its username.
     * @param username The username of the account.
     * @return A {@link PreparedQuery} with the build query.
     * @throws SQLException The exception thrown if an error sql occurs.
     */
    private PreparedQuery<Department> queryDepartmentByAccount(String username) throws SQLException {
        SelectArg accountIdArgument = new SelectArg(DatabaseConstants.ACCOUNT_NAME_COLUMN, username);

        /**
         * Queries the account table based on the their username.
         */
        QueryBuilder<Account, Integer> accountQuery = databaseManager.getAccountDao().queryBuilder();
        accountQuery.selectColumns(DatabaseConstants.ID_TABLE_NAME).where().eq(DatabaseConstants.ACCOUNT_NAME_COLUMN, accountIdArgument);

        /**
         * Queries the department table on the link between the account and department.
         */
        QueryBuilder<AccountDepartment, Integer> accountDepartmentQuery = databaseManager.getAccountDepartmentDao().queryBuilder();
        accountDepartmentQuery.selectColumns(DatabaseConstants.DEPARTMENT_NAME_COLUMN).where().in(DatabaseConstants.DEPARTMENT_ACCOUNT_NAME_COLUMN, accountQuery);

        /**
         * Selects the department based the link between the account department query and the department.
         */
        QueryBuilder<Department, Integer> departmentQuery = dao().queryBuilder();
        departmentQuery.where().in(DatabaseConstants.ID_TABLE_NAME, accountDepartmentQuery);


        return departmentQuery.prepare();
    }

}
