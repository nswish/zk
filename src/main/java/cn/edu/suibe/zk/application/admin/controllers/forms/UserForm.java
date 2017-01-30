package cn.edu.suibe.zk.application.admin.controllers.forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by ns on 2017/1/29.
 */
public class UserForm {
    private int id;

    @Size(min = 1, message = "必须输入用户名!")
    private String userName;

    @Size(min = 1, message = "必须设置密码!")
    private String password;

    private String password2;

    private String trueName;

    private String email;

    private String telephone;

    private int roleid;

    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
