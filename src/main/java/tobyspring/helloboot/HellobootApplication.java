package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class HellobootApplication {

    public static void main(String[] args) {
        /**
         * Spring Container 생성
         */
        GenericWebApplicationContext applicationContext = new GenericWebApplicationContext(); // 스프링 컨테이너 생성
        applicationContext.registerBean(HelloController.class); // 빈 오브젝트 클래스 정보 등록
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh(); // 구성 정보로 컨테이너 초기화(빈 오브젝트를 직접 생성)

        /**
         * Servlet Container 를 실행하면서 Servlet 등록
         */
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            // DispatcherServlet 은 스프링 컨테이너에 등록된 빈 클래스에 있는 매핑 애노테이션 정보를 참고해서 웹 요청을 전달할 오브젝트와 메소드를 선정
            servletContext.addServlet("dispatcherServlet",
                    new DispatcherServlet(applicationContext)
            ).addMapping("/*");
        });
        webServer.start();
    }
}
