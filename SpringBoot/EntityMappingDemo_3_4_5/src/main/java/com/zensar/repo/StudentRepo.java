package com.zensar.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.entity.many_to_many.StudentEntity;

public interface StudentRepo extends JpaRepository<StudentEntity, Long>{

}
