package com.point.common.biz;

import com.point.common.consts.Constant;
import com.point.common.database.accessor.NoSqlAccessor;
import com.point.common.database.domain.WhsVehCap;
import com.point.common.database.domain.WhsVehComp;
import com.point.common.database.domain.WhsVehFull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 车辆相关数据检索帮助类
 */
@Component
@Slf4j
public class VehicleSearchHelper {

    /**
     * 业务共通
     */
    @Autowired
    private BizCommon bizCommon;

    /**
     * NoSql操作工具
     */
    @Autowired
    private NoSqlAccessor noSqlAccessor;

//    /**
//     * 根据ID获取车辆原图数据
//     *
//     * @param id ID
//     * @return 车辆原图数据
//     */
//    @Cacheable(value = "vehFullDat")
//    public WhsVehFull getVehFullDatWithCache(String id) {
//
//        String sql = "select * from WHS_VEH_FULL where ID = ?";
//        return noSqlAccessor.queryForObject(sql, WhsVehFull.class, id);
//    }

    /**
     * 根据ID获取车辆原图数据
     *
     * @param id ID
     * @return 车辆原图数据
     */
    public WhsVehFull getVehFullDat(String id) {

        String sql = "select * from WHS_VEH_FULL where ID = ?";
        return noSqlAccessor.queryForObject(sql, WhsVehFull.class, id);
    }

//    /**
//     * 根据数据ID获取车辆数据
//     *
//     * @param id ID
//     * @return 车辆数据
//     */
//    @Cacheable(value = "vehCapDat")
//    public WhsVehCap getVehCapDatWithCache(String id) {
//
//        String sql = "select * from WHS_VEH_CAP where ID = ?";
//        return noSqlAccessor.queryForObject(sql, WhsVehCap.class, id);
//    }

    /**
     * 根据数据ID获取车辆数据
     *
     * @param id ID
     * @return 车辆数据
     */
    public WhsVehCap getVehCapDat(String id) {

        String sql = "select * from WHS_VEH_CAP where ID = ?";
        return noSqlAccessor.queryForObject(sql, WhsVehCap.class, id);
    }

    /**
     * 根据数据ID获取车辆对比结果数据
     *
     * @param id ID
     * @return 车辆数据
     */
    public List<WhsVehComp> getVehCompRsltList(String id) {

        String compIdWithDb = StringUtils.left(id, Constant.DataIdLength.COMPARE_WITH_DB);
        String idMin = StringUtils.rightPad(compIdWithDb, Constant.DataIdLength.VEH_COMPARE, "0");
        String idMax = StringUtils.rightPad(compIdWithDb, Constant.DataIdLength.VEH_COMPARE, "9");
        String sql = "select * from WHS_VEH_COMP where ID >= ? and ID <= ?";
        return noSqlAccessor.query(sql, WhsVehComp.class, idMin, idMax);
    }

}
