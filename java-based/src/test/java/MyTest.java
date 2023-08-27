import com.iris.config.SpringConfig;
import com.iris.po.User;
import com.iris.utils.SpringContextHolder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void myTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        User user = context.getBean(User.class);
        System.out.println(user.Name);
    }

    @Test
    public void myTest2() {
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);
        ApplicationContext context = SpringContextHolder.getApplicationContext();
        User user = (User)context.getBean( "user") ;
        System.out.println(user.Name);
    }
}
