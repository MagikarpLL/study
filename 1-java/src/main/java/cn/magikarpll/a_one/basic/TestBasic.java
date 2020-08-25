package cn.magikarpll.a_one.basic;

public class TestBasic {

    public static void main(String[] args) {
        //成员内部类
        A a = new A("a");
        a.print();
        A.InnerA innerA = a.new InnerA("innerA");
        innerA.print();     

        //静态内部类
        B.InnerB innerB = new B.InnerB("innerB");

        //方法内部类
        //见下

        //匿名内部类
        D d = new D();
        d.Click(new OnClickListener(){
            //...
        });

    }

}


//四种内部类
//成员内部类
class A {

    private String name;

    private String title;

    public class InnerA{
        private String name;
        InnerA(String name){
            this.name = name;
        }
        public void print(){
            System.out.println("outter name is " + A.this.name + ";inner name is " + name + "title is " + title);
        }
    }

    A(String name){
        this.name = name;
    }

    public void print(){
        System.out.println("outter a is " + name);
        System.out.println("inner a is " + new InnerA("Inner " + name).name);
    }

}


//静态内部类
class B{
    private String name;
    public static class InnerB{
        private String name;
        InnerB(String name){
            this.name = name;
        }
        public void print(){
            System.out.println("Inner B is" + name);
        }
    }


}

//方法内部类
class C{
    private String name;

    public void print(final String name){
        final String title = "title";
        class InnerC{
            public void print(){
                System.out.println(name);
                System.out.println(title);
            }
        }

    }
}


//匿名内部类
class D{
    public void Click(OnClickListener onClickListener){}

}

class OnClickListener{}