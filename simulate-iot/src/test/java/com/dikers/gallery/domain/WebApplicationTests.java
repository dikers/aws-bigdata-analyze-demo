package com.dikers.gallery.domain;

import com.dikers.gallery.repository.PictureJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class WebApplicationTests {


	@Autowired
    PictureJpaRepository pictureJpaRepository;

	@Test
	public void contextLoads() {

		List<Picture>  pictureList =  pictureJpaRepository.findAll();
		log.info( "==========  {} ", pictureList.size() );

	}

}
