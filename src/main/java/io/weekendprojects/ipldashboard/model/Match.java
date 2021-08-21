package io.weekendprojects.ipldashboard.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Match {

  private Long id;
  private String city;
  private LocalDate date;
  private String playerOfMatch;
  private String venue;
  private String homeTeam;
  private String awayTeam;
  private String tossWinner;
  private String tossDecision;
  private String matchWinner;
  private String result;
  private String resultMargin;
  private String eliminator;
  private String method;
  private String firstUmpire;
  private String secondUmpire;

}
