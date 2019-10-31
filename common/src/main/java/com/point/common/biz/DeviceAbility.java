package com.point.common.biz;

/**
 * 设备能力管理工具类
 */
public class DeviceAbility {

    /**
     * 人脸抓拍能力
     */
    public static final int ABILITY_CAP_FACE = 1;

    /**
     * 车辆抓拍能力
     */
    public static final int ABILITY_CAP_VEH = 2;

    /**
     * 人脸结构化能力
     */
    public static final int ABILITY_RECOG_FACE = 4;

    /**
     * 车辆结构化能力
     */
    public static final int ABILITY_RECOG_VEH = 8;

    /**
     * 人脸比对能力
     */
    public static final int ABILITY_COMP_FACE = 16;

    /**
     * 车辆比对能力
     */
    public static final int ABILITY_COMP_VEH = 32;

    /**
     * 人体抓拍能力
     */
    public static final int ABILITY_CAP_BODY = 64;

    /**
     * 人体结构化比对能力
     */
    public static final int ABILITY_RECOG_COMP_BODY = 128;

    /**
     * 人体结构化能力
     */
    public static final int ABILITY_RECOG_BODY = 256;

    /**
     * 车辆结构化比对能力
     */
    public static final int ABILITY_RECOG_COMP_VEH = 512;

    /**
     * 能力值
     */
    private Integer ability;

    /**
     * 构造函数
     */
    public DeviceAbility() {
        this(0);
    }

    /**
     * 构造函数
     * @param ability 能力值
     */
    public DeviceAbility(Integer ability) {
        if (ability == null) {
            this.ability = 0;
        } else {
            this.ability = ability;
        }
    }

    /**
     * 添加能力
     *
     * @param ability 能力值
     */
    public void addAbility(Integer ability) {
        this.ability |= ability;
    }

    /**
     * 获取能力值
     *
     * @return 能力值
     */
    public int getAbility() {
        return ability;
    }

    /**
     * 是否拥有指定的能力
     * @param ability 能力
     * @return 是否拥有
     */
    public boolean hasAbility(Integer ability) {
        return (this.ability & ability) == ability;
    }
}
