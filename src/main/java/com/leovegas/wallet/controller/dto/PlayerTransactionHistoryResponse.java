package com.leovegas.wallet.controller.dto;

import java.util.List;
import lombok.Data;

@Data
public class PlayerTransactionHistoryResponse {

  private String playerName;
  private List<TransactionDto> transactions;

}
