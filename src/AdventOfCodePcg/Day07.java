package AdventOfCodePcg;

import Shared.FileLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Day07 {
    List<String> input;


    public Day07() {
        this.input = FileLoader.loadFile("puzzles/Day07Test.txt");
    }

    public Day07(String path) {
        this.input = FileLoader.loadFile(path);
    }

    public static void main(String[] args) {
        List<String[]> bags = new ArrayList<>();
        List<String> contains = new ArrayList<>();

        Day07 d7 = new Day07();
        Iterator<String> iter = d7.input.iterator();
        while(iter.hasNext()) {
            String rule = iter.next();
            bags.add(d7.splitRule(rule));
        }



        for(int i = 0; i < bags.size(); i++) {
            if(bags.get(i)[1].contains("shiny gold")){

                contains.add(bags.get(i)[0]);
                bags.remove(bags.get(i));
            }
        }
        System.out.println(contains);

//
//        }

    }

    String[] splitRule(String rule){
        return rule.split("contain");
    }

//    public static void main(String[] args) {
//        Day07 d7 = new Day07("puzzles/Day07.txt");
//        List<String> rulesFrontPart = new ArrayList<>();
//        List<String> rulesBackPart = new ArrayList<>();
//        List<String> directlyContainsGold = new ArrayList<>();
//        List<String> indirectlyContainsGold = new ArrayList<>();
//
//        Iterator<String> iter = d7.input.iterator();
//        while (iter.hasNext()) {
//            String rule = iter.next();
//            rulesFrontPart.add(Arrays.asList(rule.split("contain")).get(0));
//            rulesBackPart.add(Arrays.asList(rule.split("contain")).get(1));
//        }
//        for(int i = 0; i < rulesBackPart.size(); i++){
//            String part = rulesBackPart.get(i);
//            if( part.contains("shiny gold")){
//                directlyContainsGold.add(rulesFrontPart.get(i));
//            }
//        }
//        System.out.println(directlyContainsGold);
//        for(int i = 0; i < rulesBackPart.size(); i++){
//            String part = rulesBackPart.get(i);
//            for( int j = 0; j < directlyContainsGold.size(); j++) {
//                String searchWord = directlyContainsGold.get(j);
//                searchWord = searchWord.substring(0,searchWord.indexOf("bag"));
//                if (part.contains(searchWord.trim()) && !(indirectlyContainsGold.contains(rulesFrontPart.get(i)))) {
//                    indirectlyContainsGold.add(rulesFrontPart.get(i));
//                }
//
//            }
//        }
//        System.out.println(indirectlyContainsGold);
//        System.out.println(indirectlyContainsGold.size()+directlyContainsGold.size());
//    }


}


