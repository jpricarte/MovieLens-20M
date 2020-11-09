package structs.hashTable;


import csvReader.structs.Rating;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MovieNode implements HashObject {
    private int id;
    private String movieName;
    private List<String> genres;
    private LinkedList<Float> ratingList;
    private float ratingAverage;

    public MovieNode(int movieId, String movieName, List<String> genres) {
        this.id = movieId;
        this.movieName = movieName;
        this.genres = genres;
        this.ratingList = new LinkedList<>();
    }

    public void newRating(float rating) {
        ratingList.add(rating);
    }

    public double updateRatingAverage() {
        float newAverage = 0;
        for (var rating : this.ratingList) {
            newAverage += rating;
        }
        this.ratingAverage = newAverage / this.ratingList.size();

        return ratingAverage;
    }

    // Getters N Setters

    public int getId() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<String> getGenres() {
        return genres;
    }

    public LinkedList<Float> getRatingList() {
        return ratingList;
    }

    public float getRatingAverage() {
        return ratingAverage;
    }
}
