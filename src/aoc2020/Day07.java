package aoc2020;

import Shared.FileLoader;

import java.util.*;

public class Day07 {
    List<String> input;
    private static final Map<String, List<String>> rules = new HashMap<>();

    public Day07() {
        this("puzzles/Day07Test.txt");
    }

    public Day07(String path) {
        this.input = FileLoader.loadFile(path);
        // key - value
        // "light red" - "bright white", "muted yellow"

        for (String rule : input) {
            String[] kv = rule.split(" bags contain ");

            String[] contains = kv[1]
                    .replace(" bags", "")
                    .replace(" bag", "")
                    .replace(".", "")
                    .replace("no other", "")
                    .split(", ");

            if (!contains[0].isEmpty())
                rules.put(kv[0], Arrays.asList(contains));
        }

        System.out.println(rules);
    }

    public void part1() {
        int count = 0;
        for (String color : rules.keySet()) {
            boolean containsShinyGold = contains(color, "shiny gold");
            System.out.println(color + " " + containsShinyGold);
            if (containsShinyGold) {
                count++;
            }
        }
        System.out.println(count);
    }

    public void part2() {
        int bagsInside = containedBags("shiny gold");
        System.out.println(bagsInside);
    }

    private int containedBags(String color) {
        int bagsInside = 0;
        List<String> contained = rules.getOrDefault(color, Collections.emptyList());
        System.out.println("CONTAINED " + contained);
        for (String c : contained) {
            System.out.println("das ist c " + c);
            String[] countAndColor = c.split(" ", 2);
            int count = Integer.parseInt(countAndColor[0]);
            String innerColor = countAndColor[1];
            bagsInside += count + (count * containedBags(innerColor));

        }
        return bagsInside;
    }

    private boolean contains(String color, String searchColor) {
        List<String> contained = rules.getOrDefault(color, Collections.emptyList());
        for (String c : contained) {
            String[] countAndColor = c.split(" ", 2);
            String containedColor = countAndColor[1];
            if (containedColor.equals(searchColor))
                return true;
            if (contains(containedColor, searchColor))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Day07 d7 = new Day07("puzzles/Day07.txt");
        d7.part1();
        d7.part2();
    }


//        List<String[]> bags = new ArrayList<>();
//        List<String> contains = new ArrayList<>();
//
//        Iterator<String> iter = d7.input.iterator();
//        while(iter.hasNext()) {
//            String rule = iter.next();
//            bags.add(d7.splitRule(rule));
//        }
//
//

//
//        for(int i = 0; i < bags.size(); i++) {
//            if(bags.get(i)[1].contains("shiny gold")){
//
//                contains.add(bags.get(i)[0]);
//                bags.remove(bags.get(i));
//            }
//        }
//        System.out.println(contains);

//
//        }

//}

//    String[] splitRule(String rule){
//        return rule.split("contain");
//    }

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


