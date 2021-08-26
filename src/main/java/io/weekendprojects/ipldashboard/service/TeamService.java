package io.weekendprojects.ipldashboard.service;

import io.weekendprojects.ipldashboard.dto.TeamResponse;
import java.util.List;

public interface TeamService {

  List<TeamResponse> getAllTeams();

  TeamResponse getTeamByTeamName(String teamName);

}
