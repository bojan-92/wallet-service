package com.leovegas.wallet.controller;

import com.leovegas.wallet.controller.dto.CurrentBalanceResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/player")
@Log4j2
public class PlayerController {

  @GetMapping(value = "/currentBalance", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CurrentBalanceResponse> getCurrentBalance(@RequestParam(value = "id") String id) {
    CurrentBalanceResponse currentBalanceResponse = new CurrentBalanceResponse();
    return new ResponseEntity<>(currentBalanceResponse, HttpStatus.OK);
  }

}
