package cn.magikarpll.i_nine.testclass;

public class TestClass {

    Class classClass = new TestClass().getClass( );
    TestClass myobject = new TestClass();

    {
        try {
            myobject.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


}
