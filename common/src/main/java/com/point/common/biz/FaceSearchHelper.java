package com.point.common.biz;

import com.point.common.database.accessor.NoSqlAccessor;
import com.point.common.database.domain.WhsFaceCap;
import com.point.common.database.domain.WhsFaceFull;
import com.point.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 人脸相关数据检索帮助类
 */
@Component
@Slf4j
public class FaceSearchHelper {

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
//     * 根据ID获取原图数据
//     *
//     * @param id ID
//     * @return 原图数据
//     */
//    @Cacheable(value = "faceFullDat")
//    public WhsFaceFull getFaceFullDatWithCache(String id) {
//
//        String sql = "select * from WHS_FACE_FULL where ID = ?";
//        return noSqlAccessor.queryForObject(sql, WhsFaceFull.class, id);
//    }

    /**
     * 根据ID获取原图数据
     *
     * @param id ID
     * @return 原图数据
     */
    public WhsFaceFull getFaceFullDat(String id) {

        String sql = "select * from WHS_FACE_FULL where ID = ?";
        return noSqlAccessor.queryForObject(sql, WhsFaceFull.class, id);
    }

//    /**
//     * 根据数据ID获取人脸数据
//     *
//     * @param id ID
//     * @return 人脸数据
//     */
//    @Cacheable(value = "faceDat")
//    public WhsFaceCap getFaceCapDatWithCache(String id) {
//
//        String sql = "select * from WHS_FACE_CAP where ID = ?";
//        return noSqlAccessor.queryForObject(sql, WhsFaceCap.class, id);
//    }

    /**
     * 根据数据ID获取人脸数据
     *
     * @param id ID
     * @return 人脸数据
     */
    public WhsFaceCap getFaceCapDat(String id) {

        String sql = "select * from WHS_FACE_CAP where ID = ?";
        return noSqlAccessor.queryForObject(sql, WhsFaceCap.class, id);
    }

    /**
     * 根据路人库信息获取人脸数据
     *
     * @param searchSvr 搜索服务器
     * @param searchDb  搜索底库
     * @param objId     搜索对象ID
     * @return 人脸数据
     */
    public WhsFaceCap getFaceCapDat(String searchSvr, String searchDb, String objId) {

        String sql = "select * from WHS_FACE_CAP where SEARCH_SVR = ? and SEARCH_DB = ? and SEARCH_OBJ_ID = ? ";
        return noSqlAccessor.queryForObject(sql, WhsFaceCap.class, searchSvr, searchDb, objId);
    }

    /**
     * 获取最旧的路人库信息
     *
     * @param countLimit 搜索件数
     * @return 人脸数据
     */
    public List<WhsFaceCap> getOldestFaceCapDatWithSearch(long countLimit) {

        String defaultStart = "20180101000000000";
        String sql = String.format("select ID, CAPTURE_TIME, SEARCH_SVR, SEARCH_DB, SEARCH_OBJ_ID from WHS_FACE_CAP where ID between '%s' and '%s' and SEARCH_SVR IS NOT NULL order by ID asc limit ? ",
                defaultStart,  DateUtil.getCurrentTimeDefaultFormat());
        return noSqlAccessor.query(sql, WhsFaceCap.class, countLimit);
    }

}
