package cn.magikarpll.c_three.string;

import java.io.UnsupportedEncodingException;

public class TestString {

    public static void main(String[] args) throws Exception {
        //subStringTest();
        //indexTest();
        byteConvert();

        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();


    }

    public static void subStringTest(){
        String str = "晚来天欲雪 能饮一杯无";
        System.out.println("字符串的长度是："+str.length()); //字符串的雪字打印输出  charAt(int index)
        System.out.println(str.charAt(4)); //取出子串  天欲
        System.out.println(str.substring(2));   //取出从index2开始直到最后的子串，包含2
        System.out.println(str.substring(2,4));  //取出index从2到4的子串，包含2不包含4  顾头不顾尾
    }

    public static void indexTest(){
        String str = new String("赵客缦胡缨 吴钩胡缨霜雪明"); //查找胡在字符串中第一次出现的位置
        System.out.println("\"胡\"在字符串中第一次出现的位置："+str.indexOf("胡")); //查找子串"胡缨"在字符串中第一次出现的位置
        System.out.println("\"胡缨\"在字符串中第一次出现的位置"+str.indexOf("胡缨")); //查找胡在字符串中最后一次次出现的位置
        System.out.println(str.lastIndexOf("胡")); //查找子串"胡缨"在字符串中最后一次出现的位置
        System.out.println(str.lastIndexOf("胡缨")); //从indexof为5的位置，找第一次出现的"吴"
        System.out.println(str.indexOf("吴",5));
    }

    public static void byteConvert() throws UnsupportedEncodingException {
        String str = new String("hhhabc银鞍照白马 飒沓如流星"); //将字符串转换为byte数组，并打印输出
        byte[] arrs = str.getBytes("GBK");
        for(int i=0;i< arrs.length;i++){
            System.out.println(arrs[i]);
        } //将byte数组转换成字符串
        System.out.println();
        String str1 = new String(arrs,"GBK");  //保持字符集的一致，否则会出现乱码
        System.out.println(str1);
    }

}
