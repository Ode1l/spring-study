import com.iris.po.Human;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void Test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Human human = context.getBean("human", Human.class);
        human.getCat().shout();
        human.getDog().shout();
    }
}
