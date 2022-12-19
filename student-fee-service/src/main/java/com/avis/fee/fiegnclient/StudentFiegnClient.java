package com.avis.fee.fiegnclient;

import com.avis.fee.api.response.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "STUDENT-SERVICE", configuration = FeignConfiguration.class, fallbackFactory = StudentFeignClientFallbackFactory.class)
public interface StudentFiegnClient {
	@GetMapping("/student/{studentId}")
	Student getStudent(@PathVariable(value = "studentId") Long studentId);
}
