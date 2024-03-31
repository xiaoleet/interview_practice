import java.io.*;

public class myOutputStream {
    //创建文件，并写入一个字符
    public static void main(String args[]) throws IOException {
        //1.创建文件，并写入
        writeOutputStream();
        //writeFile();
    }

    public static void writeOutputStream(){
        try{
            FileOutputStream outfile = new FileOutputStream("io/outputStream.txt",true);//false 就不追加，true就追加
            outfile.write('\n');
            outfile.write('a');
            outfile.write('\n');
            String s = "这是一个文件";
            outfile.write(s.getBytes());
            outfile.close();
            System.out.println("写入文件成功！！！");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void writeFile(){
        File outfile = new File("io/outputFile.txt");//这里是创建了对象，还没有创建文件
        try{
            if(outfile.createNewFile()){//通过createNewFile()创建文件
                System.out.println("创建文件成功！！！");
            } else {
                System.out.println("创建文件失败！！！");
            }
            //    FileOutputStream fos = new FileOutputStream(outfile);
            //    fos.write('a');//用字节流写入
            FileWriter writer = new FileWriter(outfile,true); //为outfile创建写入流，true模式表示每次都是追加，不是覆盖

            writer.write("\n");
            writer.write("这是一个文件\n");
            writer.write("aaa");
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
