package com.leovegas.wallet.controller;

import com.leovegas.wallet.controller.dto.CurrentBalanceResponse;
import com.leovegas.wallet.service.PlayerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @GetMapping(value = "/player/currentBalance", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CurrentBalanceResponse> getCurrentBalance(@RequestParam(value = "id") Long id) {
    return new ResponseEntity<>(playerService.getCurrentBalanceForPlayer(id), HttpStatus.OK);
  }

}
