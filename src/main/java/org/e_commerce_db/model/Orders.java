package org.e_commerce_db.model;

import javax.xml.crypto.Data;
import java.util.Date;

public class Orders {
    private int id;
    private int userId;
    private int status;
    private Date date;

    public Orders(int id, int userId, int status, Date date) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
