package com.leovegas.wallet.repository;

import com.leovegas.wallet.model.Player;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

  @Query(value = "SELECT p.balance FROM Player p WHERE p.id=?1")
  BigDecimal getCurrentBalanceForPlayer(Long id);

}
