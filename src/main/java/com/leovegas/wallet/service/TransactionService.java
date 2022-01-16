package com.leovegas.wallet.service;

import com.leovegas.wallet.controller.dto.CreateTransactionRequest;
import com.leovegas.wallet.model.constant.TransactionType;
import java.math.BigDecimal;

public interface TransactionService {

  void create(CreateTransactionRequest request);

}
