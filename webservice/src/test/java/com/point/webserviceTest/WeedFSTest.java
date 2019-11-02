package com.point.webserviceTest;

import com.point.webservice.WebServiceApplication;
import com.point.webservice.facade.WeedFSService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by huixing on 2019/11/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebServiceApplication.class)
public class WeedFSTest {

    @Autowired
    WeedFSService weedFSService;

    @Test
    public void testWeedFS() throws FileNotFoundException {
        weedFSService.init();
        FileInputStream fileInputStream = new FileInputStream("/Users/huixing/Pictures/2.png");
        String url = weedFSService.storagePic(fileInputStream);
        System.out.println(url);
    }


}
