package io.weekendprojects.ipldashboard.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(Include.NON_DEFAULT)
public class Match {

  @Id
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