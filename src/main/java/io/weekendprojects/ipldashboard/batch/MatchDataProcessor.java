package io.weekendprojects.ipldashboard.batch;

import io.weekendprojects.ipldashboard.data.MatchData;
import io.weekendprojects.ipldashboard.model.Match;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MatchDataProcessor implements ItemProcessor<MatchData, Match> {

  @Override
  public Match process(final MatchData matchData) {
    long startTime = System.currentTimeMillis();

    Match match = Match.builder()
        .id(Long.parseLong(matchData.getId()))
        .city(matchData.getCity())
        .date(LocalDate.parse(matchData.getDate()))
        .playerOfMatch(matchData.getPlayer_of_match())
        .venue(matchData.getVenue())
        .homeTeam(matchData.getTeam1())
        .awayTeam(matchData.getTeam2())
        .tossWinner(matchData.getToss_winner())
        .tossDecision(matchData.getToss_decision())
        .matchWinner(matchData.getWinner())
        .result(matchData.getResult())
        .resultMargin(matchData.getResult_margin())
        .eliminator(matchData.getEliminator())
        .method(matchData.getMethod())
        .firstUmpire(matchData.getUmpire1())
        .secondUmpire(matchData.getUmpire2())
        .build();

    log.info("Converting {} into {} , time taken : {} ms", matchData, match, (System.currentTimeMillis() - startTime));

    return match;
  }

}
