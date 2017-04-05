package com.sean.example.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.sean.example.database.DatabaseConstants;

import java.io.Serializable;

/**
 * Created by sean on 03/04/2017.
 */
@DatabaseTable(tableName=DatabaseConstants.ACCOUNT_TABLE_NAME)
public class Account implements Serializable {

    /**
     * The id of the account in the database.
     */
    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    /**
     * The username of the account.
     */
    @DatabaseField(columnName = DatabaseConstants.ACCOUNT_NAME_COLUMN, canBeNull = false)
    private String username;

    /**
     * The password of the account.
     */
    @DatabaseField(columnName = DatabaseConstants.PASSWORD_NAME_COLUMN, canBeNull = false)
    private String password;

    /**
     * The {@link ForeignCollection} of {@link AccountDepartment}s.
     */
    @ForeignCollectionField
    private ForeignCollection<AccountDepartment> departments;

    /**
     * Creates a new {@link Account}.
     * @param username The username to be set.
     * @param password The password to be set.
     */
    public Account(String username, String password, ForeignCollection<AccountDepartment> departments) {
        this.username = username;
        this.password = password;
        this.departments = departments;
    }

    /**
     * Empty constructor for ormlite.
     */
    Account() {

    }

    /**
     * Gets the id of the account.
     * @return The {@code id}.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the username of the account.
     * @return The {@code username}.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password of the account.
     * @return The {@code password}.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the {@link ForeignCollection} of {@link AccountDepartment}s.
     * @return The {@code AccountDepartment}.
     */
    public ForeignCollection<AccountDepartment> getDepartments() {
        return departments;
    }

    /**
     * Sets the {@link ForeignCollection} of {@link AccountDepartment}s.
     * @return The {@code AccountDepartment} to be set.
     */
    public void setDepartments(ForeignCollection<AccountDepartment> departments) {
        this.departments = departments;
    }

    /**
     * Sets the id of the account.
     * @param id The id to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the username of the account.
     * @param username The username to be set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of the account.
     * @param password The username to be set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
