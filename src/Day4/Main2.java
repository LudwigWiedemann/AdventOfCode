package Day4;

import Shared.FileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
//
//  Code von Ludwig
//
    public  static List<Character> allowedLetters = new ArrayList<>();

    public static void main(String[] args) {
        List<String> input = FileLoader.loadFile("U:\\AdventOfCode\\Day4.txt");
        //System.out.println(input);
        allowedLetters.add('a');
        allowedLetters.add('b');
        allowedLetters.add('c');
        allowedLetters.add('d');
        allowedLetters.add('e');
        allowedLetters.add('f');
        allowedLetters.add('0');
        allowedLetters.add('1');
        allowedLetters.add('2');
        allowedLetters.add('3');
        allowedLetters.add('4');
        allowedLetters.add('5');
        allowedLetters.add('6');
        allowedLetters.add('7');
        allowedLetters.add('8');
        allowedLetters.add('9');

//        for(int i = 0; i <= 9; i++) {
//            char c = (char) i;
//            allowedLetters.add(c);
//        }
        int count = 0;
        for(String passPort: input){
            if(checkPassport(passPort) && check(passPort)){
                count++;
                System.out.println("Valid");
            }else{
                System.out.println("Invalid");
            }
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

    public static boolean checkPassport(String passPort){
        List<String> singlePassport = Arrays.asList(passPort.split(" "));;
        //System.out.println(singlePassport);
        for(int i = 0; i < singlePassport.size(); i++){
            String keyValuePair = singlePassport.get(i);
            String[] keyValue = keyValuePair.split(":");
            String key = keyValue[0];
            String value = keyValue[1];
            switch(key) {

                case "byr":
                    if(!handleYear(value, 1920, 2002)){
                        System.out.println("byr is false");
                        return false;
                    }
                    break;
                case "iyr":
                    if(!handleYear(value, 2010, 2020)){
                        System.out.println("iyr is false");
                        return false;
                    }
                    break;
                case "eyr":
                    if(!handleYear(value, 2020, 2030)){
                        System.out.println("eyr is false");
                        return false;
                    }
                    break;
                case "hgt":
                    if(!handleHeight(value)){
                        System.out.println("hgt is false");
                        return false;
                    }
                    break;
                case "hcl":
                    if(!handleHCL(value)){
                        System.out.println("hcl is false");
                        return false;
                    }
                    break;
                case "ecl":
                    if(!handeleECL(value)){
                        System.out.println("ecl is false");
                        return false;
                    }
                    break;
                case "pid":
                    if(!handlePID(value)){
                        System.out.println("pid is false");
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    public static boolean handleYear(String value, int min, int max){

        int intValue = Integer.valueOf(value);
        if(intValue >= min && intValue <= max){
            return true;
        }else{
            return false;
        }
    }

    public static boolean handleHeight(String value){
        if(value.contains("in")){
            value = value.replace("in", "");
            return handleYear(value, 59,76);
        }else if(value.contains("cm")){
            value = value.replace("cm", "");
            return  handleYear(value, 150, 193);
        }
        return false;
    }

    public static boolean handleHCL(String value){
        if(!(value.length() == 7) || !(value.charAt(0) == '#')){
            return false;
        }

        for(int i = 1; i < value.length(); i++){
            if(!allowedLetters.contains(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean handeleECL(String value){
        if(!(value.contains("amb") || value.contains("blu") ||value.contains("brn") ||value.contains("gry") ||value.contains("grn") ||value.contains("hzl") ||value.contains("oth"))){
            return false;
        }
        return true;
    }

    public static boolean handlePID(String value){
        if(value.length() != 9){
            return false;
        }
        try{
            int zahl = Integer.valueOf(value);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
