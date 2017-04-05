package com.sean.example.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import com.sean.example.database.DatabaseConstants;

/**
 * Created by sean on 03/04/2017.
 */
@DatabaseTable(tableName= DatabaseConstants.DEPARTMENT_TABLE_NAME)
public class Department {

    /**
     * The id of the department.
     */
    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private int id;

    /**
     * The name of the department.
     */
    @DatabaseField(columnName = DatabaseConstants.DEPARTMENT_TABLE_NAME_COLUMN, canBeNull = false)
    private String name;

    /**
     * The {@link ForeignCollection} of {@link AccountDepartment}s.
     */
    @ForeignCollectionField
    private ForeignCollection<AccountDepartment> departments;

    /**
     * Creates a new {@link Department}.
     * @param name The name of the department.
     * @param departments The collection of departments that are registered with an account.
     */
    public Department(String name, ForeignCollection<AccountDepartment> departments) {
        this.name = name;
        this.departments = departments;
    }

    /**
     * Default constructor for ormlite.
     */
    Department() {

    }

    /**
     * Gets the id.
     * @return The {@code id}.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     * @param id The id.
     */
    public void setId(int id) {
        this.id = id;
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
     * Gets the name of the department.
     * @return The {@code name}.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the department name.
     * @param name The name to update.
     */
    public void setName(String name) {
        this.name = name;
    }
}
