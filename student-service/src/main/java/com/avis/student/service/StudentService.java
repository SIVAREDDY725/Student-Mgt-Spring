package com.avis.student.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avis.student.model.Student;
import com.avis.student.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
@Slf4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Transactional
    public Student saveStudent(Student student){
        return studentRepository.save(student);
    }
    public Optional<Student> getStudent(Long studentId){
        return studentRepository.findById(studentId);
    }

}
