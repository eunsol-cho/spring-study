package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 해당 패키지 부터 하위만 등록. 지정하지 않으면, @ComponentScan이 붙은 클래스가 기본 위치. -> 루트 위치에 두는것 추천
        //basePackages = "hello.core.member",
        // @Configuration안에 @Component가 포함되어 컴포넌트 스캔시 중복으로 빈등록이 일어남 -> 제외처리
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 자동빈 vs 수동빈
    // 오류처리 되지 않으며, 수동빈으로 오버라이딩 된다.
    // spring.main.allow-bean-definition-overriding=false 옵션으로 오버라이딩이 아닌, 런타임 에러 처리 가능
    /*@Bean(name="memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }*/

}
