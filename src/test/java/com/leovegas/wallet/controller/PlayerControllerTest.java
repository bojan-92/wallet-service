package com.leovegas.wallet.controller;


import com.leovegas.wallet.error.WalletException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.service.PlayerService;
import com.leovegas.wallet.setup.Base;
import com.leovegas.wallet.setup.TestData;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class PlayerControllerTest extends Base {

  @Autowired
  private PlayerService playerService;

  @Test
  public void get_player_current_balance_should_return_200(){
    Player player = dataSetup.createPlayer(TestData.PLAYER_ID_1, TestData.PLAYER_NAME_1, TestData.PLAYER_BALANCE_1);

    RestAssured
        .given()
        .spec(getRequestSpecification(player.getId()))
        .when()
        .get(PLAYER_CURRENT_BALANCE_ENDPOINT)
        .prettyPeek()
        .then()
        .statusCode(200)
        .extract()
        .response()
        .getBody();
  }

  @Test
  public void get_player_current_balance_should_return_500(){
    RestAssured
        .given()
        .spec(getRequestSpecification(2L))
        .when()
        .get(PLAYER_CURRENT_BALANCE_ENDPOINT)
        .prettyPeek()
        .then()
        .statusCode(500)
        .extract()
        .response()
        .getBody();
  }

  @Test
  public void get_current_balance_service_should_return_404(){
    WalletException thrown = Assertions.assertThrows(WalletException.class, () -> {
      playerService.getCurrentBalanceForPlayer(2L);
    });

    Assertions.assertEquals(HttpStatus.NOT_FOUND, thrown.getHttpStatus());
  }

}
