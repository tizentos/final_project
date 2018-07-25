package com.udacity.gradle.builditbigger.backend.jokeapi;

import java.util.ArrayList;
import java.util.List;

import ltd.boku.joketeller.JokeTeller;


public class Joke {
    List<String> jokes = new ArrayList<>();
    int numOfJokes;

    public List<String> getJokes() {
        JokeTeller jokeTeller = new JokeTeller();
        jokes = jokeTeller.getJokes(numOfJokes);
        return jokes;
    }

    public void setJokes(List<String> jokes) {
        this.jokes = jokes;
    }

    public int getNumOfJokes() {
        return numOfJokes;
    }

    public void setNumOfJokes(int numOfJokes) {
        this.numOfJokes = numOfJokes;
    }
}