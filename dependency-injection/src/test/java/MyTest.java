import com.iris.po.Student;
import com.iris.po.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
    }
    @Test
    public void myTest(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("userBeans.xml");
        User user =context.getBean("user", User.class);
        User userc =context.getBean("userc", User.class);
        System.out.println(user.getName());
        System.out.println(user.getAge());
        System.out.println(userc.getName());
        System.out.println(userc.getAge());
        // 测试单例模式与原型模式区别
        User user1 =context.getBean("user", User.class);
        User userc1 =context.getBean("userc", User.class);
        // 对比调用同一个bean，看bean是否相同
        System.out.println(user1 == user);
        System.out.println(userc1 == userc);
        // 查看hashCode到底是否为一个对象
        System.out.println(user.hashCode());
        System.out.println(user1.hashCode());
        System.out.println(userc.hashCode());
        System.out.println(userc1.hashCode());
    }
}
