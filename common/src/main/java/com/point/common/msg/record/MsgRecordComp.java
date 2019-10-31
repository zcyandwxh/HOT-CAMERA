package com.point.common.msg.record;

import lombok.Data;

import java.util.List;

/**
 * 比对结果
 */
@Data
public class MsgRecordComp extends MsgRecordDepotCommon {

    private String compDevId;
    private String compDevDesc;

    private String capDatId;
    private String fullDatId;

    private String operId;

    private MsgRecordCap capDetail;
    private List<MsgRecordCompDetail> compDetailList;

}
