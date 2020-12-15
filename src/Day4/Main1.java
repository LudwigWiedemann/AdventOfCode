package Day4;

import Shared.FileLoader;
import java.util.ArrayList;
import java.util.List;

public class Main1 {
    //
//  Code von Jakob
//


    public static void main(String[] args) {
        List<String> input = FileLoader.loadFile("U:\\AdventOfCode\\Day4.txt");
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
