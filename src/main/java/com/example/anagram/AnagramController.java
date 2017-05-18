package com.example.anagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> getAnagrams(@PathVariable("word") String word) {
        ArrayList<String> arrayList = anagramService.getAnagrams(word);
        if( arrayList.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(arrayList.toString());
        } else  {
            String status = "HTTP " + HttpStatus.NOT_FOUND + " Not Found" + System.lineSeparator();
            String message = "{ 'message' : 'Couldn't find word '"+  word +"}";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(status + message);
        }
    }
}
