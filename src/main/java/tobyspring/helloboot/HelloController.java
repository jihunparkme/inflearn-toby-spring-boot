package tobyspring.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 클래스 레벨의 @RequestMapping 과 메소드 레벨의 @GetMapping 두 가지의 정보를 조합해서 매핑에 사용할 최종 정보 생성
 */
@RestController
public class HelloController {
    // 주입 받은 오브젝트는 내부 멤버 필드로 저장해두고 이용
    private final HelloService helloService;

    /**
     * 의존 오브젝트를 생성자를 통해서 DI 어셈블러인 컨테이너가 주입을 할 수 있게 생성자 파라미터를 정의
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * 컨트롤러 메소드의 리턴값을 웹 요청의 바디에 적용하도록 @ResponseBody 선언
     */
    @GetMapping("/hello")
    public String hello(String name) {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        return helloService.sayHello(name);
    }
}