package cn.edu.suibe.zk.domain.domains;

import cn.edu.suibe.zk.domain.models.UserModel;

public class User {
    public static final User LOGIN_ERROR_USER = new User(null);

    private UserModel model;

    public User(UserModel model) {
        this.model = model;
    }

    public UserModel getModel() {
        return model;
    }
}
