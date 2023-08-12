package com.example.springbootac;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAcApplication {
    /**
     * 조건이 매칭된 자동 구성 클래스와 메소드를 출력
     */
    @Bean
    ApplicationRunner run(ConditionEvaluationReport report) {
        return args -> {
            long result = report.getConditionAndOutcomesBySource().entrySet().stream()
                    .filter(co -> co.getValue().isFullMatch()) // 컨디션 조건을 모두 통과한 빈 목록
                    .filter(co -> co.getKey().indexOf("Jmx") < 0) // Jmx 관련 구성 정보 제외
                    .map(co -> {
                        System.out.println(co.getKey());
                        co.getValue().forEach(c -> {
                            System.out.println("\t" + c.getOutcome()); // 컨디셔널 통과 조건
                        });
                        System.out.println();
                        return co;
                    }).count();

            System.out.println(result);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAcApplication.class, args);
    }

}
