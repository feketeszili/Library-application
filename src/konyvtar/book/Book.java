package konyvtar.book;

import java.util.ArrayList;
import java.util.List;

public class Book implements java.io.Serializable {
    public int id;
    public String title;
    public String author;
    public String publisher;
    public int publishedDate;
    public ArrayList<String> keywords ;
    public boolean accessable;

    public Book(int id, String title, String author, String publisher, int publishedDate, ArrayList<String> keywords, boolean accessable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.keywords = keywords;
        this.accessable = accessable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublishedDate(int publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getPublishedDate() {
        return publishedDate;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setAccessable(boolean accessable) {
        this.accessable = accessable;
    }

    public boolean isAccessable() {
        return accessable;
    }
}

