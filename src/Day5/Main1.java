package Day5;

import java.util.List;

public class Main1 {
    public static int RowLength = 7;
    public static int CountRows = 127;


    public static void main(String[] args) {
        List<String> input = Shared.FileLoader.loadFile("U:\\AdventOfCode\\Day5.txt");
        int max_Seat_Id = 0;
        for (String boardingPass : input) {
            int row = getRow(boardingPass, 0, CountRows);
            int column = getColumn(boardingPass,0,RowLength);
            int seat_id = (row*8)+column;
            if(max_Seat_Id < seat_id){
                max_Seat_Id = seat_id;
            }
        }
        System.out.println(max_Seat_Id);
    }

    public static int getColumn(String boardingPass, int min, int max) {
        for (int i = 0; i < boardingPass.length(); i++) {
            if (boardingPass.charAt(i) == 'R') {
                min = getMiddle(min,max,true);
            }
            if (boardingPass.charAt(i) == 'L') {
                max = getMiddle(min,max,false);
            }
        }
        if (min == max) {
            return min;
        } else {
            System.exit(999);
        }
        return 0;
    }


    public static int getRow(String boardingPass, int min, int max) {
        for (int i = 0; i < boardingPass.length(); i++) {
            if (boardingPass.charAt(i) == 'B') {
                min = getMiddle(min, max, true);

            }
            if (boardingPass.charAt(i) == 'F') {
                max = getMiddle(min, max, false);
            }
        }
        if (min == max) {
            return min;
        } else {
            System.exit(666);
        }
        return 0;
    }

    public static int getMiddle(int min, int max, Boolean roundup) {
        if (roundup) {
            return min + (int) (((double) max - min) / 2 + 0.5);
        }
        return min + (int) (((double) max - min) / 2);
    }

}
