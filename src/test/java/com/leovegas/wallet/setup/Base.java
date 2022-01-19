package com.leovegas.wallet.setup;

import com.leovegas.wallet.WalletServiceApplication;
import com.leovegas.wallet.model.constant.TransactionType;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {WalletServiceApplication.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class Base {

  public static final String PLAYER_CURRENT_BALANCE_ENDPOINT = "/player/currentBalance";
  public static final String TRANSACTION_PLAYER_HISTORY_ENDPOINT = "/transaction/playerHistory";
  public static final String TRANSACTION_CREATE_ENDPOINT = "/transaction/create";

  @Autowired
  protected DataSetup dataSetup;

  @LocalServerPort
  int port;

  @BeforeEach
  public void setup() {
    RestAssured.port = port;
  }

  @AfterEach
  public void clearData() {
    dataSetup.cleanData();
  }

  public RequestSpecification getRequestSpecification(Long playerId) {
    RequestSpecBuilder builder = new RequestSpecBuilder();
    builder.addParam("playerId", playerId);
    return builder.build();
  }

  public Map<String, Object> getRequestBody(Long playerId, BigDecimal amount, TransactionType type) {
    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("playerId", playerId);
    requestBody.put("amount", amount);
    requestBody.put("type", type.getValue());
    return requestBody;
  }

}
