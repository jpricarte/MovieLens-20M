package structs.GenreTrie;

import structs.hashTable.HashTable;
import structs.hashTable.MovieNode;

import java.io.Serializable;
import java.util.LinkedList;

public class GenreNode{
    private char c;
    private LinkedList<GenreNode> children;
    private boolean isLeaf;
    private LinkedList<Integer> movieIdList;

    public GenreNode() { // Construtor da raiz
        this.children = new LinkedList<GenreNode>();
        this.isLeaf = false;
        this.movieIdList = null;
    }

    public GenreNode(char c) {
        this.children = new LinkedList<GenreNode>();
        this.isLeaf = false;
        this.movieIdList = null;
        this.c = c;
    }


    public int findIndex(char c) {
        for (GenreNode m: children) {
            if(c == m.getC()) {
                return this.getChildren().indexOf(m);
            }
        }
        return -1;
    }

    public int addChild(char c) {
        int i;
        for (i=0; i<children.size(); i++) {
            if(children.get(i).c > c && i != 0) {
                children.add(i-1, new GenreNode(c));
                break;
            } else if (children.get(i).c > c) {
                children.add(i, new GenreNode(c));
                break;
            }
        }
        if (i == children.size()) children.addLast(new GenreNode(c));

        return i;
    }

    public GenreNode findChild(char c) {
        int index = this.findIndex(c);
        if(index < 0) {
            return null;
        } else {
            return this.children.get(index);
        }
    }

    public GenreNode findOrCreateChild(char c) {
        int index = this.findIndex(c);
        if(index < 0) {
            index = this.addChild(c);
        }

        return this.children.get(index);
    }

    public LinkedList<Integer> getSubTree() {
        LinkedList<Integer> l = new LinkedList<Integer>();
        if (this.isLeaf()) {
//            l.add(this.getMovieIds());
        }

        for (GenreNode m : this.children) {
            l.addAll(m.getSubTree());
        }

        return l;
    }

    // Return true if movie does not exists yet
    public boolean addMovieIdIfNotExists(int movieId, HashTable<MovieNode> movieHashTable) {
        if (movieIdList == null) {
            movieIdList = new LinkedList<Integer>();
            movieIdList.addFirst(movieId);
            return true;
        }

        float newMovieAverage = movieHashTable.find(movieId).getRatingAverage();

        for (int i=0; i < movieIdList.size(); i++) {
            if (movieIdList.get(i) == movieId) {
                return false;
            }
            float actualMovieAverage = movieHashTable.find(movieIdList.get(i)).getRatingAverage();

            if (Float.compare(newMovieAverage, actualMovieAverage) >= 0) {
                movieIdList.add(i, movieId);
                return true;
            }
        }
        movieIdList.addLast(movieId);
        return true;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public LinkedList<GenreNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public LinkedList<Integer> getMovieIdList() {
        return movieIdList;
    }

    public void setMovieIdList(LinkedList<Integer> movieIdList) {
        this.movieIdList = movieIdList;
    }

}
