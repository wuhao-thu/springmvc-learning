package my.test.springmvc.handlers;

import my.test.springmvc.entities.Address;
import my.test.springmvc.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@SessionAttributes({"user"})
@Controller
public class TestController {

    private final String SUCCESS = "success";

    @RequestMapping("/testRequestMapping")
    public String testRequestMapping() {
        System.out.println("testRequestMapping");
        return SUCCESS;
    }

    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod() {
        System.out.println("testMethod");
        return SUCCESS;
    }

    @RequestMapping(value = "/testParams", params = {"username", "age!=18"})
    public String testParams() {
        System.out.println("testParams");
        return SUCCESS;
    }

    @RequestMapping("/testAntPath/**")
    public String testAntPath() {
        System.out.println("testAntPath");
        return SUCCESS;
    }

    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("testPathVariable: " + id);
        return SUCCESS;
    }

    @PutMapping("/testRestPut")
    @ResponseBody
    public String testRestPut() {
        System.out.println("testRestPut");
        return SUCCESS;
    }

    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false) Integer age) {
        System.out.println("testRequestParam");
        System.out.println("name: " + name + ", age: " + age);
        return SUCCESS;
    }

    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader("User-Agent") String userAgent) {
        System.out.println("testRequestHeader");
        System.out.println("request from: " + userAgent);
        return SUCCESS;
    }

    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value="JSESSIONID") String sessionId) {
        System.out.println("testCookieValue, sessionId: " + sessionId);
        return SUCCESS;
    }

    @RequestMapping("/testPOJO")
    public String testPOJO(User user) {
        System.out.println("User: " + user);
        return SUCCESS;
    }

    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("testServletAPI, " + request + ", " + response);
        return SUCCESS;
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView modelAndView = new ModelAndView(SUCCESS);
        //添加模型数据到ModelAndView中
        modelAndView.addObject("time", new Date());
        return modelAndView;
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        map.put("names", Arrays.asList("Tom", "Jerry"));
        return SUCCESS;
    }

    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map) {
        Address address = new Address("Beijing", "Haidian");
        User user = new User("Tom", "12345", "tom@example.com", 18, address);
        map.put("user", user);
        return SUCCESS;
    }

    @ModelAttribute
    public void getUser(Map<String, Object> map) {
        Address address = new Address("Beijing", "Haidian");
        User user = new User();
        user.setAddress(address);
        System.out.println("从数据库中获取一个对象：" + user);
        map.put("user", user);
    }
}
