package com.avis.payment.controller;

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

import com.avis.payment.api.PaymentRequest;
import com.avis.payment.exception.DataNotFoundException;
import com.avis.payment.exception.ErrorResponse;
import com.avis.payment.model.Payment;
import com.avis.payment.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payment")
@Validated
@Slf4j
@Tag(name = "Payment Resource")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Payment Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Processing payment",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Payment.class)) }),
            @ApiResponse(responseCode = "400", description = "Required field is missing",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })})
    @PostMapping("/process")
    public Payment processPayment(@Valid @RequestBody PaymentRequest paymentRequest){
        logger.info("Process payment method:"+paymentRequest.toString());
        Payment payment = Payment.builder()
                .cardNumber(paymentRequest.getCardNumber())
                .cardType(paymentRequest.getCardType())
                .paymentType(paymentRequest.getPaymentType())
                .totalAmount(paymentRequest.getTotalAmount())
                .currencyType(paymentRequest.getCurrencyType())
                .build();
        return paymentService.savePayment(payment);
    }


    @Operation(summary = "Get Payment Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Payment Details",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Payment.class)) }),
            @ApiResponse(responseCode = "400", description = "Required field is missing",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "404", description = "data not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)) })})

    @GetMapping("/{paymentId}")
    public Payment getPayment(@Parameter(description = "Id of payment to be searched")@PathVariable @NotNull Long paymentId){
        logger.info("Get payment Details:"+paymentId);
        Optional<Payment> payment = paymentService.getPayment(paymentId);
        return payment.orElseThrow(DataNotFoundException::new);
    }

}
