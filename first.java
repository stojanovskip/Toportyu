import java.io.*;

public class first
{
    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Writer writer = null;
        String s = "";
        while(!s.equals("CANCEL"))
        {
            try
            {
                s = br.readLine();
                writer = new BufferedWriter(new OutputStreamWriter(
                         new FileOutputStream("orderlist.txt",true), "utf-8"));
                if(!s.equals("CANCEL")){
                    writer.write(s);
                    writer.write(System.getProperty("line.separator"));}
            } catch (IOException ex) { }
            finally
            {
                try {writer.close();}
                catch (Exception ex) {/*ignore*/}
            }

        }

        System.out.println("Hello World!");
    }
}