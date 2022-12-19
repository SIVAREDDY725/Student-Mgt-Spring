package com.avis.fee.fiegnclient;

import com.avis.fee.api.request.PaymentRequest;
import com.avis.fee.api.response.Payment;
import com.avis.fee.api.response.Student;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class StudentFeignClientFallbackFactory implements FallbackFactory<StudentFiegnClient> {
    @Override
    public StudentFiegnClient create(Throwable cause) {
        return new StudentFiegnClient() {
            @Override
            public Student getStudent(Long studentId) {
                throw new RuntimeException("Student service is down, try after some time!");
            }
        };
    }
}
