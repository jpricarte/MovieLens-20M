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
    protected String timestamp;

    //Constructor
    public Rating(int userId, int movieId, double rating, String timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timestamp = timestamp;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // Override hashCode n equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rating other = (Rating) obj;
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }
}
