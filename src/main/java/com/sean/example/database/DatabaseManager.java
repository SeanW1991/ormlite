package com.sean.example.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.sean.example.model.Account;
import com.sean.example.model.AccountDepartment;
import com.sean.example.model.Department;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;

/**
 * @author Sean
 */
public final class DatabaseManager {

    /**
     * The sqllite resource jdbc driver.
     */
    private static final String SQLLITE_JDBC = "jdbc:sqlite:";

    /**
     * The {@link JdbcPooledConnectionSource} for the SQLLite database.
     */
    private final JdbcPooledConnectionSource connectionSource;

    /**
     * The {@link Dao} for  the {@link Account}.
     */
    private final Dao<Account, Integer> accountDao;

    /**
     * The {@link Dao} for  the {@link Department}.
     */
    private final Dao<Department, Integer> departmentDao;

    /**
     * The {@link Dao} for  the {@link AccountDepartment}.
     */
    private final Dao<AccountDepartment, Integer> accountDepartmentDao;

    /**
     * Creates a new {@link DatabaseManager}.
     * @param path The {@link Path} of the sqlite database file.
     * @throws SQLException The exception thrown if an sql type error occurs.
     */
    public DatabaseManager(Path path) throws SQLException {
        System.out.println(path.toString());
        JdbcPooledConnectionSource connectionSource = new JdbcPooledConnectionSource(SQLLITE_JDBC + path.toString());
        connectionSource.setCheckConnectionsEveryMillis(60 * 1000);
        connectionSource.setTestBeforeGet(true);

        this.accountDao = DaoManager.createDao(connectionSource, Account.class);
        this.departmentDao = DaoManager.createDao(connectionSource, Department.class);
        this.accountDepartmentDao = DaoManager.createDao(connectionSource, AccountDepartment.class);

        this.connectionSource = connectionSource;

    }

    /**
     * Gets the {@link Dao} for the {@link Account}.
     * @return The {@code accountDao}.
     */
    public Dao<Account, Integer> getAccountDao() {
        return accountDao;
    }

    /**
     * Gets the {@link Department} {@link Dao}
     * @return The {@code accountDao}.
     */
    public Dao<Department, Integer> getDepartmentDao() {
        return departmentDao;
    }

    /**
     * Gets the {@link Dao} for the {@link AccountDepartment}.
     * @return The {@code accountDepartmentDao}.
     */
    public Dao<AccountDepartment, Integer> getAccountDepartmentDao() {
        return accountDepartmentDao;
    }

    /**
     * Closes the {@link JdbcPooledConnectionSource}.
     * @throws IOException The exception thrown if an i/o error occurs.
     */
    public void disconnect() throws IOException {
        connectionSource.close();
    }

}
