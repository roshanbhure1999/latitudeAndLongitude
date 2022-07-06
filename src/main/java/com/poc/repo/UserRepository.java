package com.poc.repo;

import com.poc.constant.ConstantType;
import com.poc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = " SELECT * FROM User WHERE salary >=?", nativeQuery = true)
    List<UserEntity> findBySalary(double salary);

    List<UserEntity> findByType(ConstantType s);
}
