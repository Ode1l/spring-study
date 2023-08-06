import com.iris.mapper.UserMapper;
import com.iris.mapper.UserMapperImpl;
import com.iris.po.User;
import com.iris.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class MyTest {
    @Test
    public void test1() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        userService.invoke();
        UserMapper userMapper = context.getBean(UserMapper.class);
        userMapper.query().forEach(System.out::println);
    }
}
