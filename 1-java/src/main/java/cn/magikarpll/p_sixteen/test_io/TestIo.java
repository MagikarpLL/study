package cn.magikarpll.p_sixteen.test_io;

import java.io.*;

public class TestIo {

    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream(new File("a.txt"));
            InputStream bufferInputStream = new BufferedInputStream(inputStream);

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }


    }

}
