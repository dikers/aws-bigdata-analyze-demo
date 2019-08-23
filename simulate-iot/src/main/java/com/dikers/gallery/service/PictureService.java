package com.dikers.gallery.service;


import com.dikers.gallery.domain.Picture;

import java.util.List;

/**
 * Picture 业务接口层
 *
 * Created by bysocket on 30/09/2017.
 */
public interface PictureService {
    /**
     * 获取所有 Book
     */
    List<Picture> findAll();

    /**
     *
     * @param picture {@link Picture}
     */
    Picture insertByPicture(Picture picture);

    /**
     * 更新 Picture
     *
     * @param picture {@link Picture}
     */
    Picture update(Picture picture);

    /**
     * 删除 Picture
     *
     * @param id 编号
     */
    Picture delete(Integer id);

    /**
     * 获取 Picture
     *
     * @param id 编号
     */
    Picture findById(Integer id);
}
