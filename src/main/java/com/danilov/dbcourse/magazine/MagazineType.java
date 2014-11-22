package com.danilov.dbcourse.magazine;

/**
 * Created by Семён on 22.11.2014.
 */
public enum MagazineType {

    GAZETTE("Газета"),
    MAGAZINE("Журнал");

    private String title;

    MagazineType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
