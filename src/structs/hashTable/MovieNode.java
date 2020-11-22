package structs.hashTable;


import csvReader.structs.Rating;

import java.util.List;

public class MovieNode implements HashObject {
    private int id;
    private String movieName;
    private List<String> genres;
    private int numOfReviews;
    private float ratingAverage;

    public MovieNode(int movieId, String movieName, List<String> genres) {
        this.id = movieId;
        this.movieName = movieName;
        this.genres = genres;
        this.numOfReviews = 0;
    }

    public void newRating(float rating) {
        ratingAverage = (numOfReviews*ratingAverage + rating) / ++numOfReviews;
    }

    public double updateRatingAverage() {
//        float newAverage = 0;
//        for (var rating : this.ratingList) {
//            newAverage += rating;
//        }
//        this.ratingAverage = newAverage / this.ratingList.size();

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

    public int getNumOfReviews() {
        return numOfReviews;
    }

    public void setNumOfReviews(int numOfReviews) {
        this.numOfReviews = numOfReviews;
    }

    public float getRatingAverage() {
        return ratingAverage;
    }
}
