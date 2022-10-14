package com.programs;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class JavaPrograms {

    public static void main(String[] args) throws Exception {
        //duplicateElements("I am A Test Automation Engineer");
        //removeDuplicates("Automation Tester");
        //arraysMethod();
        //System.out.println(isAnagram());
        //System.exit(0);
        //readDataFromTextFile();
        date();
        //System.exit(0);
        //MstringProgram();

    }

    public static void duplicateElements(String str) {
        LinkedHashMap<Character, Integer> hmap = new LinkedHashMap<Character, Integer>();

        char ch[] = str.toCharArray();
        for (char c : ch) {
            if (c != ' ') {
                System.out.print(c);
                char ch1;
                if (Character.isAlphabetic(c) && Character.isUpperCase(c)) {
                    ch1 = Character.toLowerCase(c);
                } else {
                    ch1 = Character.toUpperCase(c);
                }
                if (hmap.containsKey(c)) {
                    hmap.put(c, hmap.get(c) + 1);
                } else if (hmap.containsKey(ch1)) {
                    hmap.put(ch1, hmap.get(ch1) + 1);
                } else {
                    hmap.put(c, 1);
                }
            }
        }
        System.out.println("\n" + hmap);
    }

    public static void removeDuplicates(String str) {
        Set<Character> set = new LinkedHashSet<Character>();
        str = str.replaceAll("\\s", "");
            char ch[] = str.toCharArray();

            for (char c : ch) {
                set.add(c);
            }
            System.out.println(set);
        }

        public static void arraysMethod(){
        int arr[]={1,9,5,4,3,2};
        Arrays.sort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }

    public static void readDataFromTextFile() throws Exception {
        File file = new File("C:\\Users\\mprashanthreddy\\Desktop\\Prashanth\\Deloitte.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        /*sc.useDelimiter("\\Z");
        System.out.println(sc.next());*/
        }
    }

    public static boolean isAnagram(){
        String str1="bat";
        String str2="tab";
        if(str1.length()!=str2.length()){
            return false;
        }
            char ch1[] = str1.toCharArray();
            char ch2[] = str2.toCharArray();
            Arrays.sort(ch1);
            Arrays.sort(ch2);
            for (int i = 0; i < ch1.length; i++) {
                if(ch1[i]!=ch2[i]){
                   return false;
                }
            }
            return  true;
    }
    public static void stringProgram() {
        String str = "Java Programming";
        String a=str.toUpperCase();
        System.out.println(a);
        a = a.replaceAll("\\s", "");
        System.out.println(a);
        String str1 = "I am   a   Automation Tester";
        str1 = str1.replaceAll("\\s", "");
        System.out.println(str1);

    }
    public static void date(){

        Date date = new Date();
        date.getTime();
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat();
        System.out.println(date);
        String str = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(str);
    }
}


