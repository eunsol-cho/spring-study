package hello.core.sigleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        
        // ThreadA : A사용자가 10000원을 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B사용자가 10000원을 주문
        int userBPrice = statefulService2.order("userB", 20000);

        Assertions.assertThat(userAPrice).isEqualTo(20000);
    }
    
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
