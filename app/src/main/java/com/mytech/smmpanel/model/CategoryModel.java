package com.mytech.smmpanel.model;

import java.io.Serializable;

public class CategoryModel implements Serializable {

    String CategoryName, SelectStatus;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getSelectStatus() {
        return SelectStatus;
    }

    public void setSelectStatus(String selectStatus) {
        SelectStatus = selectStatus;
    }
}
