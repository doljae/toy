package com.example.jobs;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.domain.Employee;
import com.example.service.ExceptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "spring.batch.job.names", havingValue = "jpaCursorBatchJob")
public class JpaCursorBatchJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final ExceptionService exceptionService;
    private static final int CHUNK_SIZE = 10;

    public JpaCursorBatchJob(
        JobBuilderFactory jobBuilderFactory,
        StepBuilderFactory stepBuilderFactory,
        EntityManagerFactory entityManagerFactory,
        ExceptionService exceptionService) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.exceptionService = exceptionService;
    }

    @Bean
    public Job testBatchJobEntityInstant(Step testBatchStep) {

        return jobBuilderFactory.get("jpaCursorBatchJob")
                                .start(testBatchStep)
                                .build();
    }

    @Bean
    @JobScope
    public Step testBatchStep() {
        return stepBuilderFactory.get("jpaCursorBatchStep")
                                 .<Employee, Employee>chunk(CHUNK_SIZE)
                                 .reader(testEntityReader())
                                 .processor(testProcessor())
                                 .writer(testWriter())
                                 .faultTolerant()
                                 .skipPolicy(new AlwaysSkipItemSkipPolicy())
                                 .build();
    }

    /**
     maxItemCount(c) -> query로 조회한 row 중 c개만 읽는다 \/n
     currentItemCount(c) -> 읽어온 row중 c번째 row부터 읽겠다 (시작은 1)
     */
    @Bean
    @StepScope // ? proxy pattern...? 이게 없으면 동작함, 있으면 동작 안함...?
    public ItemReader<Employee> testEntityReader() {
        return new JpaCursorItemReaderBuilder<Employee>()
            .name("employReader")
            .entityManagerFactory(entityManagerFactory)
            .queryString("SELECT e FROM Employee e")
//            .maxItemCount(10)
//            .currentItemCount(3)
            .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Employee, Employee> testProcessor() {
        return employee -> {
            log.info("처리 중, {}", employee.getId());
            employee.setName(employee.getName() + "_new");
            log.info("처리 완료, {}", employee.getId());
            return employee;
        };
    }

    @Bean
    @StepScope
    public ItemWriter testWriter() {
        final JpaItemWriter<Employee> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }
}