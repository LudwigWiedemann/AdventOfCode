package Day1;

import java.util.List;

public class Main2 {

    public static void main(String[] args){
        System.out.print(add());
    }

    public static int add() {
        List<String> zahlen = Shared.FileLoader.loadFile("U:\\AdventOfCode\\Day1.txt");
        for(int i = 0; i < zahlen.size(); i++){
            for(int j = 1; j < zahlen.size(); j++){
                for( int z = 0; z< zahlen.size(); z++) {
                    if (Integer.valueOf(zahlen.get(i)) + Integer.valueOf(zahlen.get(j)) + Integer.valueOf(zahlen.get(z))== 2020) {
                        return Integer.valueOf(zahlen.get(i)) * Integer.valueOf(zahlen.get(j)) * Integer.valueOf(zahlen.get(z));
                    }
                }
            }
        }
        return 0;
    }
}
