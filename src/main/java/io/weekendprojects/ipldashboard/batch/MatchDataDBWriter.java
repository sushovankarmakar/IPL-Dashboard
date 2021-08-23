package io.weekendprojects.ipldashboard.batch;

import io.weekendprojects.ipldashboard.model.Match;
import io.weekendprojects.ipldashboard.repository.MatchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MatchDataDBWriter implements ItemWriter<Match> {

  private final MatchRepository matchRepository;

  @Override
  public void write(List<? extends Match> matches) throws Exception {
    long startTime = System.currentTimeMillis();

    matchRepository.saveAll(matches);

    log.info("Writing matches into database, time taken : {} ms", (System.currentTimeMillis() - startTime));
  }
}
