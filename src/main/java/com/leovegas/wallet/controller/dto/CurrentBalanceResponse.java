package com.leovegas.wallet.controller.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CurrentBalanceResponse {

  private BigDecimal currentBalance;
}
