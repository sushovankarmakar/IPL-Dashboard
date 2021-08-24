package io.weekendprojects.ipldashboard.service;

import io.weekendprojects.ipldashboard.dto.MatchResponse;
import io.weekendprojects.ipldashboard.dto.TeamResponse;
import io.weekendprojects.ipldashboard.exception.TeamException;
import io.weekendprojects.ipldashboard.model.Match;
import io.weekendprojects.ipldashboard.model.Team;
import io.weekendprojects.ipldashboard.repository.MatchRepository;
import io.weekendprojects.ipldashboard.repository.TeamRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

  private final TeamRepository teamRepository;

  private final MatchRepository matchRepository;

  public TeamResponse getTeamByTeamName(String teamName) {
    long startTime = System.currentTimeMillis();

    Team team = teamRepository.findByName(teamName);

    if (null == team) {
      log.info("No details found for team : {}, time taken : {} ms", teamName, System.currentTimeMillis() - startTime);
      throw new TeamException("No details found for team " + teamName);
    }

    Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "date");

    List<MatchResponse> latestMatches = new ArrayList<>();
    List<Match> matches = matchRepository.findByHomeTeamOrAwayTeam(teamName, teamName, pageable);

    for (Match match : matches) {
      MatchResponse matchResponse = new MatchResponse();
      BeanUtils.copyProperties(match, matchResponse);
      latestMatches.add(matchResponse);
    }

    long totalHomeWins = team.getTotalHomeWins();
    long totalAwayWins = team.getTotalAwayWins();
    long totalWins = totalHomeWins + totalAwayWins;

    long totalHomeMatches = team.getTotalHomeMatches();
    long totalAwayMatches = team.getTotalAwayMatches();
    long totalMatches = totalHomeMatches + totalAwayMatches;

    Float homeWinPercentage = getPercentageScore(totalHomeWins, totalHomeMatches);
    Float awayWinPercentage = getPercentageScore(totalAwayWins, totalAwayMatches);
    Float winPercentage = getPercentageScore(totalWins, totalMatches);

    log.info("Fetching details for team : {}, time taken : {} ms", teamName, System.currentTimeMillis() - startTime);

    return TeamResponse.builder()
        .name(team.getName())
        .totalMatches(totalMatches)
        .winPercentage(winPercentage)
        .totalHomeMatches(totalHomeMatches)
        .homeWinPercentage(homeWinPercentage)
        .totalAwayMatches(totalAwayMatches)
        .awayWinPercentage(awayWinPercentage)
        .latestMatches(latestMatches)
        .build();
  }

  private Float getPercentageScore(long value1, long value2) {
    return ((float) value1 / value2) * 100;
  }

}
