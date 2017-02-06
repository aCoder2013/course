package com.song.repository;


import com.song.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

/**
 * Created by song on 2017/1/21.
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {

    @RestResource(path = "by-name-or-mobile")
    @Query("select u from User u where UPPER(u.name) = UPPER(:name) or u.mobile = :mobile")
    List<User> findByNameOrMobileIgnoreCase(@Param("name") String name, @Param("mobile") String mobile);

    Optional<User> findByMobile(String mobile);

    @Query("select u from User u where u.mobile = :mobile and u.password =:password")
    Optional<User> findByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    @Override
    @RestResource(exported = false)
    void deleteInBatch(Iterable<User> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAllInBatch();

    @Override
    @RestResource(exported = false)
    void delete(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(User user);

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends User> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}