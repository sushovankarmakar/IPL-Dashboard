package io.weekendprojects.ipldashboard.listener;

import io.weekendprojects.ipldashboard.helper.RepositoryHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private final JdbcTemplate jdbcTemplate;

  private final RepositoryHelper repositoryHelper;

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");

      /*jdbcTemplate.query("SELECT home_team, away_team, date FROM match",
          (resultSet, row) ->
              Match.builder()
                  .homeTeam(resultSet.getString(1))
                  .awayTeam(resultSet.getString(2))
                  .date(LocalDate.parse(resultSet.getString(3)))
                  .build()
      ).forEach(match -> log.info("Found <" + match + "> in the database."));*/

      repositoryHelper.generateTeamData();
    }
  }


}
