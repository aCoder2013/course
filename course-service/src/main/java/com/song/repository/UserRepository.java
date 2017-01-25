package com.song.repository;


import com.song.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by song on 2017/1/21.
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, String> {

    @RestResource(path = "by-name")
    List<User> findByName(@Param("name") String name);
}