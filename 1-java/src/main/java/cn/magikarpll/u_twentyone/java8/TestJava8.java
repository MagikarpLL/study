package cn.magikarpll.u_twentyone.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class TestJava8 {

}

//lamba表达式
class TestLambda{

    @Test
    public void testLambda(){
        String test = "ddd";
        Arrays.asList("a","b","c").forEach(t->System.out.println("lambda1: " + t));

        Arrays.asList("a","b","c").forEach(t->{
            System.out.println("lambda2: " + t);
            //test = "ddd";
            System.out.println("lambda2: " + test);
        });

        List lists = Arrays.asList("5","3","4","2","4","1");
        lists.forEach(t->{
            System.out.println("lambda3: " + t);
        });
        Collections.sort(lists,(String a,String b)->a.compareTo(b));
        lists.forEach(t->{
            System.out.println("lambda4: " + t);
        });

    }

}

//函数式接口
@FunctionalInterface
interface FunctionInter{
    public void testFunction(int i);
}

@FunctionalInterface
interface D {
    void get(int i,String j);
}

class FunctionInterClass{

    static void add(double a,String b) {
        System.out.println(a + b);
    }

    static void delete(int m) {
        System.out.println(m);
    }

    @Test
    public void test(){
        String test = "adas";

        D d = (a,b) -> add(a,b);
        D d2 = FunctionInterClass::add;
        FunctionInter functionInter = (m) -> delete(m + this.hashCode());
        FunctionInter functionInter1 = FunctionInterClass::delete;

        d2.get(1,"2");
        functionInter1.testFunction(1);
    }

}

class TestLambda2 {

    public static void execute(Runnable runnable) {
        runnable.run();
    }

    public static void hello(){
        System.out.println("hello!");
    }

    public static void main(String[] args) {
        //Java8之前
        execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });

        //使用Lambda表达式
        execute(() -> System.out.println("run"));
        //使用方法引用
        execute(TestLambda2::hello);
    }
}

@FunctionalInterface
interface MethodInter<T, K>{
    void printhello(T t, K k);
}

@FunctionalInterface
interface Supplier<T>{
    void printhello(T t);
}

class TestMethod {

    public static void hello(String s, Integer i){
        System.out.println("hello!");
    }

    public TestMethod(){

    }

    public TestMethod(String i){

    }

    public TestMethod(int i){

    }

    public TestMethod(String i, Integer l){

    }

    public static void main(String[] args) {
        MethodInter<String, Integer> methodInter = TestMethod::hello;
        MethodInter<String, Integer> methodInter2 = Integer::parseInt;
        //下面这个就不行，函数必须要有对应的参数才行
        //MethodInter<String, Integer> methodInter3 = TestLambda2::hello;
        //构造函数引用
        //Supplier<TestMethod> supplier = TestMethod::new;
        Supplier<String> supplier = TestMethod::new;
        Supplier<Integer> supplier2 = TestMethod::new;
    }
}

//接口默认方法
interface Defaulable {
    // Interfaces now allow default methods, the implementer may or
    // may not implement (override) them.
    default String notRequired() {
        return "Default interface implementation";
    }

    static String create( String s ) {
        System.out.println("create1");
        return "gg";
    }

}

//接口默认方法
interface Defaulable2 {
    // Interfaces now allow default methods, the implementer may or
    // may not implement (override) them.
    default String notRequired() {
        return "Default interface implementation";
    }

    static String create( String s ) {
        System.out.println("create2");
        return "gg";
    }

}

class DefaultClass{

    public String notRequired() {
        return "Default class implementation";
    }

}

class DefaultableImpl implements Defaulable,Defaulable2 {

//    public String notRequired() {
//        return "Overridden implementation";
//    }

    //有子类优先子类，没有子类看父类，最后看接口
    @Test
    public void test(){
        System.out.println(notRequired());
        //貌似静态接口必须要返回值才行
        System.out.println(Defaulable.create("s"));
    }

    @Override
    public String notRequired() {
        Defaulable.super.notRequired();
        return "";
    }
}

class OverridableImpl implements Defaulable {

    public String notRequired() {
        Defaulable.super.notRequired();
        return "Overridden implementation";
    }
}

//重复注解
class RepeatingAnnotations {
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
        String value();
    };

    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {
    }

    public static void main(String[] args) {
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() );
        }
    }
}

//方法参数名
class ParameterNames {
    public static void main(String[] args) throws Exception {
        Method method = ParameterNames.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println( "Parameter: " + parameter.getName() );
        }
    }
}

//测试Optional
@Data
@AllArgsConstructor
class Student {
    private String name;
    private int age;
    private Integer score;
}

class TestOptional{

    public List<Student> initData(){
        Student s1 = new Student("张三", 19, 80);
        Student s2 = new Student("李四", 19, 50);
        Student s3 = new Student("王五", 23, null);
        Student s4 = new Student("赵六", 16, 90);
        Student s5 = new Student("钱七", 18, 99);
        Student s6 = new Student("孙八", 20, 40);
        Student s7 = new Student("吴九", 21, 88);

        return Arrays.asList(s1, s2, s3, s4, null ,s5, s6, s7);
    }

    @Test
    public void beforeJava8() {
        List<Student> studentList = initData();

        for (Student student : studentList) {
            if (student != null) {
                if (student.getAge() >= 18) {
                    Integer score = student.getScore();
                    if (score != null && score > 80) {
                        System.out.println("入选：" + student.getName());
                    }
                }
            }
        }
    }

    @Test
    public void useJava8() {
        List<Student> studentList = initData();
        for (Student student : studentList) {
            Optional<Student> studentOptional = Optional.ofNullable(student);
            Integer score = studentOptional.filter(s -> s.getAge() >= 18).map(Student::getScore).orElse(0);

            if (score > 80) {
                System.out.println("入选：" + student.getName());
            }
        }
    }

}

