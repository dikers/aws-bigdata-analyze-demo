package com.nwcd.gallery.domain;

import lombok.Data;

@Data
public class PictureSize {
    public PictureSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    int width;
    int height;
}
