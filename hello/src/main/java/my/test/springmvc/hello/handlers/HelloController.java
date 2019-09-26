package my.test.springmvc.hello.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("Hello World");
        return "success";
        //通过之前视图解析器的配置，该请求会被转发到 /WEB-INF/views/success.jsp 这个页面
    }
}
