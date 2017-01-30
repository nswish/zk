package cn.edu.suibe.zk.domain.models;

import javax.persistence.*;

/**
 * Created by ns on 2017/1/8.
 */
@Entity
@Table(name = "tb_Users")
public class UserModel {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String userName;
    private String password;
    private String trueName;
    private String email;
    private String telephone;

    /**
     * 用户角色
     * 0 - 会员 | 1 - 管理员
     */
    private int roleid;

    /**
     * 用户状态
     * 0 - 未审核 | 1 - 已审核
     */
    private int state;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
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
}
