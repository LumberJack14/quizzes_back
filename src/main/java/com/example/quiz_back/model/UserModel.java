package com.example.quiz_back.model;

public class UserModel {
    private Integer id;
    private String name;

    public static UserModel toModel(com.example.quiz_back.entity.User user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setName(user.getName());
        return model;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
