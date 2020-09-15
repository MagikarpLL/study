package cn.magikarpll.m_thirteen.generics;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGenerics {
}

class TestGenericsArray{

    @Test
    public void test(){
//        List<String>[] stringList = new ArrayList<String>()[10];
        List<?>[] arrayLists = new ArrayList<?>[10];
        arrayLists[0] = new ArrayList<String>();
        arrayLists[1] = new ArrayList<Integer>();
        arrayLists[2] = new ArrayList<Boolean>();


        List<String>[] arrayLists1 = new ArrayList[10];

//        List<String>[] lsa = new List<String>[10]; // Not really allowed.
//        Object o = lsa;
//        Object[] oa = (Object[]) o;
//        List<Integer> li = new ArrayList<Integer>();
//        li.add(new Integer(3));
//        oa[1] = li; // Unsound, but passes run time store check
//        String s = lsa[1].get(0); // Run-time error: ClassCastException.

        List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Correct.
        Integer i = (Integer) lsa[1].get(0); // OK
    }

    //泛型方法
    public <T,E> T testFanxing(T t,E e){

        return t;
    }

}
