package com.leovegas.wallet.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TransactionType {

  DEBIT("debit"),
  CREDIT("credit");

  private final String value;

  TransactionType(String value) {
    this.value = value;
  }

}
