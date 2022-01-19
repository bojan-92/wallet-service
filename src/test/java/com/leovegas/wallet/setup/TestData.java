package com.leovegas.wallet.setup;

import java.math.BigDecimal;

public abstract class TestData {

  private TestData() {}

  public static final Long PLAYER_ID_1 = 1L;
  public static final String PLAYER_NAME_1 = "PlayerNameOne";
  public static final BigDecimal PLAYER_BALANCE_1 = new BigDecimal(50);

  public static final Long TRANSACTION_ID_1 = 1L;
  public static final BigDecimal TRANSACTION_AMOUNT_GREATER_THEN_BALANCE_1 = new BigDecimal(150.654645);
  public static final BigDecimal TRANSACTION_AMOUNT_LOWER_THEN_BALANCE_1 = new BigDecimal(40.32345454354354);

}
