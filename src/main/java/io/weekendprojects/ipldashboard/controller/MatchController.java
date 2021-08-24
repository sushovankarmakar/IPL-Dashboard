package io.weekendprojects.ipldashboard.controller;

import io.weekendprojects.ipldashboard.dto.MatchResponse;
import io.weekendprojects.ipldashboard.service.MatchService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class MatchController {

  private final MatchService matchService;

  @GetMapping("/{team-name}")
  public List<MatchResponse> getTeam(@PathVariable("team-name") String teamName, @RequestParam("year") Integer year) {
    log.info("Fetching match details for team : {} in year {}", teamName, year);
    return matchService.getAllMatchesOfATeamByYear(teamName, year);
  }

}
