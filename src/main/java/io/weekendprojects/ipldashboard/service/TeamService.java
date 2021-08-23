package io.weekendprojects.ipldashboard.service;

import io.weekendprojects.ipldashboard.dto.TeamResponse;

public interface TeamService {

  TeamResponse getTeamByTeamName(String teamName);

}
