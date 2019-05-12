package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Places {
    @Expose
    @SerializedName("id")
    private Integer Id;
    @Expose
    private String type;
    @Expose
    private String name;
    @Expose
    private String address;
    @Expose
    private String email;
    @Expose
    private String description;
    @Expose
    private String telephone;
    @Expose
    private String image;
    @Expose
    @SerializedName("avgRating")
    private Integer rating;

    public Places(String type, String name, String address, String email, String description, String telephone, String image) {
        this.type = type;
        this.name = name;
        this.address = address;
        this.email = email;
        this.description = description;
        this.telephone = telephone;
        this.image = image;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
