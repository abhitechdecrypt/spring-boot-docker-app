package com.example.student.service;

import java.util.List;
import java.util.Optional;

import com.example.student.model.Student;

public interface StudentService {

	List<Student> fetchAllVehicleLocations();

	Optional<Student> fetchVehicleLocationById(Integer id);
	
	Student createUser(Student Student);
	
	Student updateUser(Student Student, int id);
	
	boolean deleteUser(int id);

}
