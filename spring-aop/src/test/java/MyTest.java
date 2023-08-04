import com.iris.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context2.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("context3.xml");
        UserService userService = (UserService) context.getBean("userService");
        userService.add();
    }
}
