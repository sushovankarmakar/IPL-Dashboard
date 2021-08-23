package io.weekendprojects.ipldashboard.repository;

import io.weekendprojects.ipldashboard.model.Match;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

  @Query(value = "SELECT m.homeTeam, COUNT(m) FROM Match m "
      + "GROUP BY m.homeTeam")
  List<Object[]> findHomeTeamsAndTotalHomeMatchesPlayed();

  @Query(value = "SELECT m.awayTeam, COUNT(m) FROM Match m "
      + "GROUP BY m.awayTeam")
  List<Object[]> findAwayTeamsAndTotalAwayMatchesPlayed();

  @Query(value = "SELECT m.matchWinner, COUNT(m) FROM Match m "
      + "WHERE m.homeTeam = m.matchWinner "
      + "GROUP BY m.matchWinner")
  List<Object[]> findHomeTeamsAndTotalHomeWins();

  @Query(value = "SELECT m.matchWinner, COUNT(m) FROM Match m "
      + "WHERE m.awayTeam = m.matchWinner "
      + "GROUP BY m.matchWinner")
  List<Object[]> findAwayTeamsAndTotalAwayWins();

  //List<Match> findFirst5ByHomeTeamOrAwayTeamOrderByDateDesc(String homeTeam, String awayTeam);

  List<Match> findByHomeTeamOrAwayTeam(String homeTeam, String awayTeam, Pageable pageable);

}
