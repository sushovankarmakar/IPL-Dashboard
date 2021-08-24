package io.weekendprojects.ipldashboard.controller;

import io.weekendprojects.ipldashboard.dto.TeamResponse;
import io.weekendprojects.ipldashboard.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class TeamController {

  private final TeamService teamService;

  @GetMapping("/{team-name}")
  public TeamResponse getTeam(@PathVariable("team-name") String teamName) {
    log.info("Fetching details for team : {}", teamName);
    return teamService.getTeamByTeamName(teamName);
  }
}
