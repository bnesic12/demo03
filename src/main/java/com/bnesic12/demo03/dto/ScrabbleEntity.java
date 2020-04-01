package com.bnesic12.demo03.dto;

import org.springframework.stereotype.Component;

@Component
public class ScrabbleEntity {
    public final char[] letters =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                    'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                    'U', 'V', 'W', 'X', 'Y', 'Z'};
    public final int[] points =
            {1, 3, 3, 2, 1, 4, 2, 4, 1, 8,
                    5, 1, 3, 1, 1, 3, 10, 1, 1, 1,
                    1, 4, 4, 8, 4, 10};
    public final int[] distribution =
            {9, 2, 2, 4, 12, 2, 3, 2, 9, 1,
                    1, 4, 2, 6,  8, 2, 1, 6, 4, 6,
                    4, 2, 2, 1, 2, 1};

    private String allLetters;
    private String allPoints;
    private String allDistributions;
    private String scrabbleWord;
    private String score;
    private String message;

    public String getAllLetters() {
        return allLetters;
    }

    public void setAllLetters(String allLetters) {
        this.allLetters = allLetters;
    }

    public String getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(String allPoints) {
        this.allPoints = allPoints;
    }

    public String getAllDistributions() {
        return allDistributions;
    }

    public void setAllDistributions(String allDistributions) {
        this.allDistributions = allDistributions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ScrabbleEntity() {
        allLetters="";
        for(int i=0; i<letters.length; i++) {
            allLetters+=Character.toString(letters[i]);
            if(i!=letters.length-1) {
                allLetters+=", ";
            }
        }
        allPoints="";
        for(int i=0; i<points.length; i++) {
            allPoints+=Integer.toString(points[i]);
            if(i!=points.length-1) {
                allPoints+=", ";
            }
        }
        allDistributions="";
        for(int i=0; i<distribution.length; i++) {
            allDistributions+=Integer.toString(distribution[i]);
            if(i!=distribution.length-1) {
                allDistributions+=", ";
            }
        }
        //System.out.println("ScrabbleEntity.ctor(): end");
    }
    public void setScrabbleWord(String scrabbleWord) {
        this.scrabbleWord=scrabbleWord;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score=score;
    }

    public String getScrabbleWord() {
        return scrabbleWord;
    }

    // Validates input word entered by user. Returns:
    //   - false if empty string or multiple words
    //   - true if single word
    private boolean validateWord(String inWord) {
        boolean rc = false;
        if(inWord!=null && !inWord.isEmpty()) {
            rc = true;
            for(int i=0; i<inWord.length(); i++) {
                char ch = inWord.charAt(i);
                if(!Character.isLetter(ch)) {
                    rc = false;
                    break;
                }
            }
        }
        return rc;
    }

    private String scrabble() {
        message="none";
        // Check input word. Exit if unsuccessful.
        if (null == scrabbleWord) {
            this.score="none";
            return message;
        }
        if(!validateWord(scrabbleWord)) {
            this.score="-1";
            message="Error: empty string or multiple words; score=-1";
            return message;
        }
        scrabbleWord = scrabbleWord.toUpperCase();

        int score = 0;
        boolean hasWildcard = false;
        int charAppearance[] = new int[letters.length];

        for(int i = 0; i< scrabbleWord.length(); i++) {
            char ch = scrabbleWord.charAt(i);
            int letterIndex = -1;

            for(int j = 0; j< letters.length; j++) {
                if(ch== letters[j]) {
                    letterIndex = j;
                    break;
                }
            }
            if(letterIndex<0) {
                this.score="-1";
                message="Internal error when processing the input string; score=-1";
                return message;
            }
            if(charAppearance[letterIndex]>= distribution[letterIndex]) {
                if(hasWildcard) {
                    this.score="-1";
                    message="Violation of the rule for word "+scrabbleWord+": letter '" + ch + "' appears more than max allowed " +
                            distribution[letterIndex] + " times and wildcard has already been used; score=-1.";
                    return message;

                } else {
                    hasWildcard = true;
                }
            } else {
                charAppearance[letterIndex]++;
                score += points[letterIndex];
            }

        }
        this.score=Integer.toString(score);
        message = "Input string: '"+ this.scrabbleWord+"' has score: "+this.score;
        if(hasWildcard) {
            message = message +" (wildcard)";
        }
        return message;
    }

    public String toString() {
        scrabble();
        return score;
    }

    public static void main(String[] args) {
        String[] tests = {"kick", "test", "captain", null, "", "\t \t"};
        ScrabbleEntity scrabbleEntity = new ScrabbleEntity();
        for(String test : tests) {
            scrabbleEntity.setScrabbleWord(test);
            System.out.println(scrabbleEntity.scrabble());
        }
    }
}
