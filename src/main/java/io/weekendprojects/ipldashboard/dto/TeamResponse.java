package io.weekendprojects.ipldashboard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.weekendprojects.ipldashboard.model.Match;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class TeamResponse {

  private String name;

  private Long totalMatches;
  private Float winPercentage;

  private Long totalHomeMatches;
  private Float homeWinPercentage;

  private Long totalAwayMatches;
  private Float awayWinPercentage;

  private List<Match> latestMatches;

}
