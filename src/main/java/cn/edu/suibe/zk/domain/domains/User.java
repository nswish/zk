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

    private UserModel model;

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户信息鉴权
     *
     * @param userRepository
     * @param userName
     * @param password
     * @return
     */
    public static User authenticate(UserRepository userRepository, String userName, String password) {
        try {
            String passwordMD5 = DigestUtils.md5DigestAsHex(password.getBytes("UTF-8"));
            UserModel userModel = userRepository.findByUserName(userName);
            if(passwordMD5.equalsIgnoreCase(userModel.getPassword())) {
                return new User(userModel);
            } else {
                return User.AUTHENTICATE_FAILED_USER;
            }
        } catch (UnsupportedEncodingException e) {
            throw new DomainException("密码的Encoding不是UTF8", e);
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
        this.userRepository.save(this.model);
    }

    public User(UserModel model) {
        this.model = model;
    }

    public UserModel getModel() {
        return model;
    }
}
