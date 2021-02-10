package Day2;

import java.util.List;

public class Main1 {

    public static void main(String[] args){
        List<String> passwoerter = Shared.FileLoader.loadFile("U:\\AdventOfCode\\Day2.txt");
        int anzahl = 0;
        for(String all : passwoerter){
            String[] cases = all.split(":");
            String[] first = cases[0].split("-");
            String[] last = first[1].split(" ");
            String sucher = last[1];
            cases[1] = cases[1].trim();
            int count =0;
            for(int i = 0; i < cases[1].length(); i++){
                 if(String.valueOf(cases[1].charAt(i)).equals(sucher)){
                     count++;
                 }
             }
            System.out.println(count);
            System.out.println(first[0]);
            System.out.println(last[0]);
            System.out.println(sucher);
            System.out.println();
            if(count >= Integer.valueOf(first[0])  && count <= Integer.valueOf(last[0])){
                anzahl++;
            }
        }
        System.out.print(anzahl);
    }
}
