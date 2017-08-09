package io.github.abhiroj.lookup.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by abhiroj on 9/8/17.
 */

public class Word {



    private List<Word> results;

    private List<Senses> senses;

    private String headword;

    private String part_of_speech;


    public String getHeadword() {
        return headword;
    }

    public void setHeadword(String headword) {
        this.headword = headword;
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

    public List<Senses> getSenses() {
        return senses;
    }

    public String getDefintion(Senses obj){
        return obj.getDefinition();
    }

    public String getExample(Senses obj){
        List<Examples> example=obj.getExamples();
        return (example==null)?"":example.get(0).getText();
    }

    public void setSenses(List<Senses> senses) {
        this.senses = senses;
    }

    public static class Senses{
        private String definition;
        private List<Examples> examples;

        public String getDefinition() {
            return definition;
        }

        public void setDefinition(String definition) {
            this.definition = definition;
        }

        public List<Examples> getExamples() {
            return examples;
        }

        public void setExamples(List<Examples> examples) {
            this.examples = examples;
        }
    }

    private static class Examples{
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
