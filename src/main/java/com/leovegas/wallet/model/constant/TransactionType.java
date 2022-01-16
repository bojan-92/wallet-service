package com.leovegas.wallet.model.constant;

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

  public static TransactionType findByValue(String value) {
    for (TransactionType v : values()) {
      if (v.getValue().equals(value)) {
        return v;
      }
    }
    return null;
  }

}
