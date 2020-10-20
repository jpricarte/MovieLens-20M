/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import fileStructs.Movie;
import java.util.LinkedList;

/**
 *
 * @author pujol
 */
public class Main {
    public static void main(String args[]) {
        LinkedList<Movie> movies = new LinkedList();
        movies = Parser.movieParser("movie.csv");
    }
}
