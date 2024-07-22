package com.example.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.exceptions.StudentNotFound;
import com.example.student.model.Student;
import com.example.student.service.StudentServiceImpl;

@RestController
@RequestMapping("student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentServiceIml;

	@PostMapping("/")
	public ResponseEntity<Student> createNewUser(@RequestBody Student user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(studentServiceIml.createUser(user));
	}

	@GetMapping("/")
	public ResponseEntity<List<Student>> listUserData() {
		return ResponseEntity.ok(studentServiceIml.fetchAllVehicleLocations());
	}

	@GetMapping("{id}")
	public ResponseEntity<Student> fetchVehicleLocationById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(studentServiceIml.fetchVehicleLocationById(id).orElseThrow(
				() -> new StudentNotFound(String.format("Student not found for ID -> %d .", id))));
	}

	@PutMapping("{id}")
	public ResponseEntity<Student> updateUser(@RequestBody Student Student, @PathVariable(value = "id") int id) {
		return ResponseEntity.status(HttpStatus.OK).body(studentServiceIml.updateUser(Student,id));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id){
		boolean isDeletedUser = studentServiceIml.deleteUser(id);
		if(isDeletedUser){
			return ResponseEntity.status(HttpStatus.CREATED).body("Student deleted Successfully");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Failed to delete the user.");
	}
}
