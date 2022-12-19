package com.avis.fee.fiegnclient;

import com.avis.fee.controller.StudentFeeController;
import com.avis.fee.exception.BadRequestException;
import com.avis.fee.exception.DataNotFoundException;
import com.avis.fee.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
@Slf4j
public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(StudentFeeController.class);
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.status() == 503){
            throw new RuntimeException("Internal Service is down, please try after some time");
        }
        ErrorResponse errorResponse;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            errorResponse = mapper.readValue(bodyIs, ErrorResponse.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
            case 400:
                return new BadRequestException(errorResponse.getMessage().toString());
            case 404:
                return new DataNotFoundException(errorResponse.getMessage().toString());
            case 500:
                return new RuntimeException();
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
