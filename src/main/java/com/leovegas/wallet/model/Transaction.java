package com.leovegas.wallet.model;

import com.leovegas.wallet.model.constant.TransactionType;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  @Column(name = "amount")
  private BigDecimal amout;

  @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JoinColumn(name = "player", nullable = false)
  private Player player;

}
