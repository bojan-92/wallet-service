package com.leovegas.wallet.controller;

import com.leovegas.wallet.controller.dto.CreateTransactionRequest;
import com.leovegas.wallet.controller.dto.PlayerTransactionHistoryResponse;
import com.leovegas.wallet.model.constant.TransactionType;
import com.leovegas.wallet.service.TransactionService;
import java.math.BigDecimal;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @GetMapping(value = "/playerHistory", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PlayerTransactionHistoryResponse> getPlayerTransactionHistory(
      @RequestParam(value = "playerId") String playerId) {
    PlayerTransactionHistoryResponse playerTransactionHistoryResponse = new PlayerTransactionHistoryResponse();
    return new ResponseEntity<>(playerTransactionHistoryResponse, HttpStatus.OK);
  }

  @PostMapping(value = "/transaction/create", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createTransaction(@Valid @RequestBody CreateTransactionRequest request) {
    transactionService.create(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
