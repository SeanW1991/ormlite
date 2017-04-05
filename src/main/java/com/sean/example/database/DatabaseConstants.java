package com.sean.example.database;

/**
 * @author Sean
 */
public final class DatabaseConstants {

    /**
     * The location of the database.
     */
    public static final String SQLITE_DATABASE_LOCATION = "./db/db.sqlite";

    /**
     * The name of the department column.
     */
    public static final String DEPARTMENT_TABLE_NAME = "departments";

    /**
     * The name of the department column.
     */
    public static final String DEPARTMENT_TABLE_NAME_COLUMN = "name";

    /**
     * The id of the account tablet.
     */
    public static final String ID_TABLE_NAME = "id";

    /**
     * The username of the account tablet.
     */
    public static final String ACCOUNT_TABLE_NAME = "accounts";

    /**
     * The name of the username column.
     */
    public static final String ACCOUNT_NAME_COLUMN = "username";

    /**
     * The name of the password column
     */
    public static final String PASSWORD_NAME_COLUMN = "password";

    /**
     * The account of the account tablet.
     */
    public static final String ACCOUNT_DEPARTMENT_TABLE_NAME = "user_departments";

    /**
     * The name of the account column.
     */
    public static final String DEPARTMENT_ACCOUNT_NAME_COLUMN = "account_id";

    /**
     * The name of the department column
     */
    public static final String DEPARTMENT_NAME_COLUMN = "department_id";

    /**
     * Private constructor so the class cannot be initialised.
     */
    private DatabaseConstants() {

    }

}
