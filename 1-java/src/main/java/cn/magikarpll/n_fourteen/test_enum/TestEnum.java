package cn.magikarpll.n_fourteen.test_enum;

import java.util.EnumMap;
import java.util.EnumSet;

public class TestEnum {

    public static void main(String[] args) {
        //枚举类为一个抽象类，因此枚举类型无法再继承父类，只能实现接口
        Enum<COLOR> anEnum = COLOR.RED;

        System.out.println("COLOR");
        for(COLOR color: COLOR.values()){
            System.out.println(color.toString());
        }

        System.out.println("COLOR2");
        for(COLOR2 color: COLOR2.values()){
            System.out.println(color.toString());
        }

        //EnumMap
        EnumMap<COLOR, String> colorStringEnumMap = new EnumMap<COLOR, String>(COLOR.class);
        colorStringEnumMap.put(COLOR.BLUE, "Blue");
        colorStringEnumMap.put(COLOR.YELLOW, "Yellow");
        colorStringEnumMap.put(COLOR.RED, "Red");

        //

    }

}

enum COLOR{
    RED,BLUE,WHITE,BLACK,PINK,YELLOW,GREEN;

    @Override
    public String toString() {
        return super.toString()+ ": " + ordinal();
    }
}

enum COLOR2{
    RED("RED",1),
    BLUE("BLUE",2),
    WHITE("WHITE",3),
    BLACK("BLACK",4),
    PINK("PINK",5),
    YELLOW("YELLOW",6),
    GREEN("GREEN",7);

    private String name;
    private int ordinal;
    COLOR2(String name, int ordinal){
        this.name = name;
        this.ordinal = ordinal;
    }

    @Override
    public String toString() {
        return super.toString()+ ": " + super.ordinal();
    }
}

enum COLOR3{
    RED,BLUE,WHITE,BLACK,PINK,YELLOW,GREEN;
}