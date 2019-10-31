package com.point.common.biz;

import com.point.common.consts.Constant;
import com.point.common.database.accessor.NoSqlAccessor;
import com.point.common.database.domain.WhsBodyCap;
import com.point.common.database.domain.WhsBodyComp;
import com.point.common.database.domain.WhsBodyFull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 人体相关数据检索帮助类
 */
@Component
@Slf4j
public class BodySearchHelper {
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

    /**
     * 根据ID获取人体原图数据
     *
     * @param id ID
     * @return 人体原图数据
     */
    public WhsBodyFull getBodyFullDat(String id) {

        String sql = "select * from WHS_BODY_FULL where ID = ?";
        return noSqlAccessor.queryForObject(sql, WhsBodyFull.class, id);
    }

    /**
     * 根据数据ID获取人体数据
     *
     * @param id ID
     * @return 人体数据
     */
    public WhsBodyCap getBodyCapDat(String id) {

        String sql = "select * from WHS_BODY_CAP where ID = ?";
        return noSqlAccessor.queryForObject(sql, WhsBodyCap.class, id);
    }

    /**
     * 根据数据ID获取人体对比结果数据
     *
     * @param id ID
     * @return 人体数据
     */
    public List<WhsBodyComp> getBodyCompRsltList(String id) {

        String compIdWithDb = StringUtils.left(id, Constant.DataIdLength.COMPARE_WITH_DB);
        String idMin = StringUtils.rightPad(compIdWithDb, Constant.DataIdLength.BODY_COMPARE, "0");
        String idMax = StringUtils.rightPad(compIdWithDb, Constant.DataIdLength.BODY_COMPARE, "9");
        String sql = "select * from WHS_BODY_COMP where ID >= ? and ID <= ?";
        return noSqlAccessor.query(sql, WhsBodyComp.class, idMin, idMax);
    }

}
