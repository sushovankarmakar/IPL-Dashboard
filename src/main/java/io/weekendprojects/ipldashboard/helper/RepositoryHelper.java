package io.weekendprojects.ipldashboard.helper;

import io.weekendprojects.ipldashboard.model.Team;
import io.weekendprojects.ipldashboard.repository.MatchRepository;
import io.weekendprojects.ipldashboard.repository.TeamRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RepositoryHelper {

  private final MatchRepository matchRepository;

  private final TeamRepository teamRepository;

  public void generateTeamData() {
    long startTime = System.currentTimeMillis();

    Map<String, Team> teams = new HashMap<>();

    matchRepository.findHomeTeamsAndTotalHomeMatchesPlayed()
        .stream()
        .map(objects -> Team.builder()
            .name(String.valueOf(objects[0]))
            .totalHomeMatches((long) objects[1])
            .build())
        .forEach(team -> teams.put(team.getName(), team));

    matchRepository.findAwayTeamsAndTotalAwayMatchesPlayed()
        .forEach(objects -> {
          Team team = teams.get(String.valueOf(objects[0]));
          Optional.ofNullable(team)
              .ifPresent(team1 -> team1.setTotalAwayMatches((long) objects[1]));
        });

    matchRepository.findHomeTeamsAndTotalHomeWins()
        .forEach(objects -> {
          Team team = teams.get(String.valueOf(objects[0]));
          Optional.ofNullable(team)
              .ifPresent(team1 -> team1.setTotalHomeWins((long) objects[1]));
        });

    matchRepository.findAwayTeamsAndTotalAwayWins()
        .forEach(objects -> {
          Team team = teams.get(String.valueOf(objects[0]));
          Optional.ofNullable(team)
              .ifPresent(team1 -> team1.setTotalAwayWins((long) objects[1]));
        });

    teamRepository.saveAll(teams.values());

    log.info("Writing teams into database, time taken : {} ms", (System.currentTimeMillis() - startTime));
  }

}
