package io.weekendprojects.ipldashboard.service;

import io.weekendprojects.ipldashboard.dto.MatchResponse;
import io.weekendprojects.ipldashboard.model.Match;
import io.weekendprojects.ipldashboard.repository.MatchRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

  private final MatchRepository matchRepository;

  @Override
  public List<MatchResponse> getAllMatchesOfATeamByYear(String team, Integer year) {
    long startTime = System.currentTimeMillis();

    List<MatchResponse> matches = new ArrayList<>();

    LocalDate startDate = LocalDate.of(year, 1, 1);
    LocalDate endDate = LocalDate.of(year + 1, 1, 1);

    //Pageable pageable = PageRequest.of(0, 30, Sort.Direction.DESC, "date");

    List<Match> matchesByYear = matchRepository.findMatchesByTeamBetweenDates(team, startDate, endDate);

    for (Match match : matchesByYear) {
      MatchResponse matchResponse = new MatchResponse();
      BeanUtils.copyProperties(match, matchResponse);
      matches.add(matchResponse);
    }

    log.info("Fetching match details for team : {} in year {}, time taken : {} ms", team, year,
        System.currentTimeMillis() - startTime);
    return matches;
  }
}
