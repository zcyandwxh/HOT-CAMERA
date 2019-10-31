package com.point.accept.bean;

import lombok.Data;

/**
 * @author huixing
 * @description 接受摄像机传过来的数据
 * @date 2019/10/29
 */
@Data
public class AcceptBean {

    private String device_id;
    private int Facelibrary;
    private String CompareScore;
    private String SnapTime;
    private String FacePicData;
}
