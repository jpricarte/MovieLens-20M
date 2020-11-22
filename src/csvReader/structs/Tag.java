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
public class Tag {
    // "userId","movieId","tag","timestamp"
    protected int userId;
    protected int movieId;
    protected String tag;
    protected String timestamp;

    // Constructor
    public Tag(int userId, int movieId, String tag, String timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", tag='" + tag + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
