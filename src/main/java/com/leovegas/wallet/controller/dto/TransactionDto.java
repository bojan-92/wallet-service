package com.leovegas.wallet.controller.dto;

import com.leovegas.wallet.model.constant.TransactionType;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TransactionDto {

  private TransactionType type;
  private BigDecimal amount;

}
