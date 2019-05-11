package models;

import com.google.gson.annotations.Expose;

public class Rating {

    @Expose
    private Integer place_id;
    @Expose
    private Integer user_id;
    @Expose
    private Integer rating;

    public Rating(Integer place_id, Integer user_id, Integer rating) {
        this.place_id = place_id;
        this.user_id = user_id;
        this.rating = rating;
    }

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}