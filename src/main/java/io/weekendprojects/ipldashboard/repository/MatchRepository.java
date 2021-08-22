package io.weekendprojects.ipldashboard.repository;

import io.weekendprojects.ipldashboard.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
