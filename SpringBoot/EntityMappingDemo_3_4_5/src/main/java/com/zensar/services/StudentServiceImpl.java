package com.zensar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.dto.Student;
import com.zensar.entity.many_to_many.StudentEntity;
import com.zensar.repo.StudentRepo;
import com.zensar.util.StudentUtil;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public Student createStudent(Student student) {
		StudentEntity studentEntity = StudentUtil.convertStudentIntoStudentEntity(student);
		studentEntity = studentRepo.save(studentEntity);
		return StudentUtil.convertStudentEntityIntoStudent(studentEntity);
	}

	@Override
	public List<Student> getAllStudents() {
		return StudentUtil.convertStudentEntityListIntoStudentList(studentRepo.findAll());
	}

}
