package com.leovegas.wallet.service;

import com.leovegas.wallet.controller.dto.CurrentBalanceResponse;
import com.leovegas.wallet.model.Player;

public interface PlayerService {

  Player findById(Long id);

  CurrentBalanceResponse getCurrentBalanceForPlayer(Long id);

}
