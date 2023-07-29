package tobyspring.helloboot;

import java.util.Objects;

public class HelloController {
    // 주입 받은 오브젝트는 내부 멤버 필드로 저장해두고 이용
    private final HelloService helloService;

    /**
     * 의존 오브젝트를 생성자를 통해서 DI 어셈블러인 컨테이너가 주입을 할 수 있게 생성자 파라미터를 정의
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}