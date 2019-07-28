package com.nwcd.gallery.repository;

import com.nwcd.gallery.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureJpaRepository  extends JpaRepository<Picture, Integer> {

}
