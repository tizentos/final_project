package com.udacity.gradle.builditbigger.backend.jokeapi;


import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.udacity.gradle.builditbigger.backend.jokeapi.Joke;

@Api(
        name = "jokeTellingApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com.jokeapi",
                ownerName = "joke.backend.builditbigger.gradle.udacity.com.jokeapi",
                packagePath = ""
        )
)
public class JokeTellerEndpoint {

    @ApiMethod(name="getJokes")
    public Joke getJokes(@Named("numOfJokes") int numOfJokes){
        Joke newJokesResponse=new Joke();
        return newJokesResponse;
    }
}
