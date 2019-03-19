package models;

import java.util.UUID;

public class Places {
    private UUID Id;
    private String type;
    private String name;
    private String address;
    private String email;
    private String description;
    private String telephone;

    public Places(String type, String name, String address, String email, String description, String telephone) {
        this.type = type;
        this.name = name;
        this.address = address;
        this.email = email;
        this.description = description;
        this.telephone = telephone;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
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
}
