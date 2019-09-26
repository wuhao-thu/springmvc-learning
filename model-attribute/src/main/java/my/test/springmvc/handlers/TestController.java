package my.test.springmvc.handlers;

import my.test.springmvc.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

//@SessionAttributes({"user"})
@Controller
public class TestController {

    private final String SUCCESS = "success";
    private static Integer ID = 0;

    @ModelAttribute
    public void getUser(Map<String, Object> map, HttpSession session) {
        User user = new User();
        user.setId(ID++);
        System.out.println("新建一个对象：" + user);
        map.put("user", user);
        if(session.getAttribute("user") == null) {
            session.setAttribute("user", user);
            System.out.println("将对象 " + user + " 添加到 session");
        }
    }

    @PostMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("user") User user) {
        System.out.println("提交表单：" + user);
        return SUCCESS;
    }
}
