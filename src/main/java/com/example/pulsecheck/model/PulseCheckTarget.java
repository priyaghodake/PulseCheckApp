package com.example.pulsecheck.model;

import jakarta.persistence.*;

@Entity
public class PulseCheckTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String email;
    private String name;
    private int freqInMins;

    public PulseCheckTarget() {
    }

    public PulseCheckTarget(Long id, String url, String email, String name, Integer freqInMins) {
        this.id = id;
        this.url = url;
        this.email = email;
        this.name = name;
        this.freqInMins = freqInMins;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Integer getFreqInMins() {
        return freqInMins;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFreqInMins(Integer freqInMins) {
        this.freqInMins = freqInMins;
    }
}