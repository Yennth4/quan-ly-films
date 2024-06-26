package com.yennth.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "Videos")
public class Video {
    @Id
    String id;
    String title;
    String poster;
    String description;
    Boolean active;

    public Video(String id, String title, String poster, String description, Boolean active) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.active = active;
    }

    public Video(String title, String poster, String description, Boolean active) {
        this.title = title;
        this.poster = poster;
        this.description = description;
        this.active = active;
    }

    public Video() {
    }

    public Video(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
