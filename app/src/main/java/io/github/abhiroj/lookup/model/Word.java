package io.github.abhiroj.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhiroj on 9/8/17.
 */

public class Word {


    private List<Word> results;

    private String headword;

    private String part_of_speech;

    private String definiton;

    @SerializedName("text")
    private String example;


    public String getHeadword() {
        return headword;
    }

    public void setHeadword(String headword) {
        this.headword = headword;
    }


    public String getDefiniton() {
        return definiton;
    }

    public void setDefiniton(String definiton) {
        this.definiton = definiton;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getPart_of_speech() {
        return part_of_speech;
    }

    public void setPart_of_speech(String part_of_speech) {
        this.part_of_speech = part_of_speech;
    }

    public List<Word> getResults() {
        return results;
    }

    public void setResults(List<Word> results) {
        this.results = results;
    }
}
