package com.avis.fee.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.avis.fee.model.StudentFee;

@Repository
public interface StudentFeeRepository extends CrudRepository<StudentFee, Long> {

}
