package com.nwcd.gallery.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author dikers
 */
@Data
@Entity
@Table(name = "picture")
public class Picture implements Serializable {


    public Picture(){

    }

    public Picture(Integer id, String url, String thumbnail, String title) {
        this.id = id;
        this.url = url;
        this.thumbnail = thumbnail;
        this.title = title;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url")
    private String url;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "title")
    private String title;


}
