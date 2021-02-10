package AdventOfCodePcg;

import Shared.FileLoader;

import java.util.*;

public class Day08 {
    private List<String> input;
    private List<String[]> commands = new ArrayList<>();

    private int accumulator = 0;

    private List<Integer> iHasBeen = new ArrayList<>();
    private int placeInProgram = 0;


    public Day08() {
        this("puzzles/Day08Test.txt");

    }

    public Day08(String path) {
        this.input = FileLoader.loadFile(path);
        for (String command : input) {
            commands.add(command.split(" "));

        }
    }

    public static void main(String[] args) {
        Day08 d8 = new Day08("puzzles/Day08.txt");
        Day08 d81 = new Day08();
        System.out.println(d8.part1());
        System.out.println(d8.part2());
    }

    public int part1() {
        while (!repeatsItself()) {
            getInstruction(placeInProgram);
        }
        return accumulator;
    }

    public int part2() {
        for (int operToChange = 0; operToChange < commands.size(); operToChange++) {

            List<String[]> temp = getChangedCopyOfCommands(operToChange);
//            gibListeAus(temp);
            standartWerteWiederherstellen();

            while (!repeatsItself()) {
                getInstruction(placeInProgram, temp);
                if (terminatedCorrectly()) {
                    return accumulator;
                }
            }
        }
        return accumulator;
    }

    private List<String[]> getChangedCopyOfCommands(int operToChange) {
        List<String[]> temp = new ArrayList();
        for(int z = 0; z < commands.size(); z++) {
            if( z == operToChange) {
                String[] neuerBefehl = new String[2];
                neuerBefehl[0] = "nop";
                neuerBefehl[1] = commands.get(operToChange)[1];
                temp.add(neuerBefehl);
            }else{
                temp.add(commands.get(z));
            }
        }
        return temp;
    }

    private void gibListeAus(List<String[]> list) {
        for(String[] ausgabe: list){
            System.out.println(" TEMP       : " +   ausgabe[0] + " " +ausgabe[1]);
        }
    }
    public void standartWerteWiederherstellen() {
        accumulator = 0;
        placeInProgram = 0;
        iHasBeen.clear();
    }



    private String changeNopAndJmp(String op) {
        switch (op.trim()) {
            case "jmp":
                return "nop";
            case "nop":
                return "jmp";
            default:
                return "acc";
        }
    }

    private boolean terminatedCorrectly() {
        if (placeInProgram == commands.size()) {
            return true;
        }
        return false;
    }


    private boolean repeatsItself() {
        if (!iHasBeen.contains(placeInProgram)) {
            return false;
        }
        return true;
    }

    private String getOperator(int i) {
        String[] command = commands.get(i);
        String operator = command[0];
        return operator;
    }

    private String getOperator(int i, List<String[]> liste) {
        String operator = liste.get(i)[0];
        return operator;
    }

    private int getValue(int i) {
        String[] command = commands.get(i);
        int value = Integer.parseInt(command[1]);
        return value;
    }

    private int getValue(int i, List<String[]> liste) {
        int value = Integer.parseInt(liste.get(i)[1]);
        return value;
    }

    private void getInstruction(int i) {
        iHasBeen.add(i);
        String operator = getOperator(i);
        int value = getValue(i);
        computeInstruction(operator, value);
    }

    private void getInstruction(int i, List<String[]> list) {
        iHasBeen.add(i);
        String operator = getOperator(i, list);
        int value = getValue(i, list);
        computeInstruction(operator, value);
    }

    private void computeInstruction(String instruction, int value) {
        switch (instruction) {
            case "acc":
                accumulator += value;
                System.out.println("acc " + value);
                placeInProgram++;
                break;
            case "jmp":
                placeInProgram += value;
                System.out.println("jmp " + value);

                break;
            case "nop":
                System.out.println("nop " + value);
                placeInProgram++;
                break;
        }
    }


}
