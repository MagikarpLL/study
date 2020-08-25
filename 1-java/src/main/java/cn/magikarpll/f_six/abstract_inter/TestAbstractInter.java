package cn.magikarpll.f_six.abstract_inter;

public class TestAbstractInter extends People implements TestInterface{

    public TestAbstractInter(String name, int age){
        super(name, age);
    }

    @Override
    public void talk() {

    }

    @Override
    public void test2() {

    }

    @Override
    public void test() {

    }
}


abstract class People{
    //父类属性私有化
    private String name;
    private int age;
    //提供父类的构造器
    public People(String name,int age){
        this.name = name;
        this.age = age;
    }
    //提供获取和设置属性的getter()/setter()方法
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //提供一个抽象方法
    public abstract void talk();

    void test(){
        System.out.println("ddd");
    }
}

//定义一个接口
interface Study{
    //设置课程数量为3
    int COURSENUM = 3;
    //构建一个默认方法
    default void stu(){
        System.out.println("学生需要学习"+COURSENUM+"门课程");
    }
}

//再定义一个接口
interface Write{
    //定义一个抽象方法
    void print();
}

//子类继承People,实现接口Study,Write
class Student extends People implements Study,Write{
    //通过super关键字调用父类的构造器
    public Student(String name, int age) {
        super(name, age);
    }
    //实现父类的抽象方法
    public void talk() {
        System.out.println("我的名字叫"+this.getName()+",今年"+this.getAge()+"岁");
    }
    //实现Write接口的抽象方法
    public void print() {
        System.out.println("学生会写作业");
    }
}