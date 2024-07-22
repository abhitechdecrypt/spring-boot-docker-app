package com.example.student.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.exceptions.StudentNotFound;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> fetchAllVehicleLocations() {
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> fetchVehicleLocationById(Integer id) {
		Student userData = studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFound("User Not Found"));
		LOGGER.info("Student data By ID :: "+ userData);
		return Optional.of(userData);
	}

	@Override
	public Student createUser(Student Student) {
		Student save = studentRepository.save(Student);
		
		LOGGER.info("Student Created");
		return save;
	}

	@Override
	public Student updateUser(Student Student, int id) {
	    Student user = studentRepository.findById(id)
	        .orElseThrow(() -> new StudentNotFound("User Not Found"));

	    // Copy the fields from Student to user, except for the id and dateOfCreation
	    user.setName(Student.getName());
	    user.setRollNumber(Student.getRollNumber());
	    // Keep the original dateOfCreation

	    // Save the updated user
	    
	    LOGGER.info("Student data got updated :: => "+user);
	    return studentRepository.save(user);
	}


	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		Student user = studentRepository.findById(id).orElseThrow(() -> new StudentNotFound("User Not Found"));
		boolean userDeletedFlag = false;
		if (user != null) {
			studentRepository.delete(user);
			userDeletedFlag=true;
		}
		LOGGER.info("Student Data got Deleted => "+user);
		return userDeletedFlag;
	}

}
