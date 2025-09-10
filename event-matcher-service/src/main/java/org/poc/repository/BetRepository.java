package org.poc.repository;

import org.poc.domain.entity.Bet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findByEventId(String eventId);
}
