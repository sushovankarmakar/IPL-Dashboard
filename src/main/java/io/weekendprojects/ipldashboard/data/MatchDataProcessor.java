package io.weekendprojects.ipldashboard.data;

import io.weekendprojects.ipldashboard.model.Match;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  @Override
  public Match process(final MatchInput matchInput) {
    long startTime = System.currentTimeMillis();

    Match match = Match.builder()
        .id(Long.parseLong(matchInput.getId()))
        .city(matchInput.getCity())
        .date(LocalDate.parse(matchInput.getDate()))
        .playerOfMatch(matchInput.getPlayer_of_match())
        .venue(matchInput.getVenue())
        .homeTeam(matchInput.getTeam1())
        .awayTeam(matchInput.getTeam2())
        .tossWinner(matchInput.getToss_winner())
        .tossDecision(matchInput.getToss_decision())
        .matchWinner(matchInput.getWinner())
        .result(matchInput.getResult())
        .resultMargin(matchInput.getResult_margin())
        .eliminator(matchInput.getEliminator())
        .method(matchInput.getMethod())
        .firstUmpire(matchInput.getUmpire1())
        .secondUmpire(matchInput.getUmpire2())
        .build();

    log.info("Converting {} into {} , time taken : {}", matchInput, match, (System.currentTimeMillis() - startTime));

    return match;
  }

}
