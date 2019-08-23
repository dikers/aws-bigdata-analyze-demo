package com.dikers.gallery.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import software.amazon.awssdk.regions.Region;

/**
 * Book 控制层
 * <p>
 * Created by bysocket on 30/09/2017.
 */
@Controller
@Slf4j
@RequestMapping(value = "/")
public class IndexController {


    Region REGION = Region.US_EAST_1;

    private static final String INDEX_PATH_NAME = "index";

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap map) {

        return INDEX_PATH_NAME;
    }


}
