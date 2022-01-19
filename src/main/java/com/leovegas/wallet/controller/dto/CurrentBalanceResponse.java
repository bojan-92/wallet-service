package com.leovegas.wallet.controller.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CurrentBalanceResponse {

  private BigDecimal currentBalance;
}
