package cn.edu.suibe.zk.admin.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ns on 2017/1/8.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;

    private String name;
}
