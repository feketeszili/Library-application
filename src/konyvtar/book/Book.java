package konyvtar.book;

import java.util.ArrayList;

public class Book implements java.io.Serializable {
    public int ID;
    public String title;
    public String author;
    public String publisher;
    public int publishedDate;
    public ArrayList<String> keywords ;
    public boolean accessable;

    public Book(int ID, String title, String author, String publisher, int publishedDate, ArrayList<String> keywords, boolean accessable) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.keywords = keywords;
        this.accessable = accessable;
    }

    public Book() {

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
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

