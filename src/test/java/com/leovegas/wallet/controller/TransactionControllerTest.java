package com.leovegas.wallet.controller;

import com.leovegas.wallet.controller.dto.CreateTransactionRequest;
import com.leovegas.wallet.error.WalletException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.model.constant.TransactionType;
import com.leovegas.wallet.service.TransactionService;
import com.leovegas.wallet.setup.Base;
import com.leovegas.wallet.setup.TestData;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class TransactionControllerTest extends Base {

  @Autowired
  private TransactionService transactionService;

  @Test
  public void get_empty_transaction_player_history_should_return_200() {
    Player player = dataSetup.createPlayer(TestData.PLAYER_ID_1, TestData.PLAYER_NAME_1, TestData.PLAYER_BALANCE_1);

    RestAssured
        .given()
        .spec(getRequestSpecification(player.getId()))
        .when()
        .get(TRANSACTION_PLAYER_HISTORY_ENDPOINT)
        .prettyPeek()
        .then()
        .statusCode(200)
        .extract()
        .response()
        .getBody();
  }

  @Test
  public void get_transaction_player_history_should_return_200() {
    Player player = dataSetup.createPlayer(TestData.PLAYER_ID_1, TestData.PLAYER_NAME_1, TestData.PLAYER_BALANCE_1);
    dataSetup
        .createTransaction(TestData.TRANSACTION_ID_1, TransactionType.CREDIT,
            TestData.TRANSACTION_AMOUNT_GREATER_THEN_BALANCE_1, player);

    RestAssured
        .given()
        .spec(getRequestSpecification(player.getId()))
        .when()
        .get(TRANSACTION_PLAYER_HISTORY_ENDPOINT)
        .prettyPeek()
        .then()
        .statusCode(200)
        .extract()
        .response()
        .getBody();
  }

  @Test
  public void create_credit_transaction_should_return_201() {
    Player player = dataSetup.createPlayer(TestData.PLAYER_ID_1, TestData.PLAYER_NAME_1, TestData.PLAYER_BALANCE_1);

    RestAssured
        .given()
        .header("Content-Type", "application/json")
        .body(
            getRequestBody(player.getId(), TestData.TRANSACTION_AMOUNT_GREATER_THEN_BALANCE_1, TransactionType.CREDIT))
        .when()
        .post(TRANSACTION_CREATE_ENDPOINT)
        .prettyPeek()
        .then()
        .statusCode(201)
        .extract()
        .response()
        .getBody();
  }

  @Test
  public void create_debit_transaction_amount_lower_then_user_balance_should_return_201() {
    Player player = dataSetup.createPlayer(TestData.PLAYER_ID_1, TestData.PLAYER_NAME_1, TestData.PLAYER_BALANCE_1);

    RestAssured
        .given()
        .header("Content-Type", "application/json")
        .body(getRequestBody(player.getId(), TestData.TRANSACTION_AMOUNT_LOWER_THEN_BALANCE_1, TransactionType.DEBIT))
        .when()
        .post(TRANSACTION_CREATE_ENDPOINT)
        .prettyPeek()
        .then()
        .statusCode(201)
        .extract()
        .response()
        .getBody();
  }

  @Test
  public void create_debit_transaction_amount_equals_to_user_balance_should_return_201() {
    Player player = dataSetup.createPlayer(TestData.PLAYER_ID_1, TestData.PLAYER_NAME_1, TestData.PLAYER_BALANCE_1);

    RestAssured
        .given()
        .header("Content-Type", "application/json")
        .body(getRequestBody(player.getId(), TestData.PLAYER_BALANCE_1, TransactionType.DEBIT))
        .when()
        .post(TRANSACTION_CREATE_ENDPOINT)
        .prettyPeek()
        .then()
        .statusCode(201)
        .extract()
        .response()
        .getBody();
  }

  @Test
  public void create_debit_transaction_amount_greater_then_user_balance_should_return_400() {
    WalletException thrown = Assertions.assertThrows(WalletException.class, () -> {
      Player player = dataSetup.createPlayer(TestData.PLAYER_ID_1, TestData.PLAYER_NAME_1, TestData.PLAYER_BALANCE_1);
      CreateTransactionRequest request = CreateTransactionRequest.builder().playerId(player.getId())
          .amount(TestData.TRANSACTION_AMOUNT_GREATER_THEN_BALANCE_1)
          .type(TransactionType.DEBIT.getValue()).build();
      transactionService.create(request);
    });

    Assertions.assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());
  }

}
