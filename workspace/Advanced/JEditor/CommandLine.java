
import java.io.*;
public class CommandLine {
    public static void main(String[] args) {
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir ");
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);           

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
