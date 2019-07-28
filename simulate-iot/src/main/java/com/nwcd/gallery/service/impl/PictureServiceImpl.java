package com.nwcd.gallery.service.impl;

import com.nwcd.gallery.domain.Picture;
import com.nwcd.gallery.repository.PictureJpaRepository;
import com.nwcd.gallery.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PictureServiceImpl implements PictureService {




    private final int MAX_PICTURE_COUNT = 30;

    @Autowired
    PictureJpaRepository pictureJpaRepository;


    @Override
    public List<Picture> findAll() {

        List<Picture> list = pictureJpaRepository.findAll();
        if(list != null && list.size() > MAX_PICTURE_COUNT){
            list = list.subList( 0, MAX_PICTURE_COUNT-1);
        }

        return list ;
    }

    @Override
    public Picture insertByPicture(Picture picture) {

        pictureJpaRepository.save( picture );
        return picture;
    }

    @Override
    public Picture update(Picture picture) {
        pictureJpaRepository.saveAndFlush( picture );
        return null;
    }

    @Override
    public Picture delete(Integer id) {

        pictureJpaRepository.deleteById( id );
        return null;
    }

    @Override
    public Picture findById(Integer id) {

        Optional<Picture> optional =  pictureJpaRepository.findById( id );
        if(optional.isPresent()){
            return optional.get();
        }else {
            return null;
        }


    }
}
