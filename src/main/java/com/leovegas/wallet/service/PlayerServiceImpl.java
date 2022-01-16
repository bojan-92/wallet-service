package com.leovegas.wallet.service;

import com.leovegas.wallet.error.WalletException;
import com.leovegas.wallet.model.Player;
import com.leovegas.wallet.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  @Autowired
  private PlayerRepository playerRepository;

  @Override
  public Player findPlayerById(Long id) {
    return playerRepository.findById(id)
        .orElseThrow(() -> new WalletException("Player not found", HttpStatus.NOT_FOUND));
  }
}
