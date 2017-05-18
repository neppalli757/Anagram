package com.example.anagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Sravan on 5/16/2017.
 */

@RestController
public class AnagramController {
    @Autowired
    AnagramService anagramService;

    @RequestMapping("/word/{word}")
    public ArrayList<String> getAnagram(@PathVariable("word") String word) {
        return anagramService.getAnagrams(word);
    }
}
