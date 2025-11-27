package com.zensar.services;

import java.util.List;

import com.zensar.dto.Student;

public interface StudentService {
	public Student createStudent(Student student);
	public List<Student> getAllStudents();
}
