package com.singfung.demo.repository;

import com.singfung.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author sing-fung
 * @since 1/22/2023
 */

@Repository
public interface UserRepository extends JpaRepository<User, Serializable>
{}
