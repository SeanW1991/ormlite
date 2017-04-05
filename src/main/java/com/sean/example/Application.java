package com.sean.example;

import com.sean.example.database.DatabaseConstants;
import com.sean.example.database.DatabaseManager;
import com.sean.example.database.impl.AccountDepartmentRepository;
import com.sean.example.database.impl.AccountRepository;
import com.sean.example.database.impl.DepartmentRepository;
import com.sean.example.model.Account;
import com.sean.example.model.Department;
import io.reactivex.Flowable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Sean
 */
public final class Application {

    /**
     * Starts the application.
     * @param args The arguments of the application.
     */
    public static void main(String[] args) {
        try {
            new Application(Paths.get(DatabaseConstants.SQLITE_DATABASE_LOCATION)).start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The {@link DatabaseManager} for handling the sqlite database.
     */
    private final DatabaseManager databaseManager;

    private final AccountRepository accountRepository;

    private final DepartmentRepository departmentRepository;

    private final AccountDepartmentRepository accountDepartmentRepository;

    /**
     * Creates a new {@link Application} with the {@link Path} of the directory of the sqlite database.
     * @param path The path of the sqlite file.
     * @throws SQLException The exception thrown if an sql error occurs.
     */
    public Application(Path path) throws SQLException {
        this.databaseManager = new DatabaseManager(path);

        this.accountRepository = new AccountRepository(databaseManager);
        this.departmentRepository = new DepartmentRepository(databaseManager);
        this.accountDepartmentRepository = new AccountDepartmentRepository(databaseManager);

    }

    /**
     * Starts the application.
     */
    public void start() throws SQLException {

        String name = "sean";

        Flowable<Account> accountFlowable = accountRepository.findByNameAsFlowable(name);
        accountFlowable.subscribe(account -> System.out.println(account.getUsername()), throwable -> System.out.println(throwable.getMessage()));

        Flowable<List<Department>> departmentsFlowable = departmentRepository.findDepartmentByUsernameAsFlowable(name);
        departmentsFlowable.subscribe(departments -> departments.forEach(department -> System.out.println(department.getName())));

    }


}
