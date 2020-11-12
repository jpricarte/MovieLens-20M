package structs.hashTable;

import structs.hashTable.HashObject;

import java.util.LinkedList;

public class UserNode implements HashObject {

    private int userId;
    private LinkedList<Integer> movieIdList;
    private LinkedList<Float> ratingList;

    public UserNode(int userId) {
        this.userId = userId;
        this.movieIdList = new LinkedList<Integer>();
        this.ratingList = new LinkedList<Float>();
    }

    public void addRating(int movieId, float rating) {
        movieIdList.add(movieId);
        ratingList.add(rating);
    }

    @Override
    public int getId() {
        return userId;
    }

    public LinkedList<Integer> getMovieIdList() {
        return movieIdList;
    }

    public LinkedList<Float> getRatingList() {
        return ratingList;
    }

}
