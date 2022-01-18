package com.leovegas.wallet.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PlayerTransactionHistoryResponse {

  private String playerName;
  private List<TransactionDto> transactions;

}
