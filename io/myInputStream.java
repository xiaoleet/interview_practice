import java.io.*;
import java.nio.charset.StandardCharsets;

public class myInputStream {
    public static void main(String args[]){
        //InputStreamRead();
        //FileReader();
        //InputStreamReadLine();
        //FileReaderLine();
        FileReaderLine2OutFile();
    }
    //字节流的方式将文件读入
    public static void InputStreamRead(){
        try{
            FileInputStream inf = new FileInputStream("io/a.txt");
            int data;
            while ((data=inf.read())!=-1){//每次读1个字节，指针往下指一个字节，中文是2个字节，所以，会导致读中文乱码。
                System.out.print((char) data);
            }
            inf.close(); // 记得关闭文件流
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字符流的方式将文件读入
    public static void FileReader(){
        try{
            FileReader reader = new FileReader("io/a.txt");//每次读1个字符，字符大小和GBK，UTF-8、UTF-16这些编码有关系
            int data;
            while ((data=reader.read())!=-1){
                System.out.print((char) data);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字节流+按行读
    public static void InputStreamReadLine(){
        try{
            FileInputStream inf = new FileInputStream("io/a.txt");
            InputStreamReader isr = new InputStreamReader(inf, StandardCharsets.UTF_8);//添加编码方式，可以读中文
            BufferedReader bfr = new BufferedReader(isr);
            String line;
            while ((line=bfr.readLine())!=null){
                System.out.println(line);
            }
            inf.close();
            isr.close(); // 记得关闭文件流
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字符流的方式将文件读入
    public static void FileReaderLine(){
        try{
            FileReader reader = new FileReader("io/a.txt");//每次读1个字符，字符大小和GBK，UTF-8、UTF-16这些编码有关系
            BufferedReader bfr = new BufferedReader(reader);
            String line;
            while ((line=bfr.readLine())!=null){
                System.out.println(line);
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //通义实验室：一个文件包含AAABBCC的行写入到另外一个文件中
    public static void FileReaderLine2OutFile(){
        File file = new File("io/regex_match.txt");//待写入文件
        FileWriter writer = null;
        try{
            file.createNewFile();
            writer = new FileWriter(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            FileReader reader = new FileReader("io/a.txt");//源文件。每次读1个字符，字符大小和GBK，UTF-8、UTF-16这些编码有关系
            BufferedReader bfr = new BufferedReader(reader);
            String line;
            while ((line=bfr.readLine())!=null){
                System.out.println(line);
                if(line.contains("AAABBBCC")){
                    writer.write(line+"\n");
                }
            }
            //关闭流的原则是按照创建流的相反顺序进行关闭，这是因为内层的流通常依赖于外层的流，关闭外层的流会自动关闭内层的流，从而确保资源的释放和内存的回收。因此，在关闭流时，应该先关闭最内层的流，然后依次向外层关闭。
            bfr.close();
            reader.close();
            writer.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
