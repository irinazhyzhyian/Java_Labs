package com.example.zhyzhyian;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Search {

    private static final String TEXT_FILE = "/Users/user/irina/Java_Lab2/src/main/resources/text.txt";

    public List<String> searchWord(String searchStr) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(TEXT_FILE));
        StringBuilder text = new StringBuilder();

        while(scan.hasNext()){
            text.append(scan.nextLine());
        }

        List<String> result = new ArrayList<>();

        String[] sentences = text.toString().split("\\.");
        Arrays.stream(sentences).forEach(sentence -> {
            if(sentence.contains(searchStr)){
                result.add(sentence);
            }
        });

        return result;
    }
}
