package com.avis.fee.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.avis.fee.api.response.*;
import com.avis.fee.api.request.PaymentRequest;
import com.avis.fee.api.request.StudentFeeRequest;
import com.avis.fee.exception.DataNotFoundException;
import com.avis.fee.fiegnclient.PaymentFeignClient;
import com.avis.fee.fiegnclient.StudentFiegnClient;
import com.avis.fee.model.StudentFeeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avis.fee.model.StudentFee;
import com.avis.fee.repository.StudentFeeRepository;

import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;


@Service
@Slf4j
public class StudentFeeService {
    @Autowired
    private StudentFeeRepository studentFeeRepository;

    @Autowired
    private PaymentFeignClient paymentFeignClient;

    @Autowired
    private StudentFiegnClient studentFiegnClient;

    public StudentFeeResponse payStudentFee(StudentFeeRequest studentFeeRequest){
        Student student = getStudent(studentFeeRequest.getStudentId());
        Payment payment=processPayment(studentFeeRequest);
        StudentFee studentFee=saveStudentFee(studentFeeRequest,payment.getPaymentId());

        return prepareStudentFeeResponse(student, payment, studentFee);
    }

    public Student getStudent(Long studentId){
        return studentFiegnClient.getStudent(studentId);
    }

    public Payment processPayment(StudentFeeRequest studentFeeRequest){
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .cardNumber(studentFeeRequest.getCardNumber())
                .cardType(studentFeeRequest.getCardType())
                .paymentType(studentFeeRequest.getPaymentType())
                .totalAmount(studentFeeRequest.getTotalAmount())
                .currencyType(studentFeeRequest.getCurrency())
                .build();
        return paymentFeignClient.processPayment(paymentRequest);
    }

    public Payment getPayment(Long paymentId){
     return paymentFeignClient.getPaymentDetails(paymentId);
    }

    @Transactional
    public StudentFee saveStudentFee(StudentFeeRequest studentFeeRequest,Long paymentId){
        List<StudentFeeTypes> feeTypesList = studentFeeRequest.getFeeTypes().getFeeTypes().stream().map(fee->{
            return StudentFeeTypes.builder().feeTypeName(fee.getFeeTypeName()).feeAmount(fee.getFeeAmount()).build();
        }).collect(Collectors.toList());

        StudentFee studentFee = StudentFee.builder().studentId(studentFeeRequest.getStudentId())
                .totalAmount(studentFeeRequest.getTotalAmount())
                .paymentStatus("SUCCESS")
                .currency(studentFeeRequest.getCurrency())
                .feeTypes(feeTypesList)
                .paymentId(paymentId)
                .build();
        return studentFeeRepository.save(studentFee);
    }

    public StudentFeeResponse getReceipt(Long receiptId){
        Optional<StudentFee> studentFeeOptional = studentFeeRepository.findById(receiptId);
        StudentFee studentFee = studentFeeOptional.orElseThrow(DataNotFoundException::new);
        Student student = getStudent(studentFee.getStudentId());
        if(student==null){
            throw new DataNotFoundException("Student data not found");
        }
        Payment payment=getPayment(studentFee.getPaymentId());
        if(payment==null){
            throw new DataNotFoundException("Payment data not found");
        }
        return prepareStudentFeeResponse(student, payment, studentFee);
    }

    public StudentFeeResponse prepareStudentFeeResponse(Student student, Payment payment, StudentFee studentFee){
        StudentFeeResponse studentFeeResponse = StudentFeeResponse.builder()
                .studentId(student.getStudentId())
                .studentName(student.getStudentName())
                .schoolName(student.getSchoolName())
                .grade(student.getGrade())
                .cardNumber(payment.getCardNumber())
                .paymentId(payment.getPaymentId())
                .paymentType(payment.getPaymentType())
                .paymentStatus(payment.getPaymentStatus())
                .cardType(payment.getCardType())
                .currency(payment.getCurrencyType())
                .totalAmount(payment.getTotalAmount())
                .receiptId(studentFee.getReceiptId())
                .feeTypes(prepareFeeTypesList(studentFee))
                .build();
        return studentFeeResponse;
    }

    public StudentFeeTypeResponseCollection prepareFeeTypesList(StudentFee studentFee){
        return StudentFeeTypeResponseCollection.builder()
                .feeTypes(
                        studentFee.getFeeTypes().stream().map( feeType -> {
                            return StudentFeeTypeResponse.builder()
                                    .feeTypeId(feeType.getFeeTypeId())
                                    .feeTypeName(feeType.getFeeTypeName())
                                    .feeAmount(feeType.getFeeAmount())
                                    .build();
                        }).collect(Collectors.toList())
                )
                .build();
    }

}
