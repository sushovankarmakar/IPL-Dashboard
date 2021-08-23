package io.weekendprojects.ipldashboard.config;

import io.weekendprojects.ipldashboard.batch.MatchDataDBWriter;
import io.weekendprojects.ipldashboard.batch.MatchDataProcessor;
import io.weekendprojects.ipldashboard.data.MatchData;
import io.weekendprojects.ipldashboard.listener.JobCompletionNotificationListener;
import io.weekendprojects.ipldashboard.model.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

  @Value("${file.input}")
  private String fileInput;

  @Value("${file.input.fieldNames}")
  private String[] fieldNames;

  public final JobBuilderFactory jobBuilderFactory;

  public final StepBuilderFactory stepBuilderFactory;

  private final MatchDataDBWriter matchDataDBWriter;

  private final MatchDataProcessor matchDataProcessor;

  @Bean
  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
    return jobBuilderFactory.get("importUserJob")
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(step1)
        .end()
        .build();
  }

  @Bean
  public Step step1() {
    return stepBuilderFactory.get("step1")
        .<MatchData, Match>chunk(10)
        .reader(reader())
        .processor(matchDataProcessor)
        .writer(matchDataDBWriter)
        .build();
  }

  @Bean
  public FlatFileItemReader<MatchData> reader() {
    return new FlatFileItemReaderBuilder<MatchData>()
        .name("matchInputReader")
        .resource(new ClassPathResource(fileInput))
        .linesToSkip(1)
        .delimited()
        .names(fieldNames)
        .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
          setTargetType(MatchData.class);
        }})
        .build();
  }

  /*@Bean
  public MatchDataProcessor processor() {
    return new MatchDataProcessor();
  }*/

  /*@Bean
  public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {

    String sqlQuery = "INSERT INTO match "
        + "(id, city, date, player_of_match, venue, home_team, away_team, toss_winner, toss_decision, "
        + "match_winner, result, result_margin, eliminator, method, first_umpire, second_umpire) "
        + "VALUES (:id, :city, :date, :playerOfMatch, :venue, :homeTeam, :awayTeam, :tossWinner, :tossDecision, "
        + ":matchWinner, :result, :resultMargin, :eliminator, :method, :firstUmpire, :secondUmpire)";

    return new JdbcBatchItemWriterBuilder<Match>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql(sqlQuery)
        .dataSource(dataSource)
        .build();
  }*/

}
