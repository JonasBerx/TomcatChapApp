package domain;

public class Comment {
    private String name;
    private String comment;
    private int rating;

    public Comment(String name, String comment, int rating) {
        setComment(comment);
        setName(name);
        setRating(rating);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
