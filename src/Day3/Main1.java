package Day3;

import java.util.List;

public class Main1 {
    public static void main(String[] args){
        List<String> input = Shared.FileLoader.loadFile("U:\\AdventOfCode\\Day3.txt");
        int count=0;
        int r =0;
        for(int i = 0; i< input.size(); i++) {
            System.out.println(input.get(i).charAt(r));
            if(String.valueOf(input.get(i).charAt(r)).equals("#")){
                count++;
            }
            r=r+3;
            if(r>=input.get(i).length()){
                r=r-input.get(i).length();
            }
        }
        System.out.println("##########");
        System.out.println(count);


    }
}
