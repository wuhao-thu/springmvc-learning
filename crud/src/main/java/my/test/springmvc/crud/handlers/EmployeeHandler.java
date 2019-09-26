package my.test.springmvc.crud.handlers;

import my.test.springmvc.crud.dao.DepartmentDao;
import my.test.springmvc.crud.dao.EmployeeDao;
import my.test.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@Controller
public class EmployeeHandler {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @ModelAttribute
    public void getEmployee(@RequestParam(value="id", required = false) Integer id, Map<String, Object> map) {
        if(id != null) {
            map.put("employee", employeeDao.get(id));
        }
    }

    @RequestMapping(value = "/testFileUpload", method = RequestMethod.POST)
    public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("desc: " + desc);
        System.out.println("OriginalFilename: " + file.getOriginalFilename());
        System.out.println("InputStream: " + file.getInputStream());
        return "success";
    }

    @RequestMapping("/i18n")
    public String testI18n(Locale locale) {
        String val = messageSource.getMessage("i18n.user", null, locale);
        System.out.println(val);
        return "i18n";
    }

    @RequestMapping("/testRequestEntity")
    @ResponseBody
    // 打印请求的头信息
    public String testResponseEntity(RequestEntity<String> entity) throws IOException {
        return entity.getHeaders().toString();
    }

    @RequestMapping("/testResponseEntity")
    // 文件下载
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        // 获取输入流
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/files/test.pdf");
        // 文件的内容
        byte[] body = new byte[in.available()];
        in.read(body);
        // headers
        HttpHeaders headers = new HttpHeaders();
        // 设置文件名称
        headers.add("Content-Disposition", "attachment;filename=test.pdf");
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }

    @RequestMapping("/testHttpMessageConverter")
    @ResponseBody
    public String testHttpMessageConverter(@RequestBody String body) {
        System.out.println(body);
        return "Hello world! " + new Date();
    }

    @RequestMapping("/testJson")
    @ResponseBody
    public Collection<Employee> testJson() {
        return employeeDao.getAll();
    }

    @RequestMapping("/testConversionServiceConverter")
    @ResponseBody
    public String testConverter(@RequestParam("employee") Employee employee) {
        return employee.toString();
    }

    // 显示所有员工
    @RequestMapping("/emps")
    public String list(Map<String, Object> map) {
        map.put("employees", employeeDao.getAll());
        return "list";
    }

    // 跳转到添加员工的页面
    @RequestMapping(value="/emp", method = RequestMethod.GET)
    public String input(Map<String, Object> map) {
        map.put("departments", departmentDao.getDepartments());
        map.put("employee", new Employee());
        return "input";
    }

    // 保存
    @RequestMapping(value="/emp", method=RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult result, Map<String, Object> map) {
        if(result.getErrorCount() > 0) {
            System.out.println("出错了！");
            for(FieldError error:result.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }
            // 如果出错，重新载入编辑页面
            map.put("departments", departmentDao.getDepartments());
            return "input";
        } else {
            employeeDao.save(employee);
            System.out.println("save " + employee);
            return "redirect:/emps";
        }
    }

    // 跳转到修改员工的页面
    @RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String, Object> map) {
        map.put("employee", employeeDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return "input";
    }

    // 更新员工
    @RequestMapping(value="/emp", method=RequestMethod.PUT)
    public String update(Employee employee) {
        employeeDao.save(employee);
        System.out.println("update " + employee);
        return "redirect:/emps";
    }

    // 删除员工
    @RequestMapping(value="/emp/{id}", method=RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        System.out.println("delete");
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}

