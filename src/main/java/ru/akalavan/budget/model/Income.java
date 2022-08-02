package ru.akalavan.budget.model;

/**
 * @author Вагин Михаил
 * date 01.08.2022
 */
public class Income {

    private int id;
    private String name;

    public Income(int id, String name) {
        this.id = id;
        this.name = name;
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
}
