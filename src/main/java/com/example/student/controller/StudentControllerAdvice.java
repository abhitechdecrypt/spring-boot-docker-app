package com.example.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.student.exceptions.StudentNotFound;

@RestControllerAdvice
public class StudentControllerAdvice {
	
	@ExceptionHandler(StudentNotFound.class)
	public ResponseEntity<String> studentNotFoundHandler(StudentNotFound ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
