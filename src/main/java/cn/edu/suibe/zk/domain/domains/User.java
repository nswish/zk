package cn.edu.suibe.zk.domain.domains;

import cn.edu.suibe.zk.common.exceptions.DomainException;
import cn.edu.suibe.zk.domain.models.UserModel;
import cn.edu.suibe.zk.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.stream.StreamSupport;

public class User {
    public static final User AUTHENTICATE_FAILED_USER = new User(null);
    public static final User AUTHENTICATE_NOTFOUND_USER = new User(null);

    private UserModel model;

    @Autowired
    private UserRepository userRepository;

    /**
     * 转换密码
     * @param password
     * @return
     */
    public static String convertPassword(String password) {
        try {
            String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
            return passwordMD5;
        } catch (UnsupportedEncodingException e) {
            throw new DomainException("转换密码失败", e);
        }
    }

    /**
     * 用户信息鉴权
     *
     * @param userRepository
     * @param userName
     * @param password
     * @return
     */
    public static User authenticate(UserRepository userRepository, String userName, String password) {
        String passwordMD5 = convertPassword(password);
        UserModel userModel = userRepository.findByUserName(userName);
        if(passwordMD5.equalsIgnoreCase(userModel.getPassword())) {
            return new User(userModel);
        } else {
            return User.AUTHENTICATE_FAILED_USER;
        }
    }

    /**
     * 查找所有的用户信息
     *
     * @param userRepository
     * @return
     */
    public static User[] findAllUsers(UserRepository userRepository) {
        User[] users = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(model -> new User(model))
                .toArray(User[]::new);
        return users;
    }

    /**
     * 查找指定ID的用户信息
     *
     * @param userRepository
     * @param id
     * @return
     */
    public static User findUserById(UserRepository userRepository, int id) {
        UserModel userModel = userRepository.findById(id);

        if(userModel == null) {
            return AUTHENTICATE_NOTFOUND_USER;
        }

        return new User(userModel);
    }

    /**
     * 创建一个空的用户对象
     *
     * @return
     */
    public static User newUser() {
        return new User(new UserModel());
    }

    /**
     * 保存用户对象
     */
    public void save() {
        if(this.model.getId() == 0) {
            UserModel userModel = this.userRepository.findByUserName(this.model.getUserName());

            if(userModel != null) {
                throw new DomainException(String.format("数据库中已存在[%s]用户!", this.model.getUserName()));
            }
        }

        this.userRepository.save(this.model);
    }

    /**
     * 删除用户信息
     */
    public void delete() {
        this.userRepository.delete(this.model);
    }

    public User(UserModel model) {
        this.model = model;
    }

    public UserModel getModel() {
        return model;
    }
}
