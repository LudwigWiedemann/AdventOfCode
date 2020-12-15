package Day2;

import java.util.List;

public class Main2 {
    public static void main(String[]args){
        List<String> passwoerter = Shared.FileLoader.loadFile("U:\\AdventOfCode\\Day2.txt");
    int count =0;
        for(int i = 0; i < passwoerter.size(); i++){

            String firstSplit[] = passwoerter.get(i).split(":");
            String secondSplit[] = firstSplit[0].split(" ");
            String thirdSplit[] = secondSplit[0].split("-");
            String first = thirdSplit[0];
            String second = thirdSplit[1];
            String sucher = secondSplit[1];
            String passwort = firstSplit[1].trim();
            boolean erster = false;
            boolean zweiter = false;
            if(String.valueOf(passwort.charAt(Integer.valueOf(first)-1)).equals(sucher)){
                erster = true;
            }

            if(String.valueOf(passwort.charAt(Integer.valueOf(second)-1)).equals(sucher)){
                zweiter = true;
            }
            if((erster && !zweiter) || (zweiter && !erster)){
                count++;
            }
        }
        System.out.print(count);
    }
}
