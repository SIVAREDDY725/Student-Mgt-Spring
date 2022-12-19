package com.avis.fee.controller;


import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.avis.fee.api.response.StudentFeeResponse;
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

import com.avis.fee.api.request.StudentFeeRequest;
import com.avis.fee.exception.DataNotFoundException;
import com.avis.fee.exception.ErrorResponse;
import com.avis.fee.model.StudentFee;
import com.avis.fee.service.StudentFeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/feePayment")
@Validated
@Slf4j
@Tag(name = "Student Fee Resource")
public class StudentFeeController {
    private static final Logger logger = LoggerFactory.getLogger(StudentFeeController.class);
    @Autowired
    private StudentFeeService studentFeeService;

    @Operation(summary = "Student Fee Payment Process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Process Payment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentFeeResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Required field is missing",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })})
    @PostMapping("/pay")
    public StudentFeeResponse feePayment(@Valid @RequestBody StudentFeeRequest studentFeeRequest){
        logger.info("Fee Payment : "+studentFeeRequest.toString());

        return studentFeeService.payStudentFee(studentFeeRequest);
    }


    @Operation(summary = "Get Payment Receipt")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a Student",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StudentFee.class)) }),
            @ApiResponse(responseCode = "400", description = "Required field is missing",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "data not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })})
    @GetMapping("/{receiptId}")
    public StudentFeeResponse getReceipt(@Parameter(description = "id of receipt to be searched")@PathVariable @NotNull Long receiptId){
       try{
           return studentFeeService.getReceipt(receiptId);
       }catch (DataNotFoundException e){
           logger.error("ERROR in GetReceipt Method: ", e);
           throw new DataNotFoundException();
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }
}
