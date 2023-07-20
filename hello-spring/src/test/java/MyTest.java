import com.iris.po.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        // 获取Spring上下文
        System.out.println("获取Spring上下文之前");
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("进入Spring上下文");
        // 我们的对象在Spring中管理，直接获取就可以了
        Hello hello = (Hello) context.getBean("hello");
        Hello hello1 = (Hello) context.getBean("StringS");
        System.out.println("进入bean");
        System.out.println(hello);
        System.out.println(hello1);
    }
}
