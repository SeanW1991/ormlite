package com.sean.example.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sean.example.database.DatabaseConstants;

/**
 * @author Sean
 */
@DatabaseTable(tableName= DatabaseConstants.ACCOUNT_DEPARTMENT_TABLE_NAME)
public class AccountDepartment {

    /**
     * The id of the account in the database.
     */
    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    /**
     * The account reference.
     */
    @DatabaseField(columnName = DatabaseConstants.DEPARTMENT_ACCOUNT_NAME_COLUMN, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, canBeNull = false)
    private Account account;

    /**
     * The department reference.
     */
    @DatabaseField(columnName = DatabaseConstants.DEPARTMENT_NAME_COLUMN, foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true ,canBeNull = false)
    private Department department;

    /**
     * Creates a new {@link AccountDepartment}.
     * @param account The account to be set.
     * @param department The department to be set.
     */
    public AccountDepartment(Account account, Department department) {
        this.account = account;
        this.department = department;
    }

    /**
     * Default constructor for ormlite.
     */
    AccountDepartment() {

    }

    /**
     * Gets the id of the account.
     * @return The {@code id}.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the account.
     * @return The {@code account}.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Gets the department of the account.
     * @return The {@code department}.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Sets the id of the account.
     * @param id The id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the account.
     * @param account The account to be set.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Sets the department of the account.
     * @param department The account to be set.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
}
