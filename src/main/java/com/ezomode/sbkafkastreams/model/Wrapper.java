package com.ezomode.sbkafkastreams.model;

import java.util.Date;

public class Wrapper {

    private String payload;
    private Date created;

    public Wrapper() {
    }

    public Wrapper(String payload, Date created) {
        this.payload = payload;
        this.created = created;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }


}