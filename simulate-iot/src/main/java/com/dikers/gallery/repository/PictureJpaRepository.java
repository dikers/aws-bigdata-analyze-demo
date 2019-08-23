package com.dikers.gallery.repository;

import com.dikers.gallery.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureJpaRepository  extends JpaRepository<Picture, Integer> {

}
