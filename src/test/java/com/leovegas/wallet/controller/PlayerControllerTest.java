package com.leovegas.wallet.controller;


import com.leovegas.wallet.error.WalletException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.service.PlayerService;
import com.leovegas.wallet.setup.Base;
import com.leovegas.wallet.setup.TestData;
import io.restassured.RestAssured;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class PlayerControllerTest extends Base {

  @Test
  public void get_player_current_balance_should_return_200() {
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
  public void get_current_balance_for_non_existing_user_should_return_404() {
    Random rand = new Random();
    Long randId = rand.nextLong();

    RestAssured
        .given()
        .spec(getRequestSpecification(randId))
        .when()
        .get(PLAYER_CURRENT_BALANCE_ENDPOINT)
        .prettyPeek()
        .then()
        .statusCode(404)
        .extract()
        .response()
        .getBody();
  }

}
