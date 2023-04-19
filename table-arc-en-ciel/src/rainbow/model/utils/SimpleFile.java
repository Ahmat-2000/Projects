package rainbow.model.utils;

import java.io.*;

public class SimpleFile extends AbstractFileReader{

    public SimpleFile(String filename){
        super(filename);
    }

    public String readFile(){
        try{
            File file = new File(this.filename);
            java.io.FileReader fr = new java.io.FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null){
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            return sb.toString();
        } catch(IOException e){
            System.out.print(e.getMessage());
        }
        return null;
    }

    public void writeFile(String content){
        try{
            File file = new File(this.filename);
            file.createNewFile();
            FileWriter fw = new FileWriter(this.filename);
            fw.write(content);
            fw.close();
        } catch(IOException e){
            System.out.print(e.getMessage());

        }
    }
}
