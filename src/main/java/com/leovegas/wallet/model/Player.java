package com.leovegas.wallet.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Player {

  @Id
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "balance")
  private BigDecimal balance;

}
