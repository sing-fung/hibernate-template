package com.singfung.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.singfung.demo.model.dto.UserDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sing-fung
 * @since 1/22/2023
 */

@Entity
@Table(name = "demo_user")
@Data
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "username", nullable = false, unique = true, length = 30)
    String username;

    @Column(name = "password", nullable = false, length = 256)
    String password;

    @Column(name = "email", nullable = false, unique = true, length = 80)
    String email;

    @Column(name = "create_time", nullable = false, length = 50)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createTime;

    @Column(name = "ts", nullable = false, length = 50)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date ts;

    public User(UserDTO dto) {
        BeanUtils.copyProperties(dto, this);
    }
}