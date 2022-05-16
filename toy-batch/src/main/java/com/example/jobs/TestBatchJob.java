
package com.example.jobs;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "spring.batch.job.names", havingValue = "testBatchJob")
public class TestBatchJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private static final List<String> stringList = List.of("a", "b", "c", "d", "e");
    private static final int CHUNK_SIZE = 5;

    public TestBatchJob(
        JobBuilderFactory jobBuilderFactory,
        StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job testBatchJobInstant(Step testBatchStep) {

        return jobBuilderFactory.get("testBatchJob")
                                .start(testBatchStep)
                                .build();
    }

    @Bean
    @JobScope
    public Step testBatchStep() {
        return stepBuilderFactory.get("testBatchStep")
                                 .<String, String>chunk(CHUNK_SIZE)
                                 .reader(testReader())
                                 .processor(testProcessor())
                                 .writer(testWriter())
                                 .faultTolerant()
                                 .skipPolicy(new AlwaysSkipItemSkipPolicy())
                                 .build();
    }

    @Bean
    @StepScope
    public ListItemReader<String> testReader() {
        return new ListItemReader<>(stringList);
    }

    @Bean
    @StepScope
    public ItemProcessor<String, String> testProcessor() {
        return item -> {
            handleItemWitoutCatchException(item);
            sendAoa(item);
            return item;
        };
    }

    @Bean
    @StepScope
    public ItemWriter testWriter() {
        return item -> log.info("item writer success = {}", item);
    }

    private void sendAoa(String item) {
        log.info("send aoa={}", item);
    }

    private void handleItem(String item) {
        try {
            log.info("handle item={}", item);
            if (item.equals("d")) {
                makeException();
            }
        } catch (Exception e) {
            log.info("exception occured!!");
        }
    }

    private void handleItemWitoutCatchException(String item) {
        log.info("handle item={}", item);
        if (item.equals("d")) {
            makeException();
        }

    }

    private void makeException() {
        throw new RuntimeException();
    }
}
