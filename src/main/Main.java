/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import csvReader.reader.Parser;
import csvReader.structs.Movie;
import csvReader.structs.Rating;
import csvReader.structs.Tag;

import java.util.LinkedList;

/**
 *
 * @author guibgoulart, jpricarte
 */

public class Main {
    public static void main(String args[]) {
        LinkedList<Movie> movies = Parser.movieParser("movie.csv");
        System.out.println(movies.size());

        LinkedList<Rating> ratings = Parser.ratingParser("minirating.csv");
        System.out.println(ratings.size());

        LinkedList<Tag> tags = Parser.tagParser("tag.csv");
        System.out.println(tags.size());
    }
}
