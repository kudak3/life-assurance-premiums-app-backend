package com.ellachihwa.lapa.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date created;

    private String affectedTable;

    @Column(columnDefinition = "boolean default false")
    private boolean viewed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAffectedTable() {
        return affectedTable;
    }

    public void setAffectedTable(String affectedTable) {
        this.affectedTable = affectedTable;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
}
