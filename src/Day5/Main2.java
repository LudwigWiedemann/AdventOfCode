package Day5;

import java.util.ArrayList;
import java.util.List;

public class Main2 {

    public static void main(String[] args) {
        List<String> input = Shared.FileLoader.loadFile("puzzles\\Day5.txt");
        List<Integer> seat_ids = new ArrayList<>();
        for (String boardingPass : input) {
            int row = Main1.getRow(boardingPass, 0, Main1.CountRows);
            int column = Main1.getColumn(boardingPass,0,Main1.RowLength);
             seat_ids.add((row*8)+column);

        }
        for(int row =0; row <= 127;row++){
            for(int seat = 0; seat <=7; seat++){
                int seat_id = (row*8)+seat;
                if(!seat_ids.contains(seat_id)){
                    int seat_id_u = seat_id+1;
                    int seat_id_D = seat_id-1;
                    if(seat_ids.contains(seat_id_D) && seat_ids.contains(seat_id_u)){
                        System.out.println("Row: " + row + " Seat: " + seat);
                        System.out.println(seat_id);
                    }
                }
            }
        }

    }


}
