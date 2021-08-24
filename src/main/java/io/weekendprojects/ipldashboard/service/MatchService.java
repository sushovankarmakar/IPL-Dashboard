package io.weekendprojects.ipldashboard.service;

import io.weekendprojects.ipldashboard.dto.MatchResponse;
import java.util.List;

public interface MatchService {

  List<MatchResponse> getAllMatchesOfATeamByYear(String team, Integer year);

}
