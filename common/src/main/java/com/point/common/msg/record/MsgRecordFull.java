package com.point.common.msg.record;

import com.point.common.data.ObjectPositionInfo;
import lombok.Data;

import java.util.List;

/**
 * 抓拍原图结果
 */
@Data
public class MsgRecordFull extends MsgRecordDepotCommon {

    private String imgId;
    private List<ObjectPositionInfo> objPos;
}
