//
//package com.example.jobs;
//
//import javax.persistence.EntityManagerFactory;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.example.domain.Employee;
//import com.example.service.ExceptionService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Configuration
//@ConditionalOnProperty(name = "spring.batch.job.names", havingValue = "testBatchEntityJob")
//public class TestBatchEntityJob {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final EntityManagerFactory entityManagerFactory;
//    private final ExceptionService exceptionService;
//    private static final int CHUNK_SIZE = 5;
//
//    public TestBatchEntityJob(
//        JobBuilderFactory jobBuilderFactory,
//        StepBuilderFactory stepBuilderFactory,
//        EntityManagerFactory entityManagerFactory,
//        ExceptionService exceptionService) {
//        this.jobBuilderFactory = jobBuilderFactory;
//        this.stepBuilderFactory = stepBuilderFactory;
//        this.entityManagerFactory = entityManagerFactory;
//        this.exceptionService = exceptionService;
//    }
//
//    @Bean
//    public Job testBatchJobEntityInstant(Step testBatchStep) {
//
//        return jobBuilderFactory.get("testBatchEntityJob")
//                                .start(testBatchStep)
//                                .build();
//    }
//
//    @Bean
//    @JobScope
//    public Step testBatchStep() {
//        return stepBuilderFactory.get("testBatchEntityStep")
//                                 .<Employee, Employee>chunk(CHUNK_SIZE)
//                                 .reader(testEntityReader())
//                                 .processor(testProcessor())
//                                 .writer(testWriter())
//                                 .faultTolerant()
//                                 .skipPolicy(new AlwaysSkipItemSkipPolicy())
//                                 .build();
//    }
//
//    @Bean
//    @StepScope
//    public JpaPagingItemReader<Employee> testEntityReader() {
//        return new JpaPagingItemReaderBuilder<Employee>()
//            .name("employReader")
//            .entityManagerFactory(entityManagerFactory)
//            .pageSize(CHUNK_SIZE)
//            .queryString("SELECT e FROM Employee e")
//            .build();
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<Employee, Employee> testProcessor() {
//        return employee -> {
//            exceptionService.handleItem(employee);
//            employee.setName(employee.getName() + "_new");
//            exceptionService.sendAoa(employee);
//            return employee;
//        };
//    }
//
//    @Bean
//    @StepScope
//    public ItemWriter testWriter() {
//        // log.info("item writer success = {}", employee);
//        final JpaItemWriter<Employee> jpaItemWriter = new JpaItemWriter<>();
//        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
//        return jpaItemWriter;
//    }
//
//}
