package com.leovegas.wallet.model;

import com.leovegas.wallet.constant.TransactionType;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Transaction {

  @Id
  private Long id;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @Column(name = "amount")
  private BigDecimal amout;

  @ManyToOne
  @JoinColumn(name = "player", nullable = false)
  private Player player;

}
