package structs.hashTable;


import csvReader.structs.Rating;

import java.util.LinkedList;
import java.util.List;

public class MovieNode implements HashObject{
    private int id;
    private String movieName;
    private List<String> genres;
    private LinkedList<Double> ratingList;
    private double ratingAverage;

    public MovieNode(int movieId, String movieName, List<String> genres) {
        this.id = movieId;
        this.movieName = movieName;
        this.genres = genres;
        this.ratingList = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void newRating(double rating) {
        ratingList.add(rating);
    }

    public double updateRatingAverage() {
        double newAverage = 0;
        for (var rating : this.ratingList) {
            newAverage += rating;
        }
        this.ratingAverage = newAverage / this.ratingList.size();

        return ratingAverage;
    }

    // Getters N Setters

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(LinkedList<String> genres) {
        this.genres = genres;
    }

    public LinkedList<Double> getRatingList() {
        return ratingList;
    }

    public void setRatingList(LinkedList<Double> ratingList) {
        this.ratingList = ratingList;
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }
}
