package structs.MovieTrie;

import java.io.Serializable;
import java.util.LinkedList;

public class MovieNode implements Serializable {
    private char c;
    private LinkedList<MovieNode> children;
    private boolean isLeaf;
    private int movieId;

    public MovieNode() {
        this.children = new LinkedList<MovieNode>();
        this.isLeaf = false;
        movieId = -1;
    }

    public MovieNode(char c) {
        this.children = new LinkedList<MovieNode>();
        this.isLeaf = false;
        this.movieId = -1;
        this.c = c;
    }

    public MovieNode(int movieId) {
        this.movieId = movieId;
    }

    public int findIndex(char c) {
        for (MovieNode m: this.getChildren()) {
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
                children.add(i-1, new MovieNode(c));
                break;
            } else if (children.get(i).c > c) {
                children.add(i, new MovieNode(c));
                break;
            }
        }
        if (i == children.size()) children.addLast(new MovieNode(c));

        return i;
    }

    public MovieNode findChild(char c) {
        int index = this.findIndex(c);
        if(index < 0) {
            return null;
        } else {
            return this.children.get(index);
        }
    }

    public MovieNode findOrCreateChild(char c) {
        int index = this.findIndex(c);
        if(index < 0) {
            index = this.addChild(c);
        }

        return this.children.get(index);
    }

    public LinkedList<Integer> getSubTree() {
        LinkedList<Integer> l = new LinkedList<Integer>();
        if (this.isLeaf()) {
            l.add(this.getMovieId());
        }

        for (MovieNode m : this.children) {
            l.addAll(m.getSubTree());
        }

        return l;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public LinkedList<MovieNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
