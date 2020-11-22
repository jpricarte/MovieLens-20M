/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvReader.structs;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author guibgoulart, jpricarte
 */
public class Movie {

    // "movieId","title","genres"
    protected int movieId;
    protected String title;
    protected List<String> genres;

    // Constructor
    public Movie(int movieId, String title, List<String> genres) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
    }

    // Getters n Setters

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

}
