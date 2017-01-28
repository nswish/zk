package cn.edu.suibe.zk.admin.domain.domains;

import cn.edu.suibe.zk.admin.domain.models.UserModel;

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
