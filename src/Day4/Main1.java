package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main1 {
    //
//  Code von Jakob
//
    public static List<String> loadFile(String path) {
        File file = new File(path);
        if (!file.canRead() || !file.isFile())
            System.exit(0);
        List<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int i = 0;
            String line;
            input.add("");
            while ((line = br.readLine()) != null) {
                if(line.equals("")){
                    input.add("");
                    i++;
                }else{
                    input.set(i, (input.get((i))+ line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void main(String[] args) {
        List<String> input = loadFile("U:\\Test.txt");
        int count = 0;
        for(String all : input){
            if(check(all)){
                System.out.println("Valid");
                count++;
            }else{
                System.out.println("Invalid");
            }
            /*if(all.contains("byr") && all.contains("iyr") && all.contains("eyr") && all.contains("hgt") && all.contains("hcl") && all.contains("ecl") && all.contains("pid")){
                System.out.println("Valid");
            }else{
                System.out.println("Invalid");
            }*/
        }
        System.out.println(count);
    }



    public static boolean check(String element){
        List<String> required = new ArrayList<>();
        required.add("byr");
        required.add("iyr");
        required.add("eyr");
        required.add("hgt");
        required.add("hcl");
        required.add("ecl");
        required.add("pid");
        for(String all : required){
            if(!element.contains(all)){
                return false;
            }
        }
        return true;
    }
}
