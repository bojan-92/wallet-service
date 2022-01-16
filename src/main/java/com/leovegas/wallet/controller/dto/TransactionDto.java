package com.leovegas.wallet.controller.dto;

import com.leovegas.wallet.model.constant.TransactionType;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionDto {

  private TransactionType type;
  private BigDecimal amount;

}
