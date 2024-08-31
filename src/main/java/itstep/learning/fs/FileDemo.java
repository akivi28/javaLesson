package itstep.learning.fs;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FileDemo {

    public void run(){
        try( InputStream rs = this.getClass().getClassLoader().getResourceAsStream("db.ini")){
            String content = readStream(rs);
            Map<String,String> ini = new HashMap<>();

            String[]lines = content.split("\n");
            for(String line : lines){
                String[] parts = line.split("=");
                ini.put(parts[0],parts[1].trim());
            }

            System.out.println(String.format("jdbs:%s://%s:%s/%s",
                    ini.get("dbms"),
                    ini.get("host"),
                    ini.get("port"),
                    ini.get("schema")));

        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void runFile(){
        System.out.println("File System");

        File file = new File("test.txt");

        ByteArrayOutputStream byteBuilder =  new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len;

        try(InputStream is = new FileInputStream(file)){
            System.out.println(readStream(is));
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }

    public String readStream(InputStream in) throws IOException {
        byte[] buffer = new byte[4096];
        try(BufferedInputStream bis = new BufferedInputStream(in);
            ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();){

            int len;
            while((len = bis.read(buffer)) != -1){
                byteBuilder.write(buffer, 0, len);
            }
            return byteBuilder.toString(StandardCharsets.UTF_8.name());
        }
    }
    public String readStream(InputStream in, Charset charset ) throws IOException {
        byte[] buffer = new byte[4096];
        try(BufferedInputStream bis = new BufferedInputStream(in);
            ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();){

            int len;
            while((len = bis.read(buffer)) != -1){
                byteBuilder.write(buffer, 0, len);
            }
            return byteBuilder.toString(charset.name());
        }
    }
}
