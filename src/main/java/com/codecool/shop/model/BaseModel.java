package com.codecool.shop.model;


import java.lang.reflect.Field;


/**
 * A BaseModel from which Product, ProductCategory and Supplier inherits. Has two constructors.
 */
public class BaseModel {

    protected int id;
    protected String name;
    protected String description;

    /**
     * Constructor which only needs a name parameter.
     * @param name Name of the Model.
     */
    public BaseModel(String name) {
        this.name = name;
    }

    /**
     * Constructor which needs name and description.
     * @param name Name of the Model.
     * @param description Description of the Model.
     */
    public BaseModel(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
                if (value != null) {
                    sb.append(field.getName() + ":" + value + ",");
                }
            } catch (IllegalAccessException e) {

            }
        }
        return sb.toString();
    }

}
