package encrypt.method;

import java.io.*;

//https://blog.csdn.net/xietansheng/article/details/88082266
public class IOUtils {

    public static void writeFiles(String data, File file) throws IOException {
        OutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(file);
            outputStream.write(data.getBytes());
            outputStream.flush();
        }finally {
            outputStream.close();
        }
    }

    public static String readFiles(File file)throws IOException{
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try{
            inputStream = new FileInputStream(file);
            outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len = -1;
            while((len = inputStream.read(bytes)) != -1){
                outputStream.write(bytes, 0, len);
            }
            outputStream.flush();
            byte[] data = outputStream.toByteArray();
            return new String(data);
        }finally {
            inputStream.close();
            outputStream.close();;
        }
    }


}
