package io.weekendprojects.ipldashboard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class MatchResponse {

  private String homeTeam;
  private String awayTeam;

  private LocalDate date;
  private String venue;
  private String city;

  private String tossWinner;
  private String tossDecision;

  private String matchWinner;
  private String result;
  private String resultMargin;
  private String playerOfMatch;

  private String eliminator;
  private String method;
  private String firstUmpire;
  private String secondUmpire;

}
