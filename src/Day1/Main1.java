package Day1;

import java.util.List;

public class Main1 {

    public static void main(String[]args){
        System.out.print(add());
    }


    public static int add(){
    List<String> zahlen = Shared.FileLoader.loadFile("U:\\AdventOfCode\\Day1.txt");

        for(int i = 0; i < zahlen.size(); i++){
            for(int j = 1; j < zahlen.size(); j++){
                if(Integer.parseInt(zahlen.get(i)) + Integer.parseInt(zahlen.get(j)) == 2020){
                    return Integer.parseInt(zahlen.get(i)) * Integer.parseInt(zahlen.get(j));
                }
            }
        }
        return 0;
    }
}
