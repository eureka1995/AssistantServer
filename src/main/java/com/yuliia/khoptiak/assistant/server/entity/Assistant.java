package com.yuliia.khoptiak.assistant.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "fitness_assistant")
public class Assistant {

    @Id
    @GeneratedValue(  generator = "increment")
    @GenericGenerator( name = "increment", strategy = "increment")
    private long id;

    @Column(name = "category", nullable = false, length = 10)
    private String category;

    @Column(name = "title",  nullable = false)
    private String title;

    public Assistant() {
            }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
