package com.avis.student.controller;


import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avis.student.api.StudentRequest;
import com.avis.student.exception.DataNotFoundException;
import com.avis.student.exception.ErrorResponse;
import com.avis.student.model.Student;
import com.avis.student.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Validated
@Slf4j
@Tag(name = "Student Resource")
public class StudentRegistrationController {
    private static final Logger logger = LoggerFactory.getLogger(StudentRegistrationController.class);
    @Autowired
    private StudentService studentService;
    @Operation(summary = "Register Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Register a Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "400", description = "Required field is missing",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })})
    @PostMapping("/registration")
    public Student registerStudent(@Valid @RequestBody StudentRequest studentRequest){
        logger.info("Register Student method: "+studentRequest.toString());
        Student student = Student.builder()
                .studentName(studentRequest.getStudentName())
                .mobileNumber(studentRequest.getMobileNumber())
                .schoolName(studentRequest.getSchoolName())
                .grade(studentRequest.getGrade())
                .build();
        return studentService.saveStudent(student);
    }


    @Operation(summary = "Get Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class)) }),
            @ApiResponse(responseCode = "400", description = "Required field is missing",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "data not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })})
    @GetMapping("/{studentId}")
    public Student getStudent(@Parameter(description = "id of student to be searched")@PathVariable @NotNull Long studentId){
        logger.info("Get Student Details: "+studentId);
        Optional<Student> student = studentService.getStudent(studentId);
        return student.orElseThrow(DataNotFoundException::new);
    }
}
