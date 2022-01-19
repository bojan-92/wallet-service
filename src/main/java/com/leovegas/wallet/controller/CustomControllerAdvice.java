package com.leovegas.wallet.controller;

import com.leovegas.wallet.error.ErrorResponse;
import com.leovegas.wallet.error.WalletException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice {

  @ExceptionHandler(WalletException.class)
  public ResponseEntity<ErrorResponse> handleWalletException(WalletException e) {
    return new ResponseEntity<>(
        ErrorResponse.builder().status(e.getHttpStatus()).message(e.getMessage()).build(),
        e.getHttpStatus()
    );
  }

}
