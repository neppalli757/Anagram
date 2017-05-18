package com.example.anagram;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * Created by Sravan on 5/17/2017.
 */

    @Service
    public class AnagramService {

        ArrayList<String> dictionary;

        public ArrayList<String> getDictionary() {

            try {
                String url = "https://users.cs.duke.edu/~ola/ap/linuxwords";

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                // optional default is GET
                con.setRequestMethod("GET");

                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                dictionary = new ArrayList<>();
                while ((inputLine = in.readLine()) != null) {
                    dictionary.add(inputLine);
                }
                in.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return dictionary;
        }

        public ArrayList<String> getAnagrams(String givenWord) {
            if(dictionary == null) {
                dictionary = getDictionary();
            }
            ArrayList<String> anagramList = new ArrayList<>();

            for(String dictionaryWord : dictionary) {

                boolean anagram = isAnagram(givenWord, dictionaryWord);
                if(anagram) {
                    anagramList.add(dictionaryWord);
                }
            }
            Collections.sort(anagramList);
            return anagramList;
        }

        public boolean isAnagram(String firstWord, String secondWord) {
            firstWord = firstWord.toLowerCase();
            secondWord = secondWord.toLowerCase();
            boolean result = false;
            char[] word1 = firstWord.toCharArray();
            char[] word2 = secondWord.toCharArray();
            if(!firstWord.equals(secondWord) && firstWord.length() == secondWord.length()) {
                Arrays.sort(word1);
                Arrays.sort(word2);
                result = Arrays.equals(word1, word2);

            }

            return result;
        }


    }


