/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvReader.structs;

import java.security.Timestamp;

/**
 *
 * @author guibgoulart, jpricarte
 */
public class Rating {
    // userId, movieId, rating, timestamp
    protected int userId;
    protected int movieId;
    protected double rating;

    //Constructor
    public Rating(int userId, int movieId, double rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
    }

    // Getters n Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
