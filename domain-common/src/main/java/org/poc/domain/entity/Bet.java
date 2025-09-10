package org.poc.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "bets")
public class Bet {

    @Id
    @Column(name = "bet_id")
    private BigInteger betId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "event_id", nullable = false)
    private String eventId;

    @Column(name = "event_market_id", nullable = false)
    private String eventMarketId;

    @Column(name = "event_winner_id")
    private String eventWinnerId;

    @Column(name = "bet_amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal betAmount;

}
