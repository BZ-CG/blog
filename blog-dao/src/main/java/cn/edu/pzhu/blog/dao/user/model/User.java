package cn.edu.pzhu.blog.dao.user.model;


import lombok.Data;

import java.io.Serializable;

/**
 * @author CG-PC
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1470076345146614129L;
    private Integer id;
    private String name;
    private String password;
}
