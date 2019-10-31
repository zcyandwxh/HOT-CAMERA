package com.point.common.consts;

/**
 * 常量定义
 */
public class Constant {

    /**
     * 前端设备类型
     */
    public static class FrontDeviceType {

        public static final int RTSP = 1;
        public static final int IPC = 2;
        public static final int FILE = 3;
        public static final int DIRECTORY = 4;
    }

    /**
     * 设备是否为是智能设备
     */
    public static class DevSmart {

        public static final int YES = 1;
        public static final int NO = 2;
    }

    /**
     * 设备是否为是32位设备
     */
    public static class Dev32Bit {

        public static final int YES = 1;
        public static final int NO = 2;
    }

    /**
     * 消息数据类型
     */
    public static class DataType {
        public static final int NOTHING = 0;

        public static final int BODY = 1;
        public static final int FACE = 2;
        public static final int VEH = 3;
        public static final int OBJECT = 4;
        public static final int BEHAVIOR = 5;

        public static final int FACE_FULL = 20;
        public static final int FACE_RECOG = 21;
        public static final int FACE_COMPARE = 22;

        public static final int VEH_FULL = 30;
        public static final int VEH_RECOG = 31;
        public static final int VEH_COMPARE = 32;

        public static final int BODY_FULL = 20;
        public static final int BODY_RECOG = 21;
        public static final int BODY_COMPARE = 22;

        public static final int TASK = 60;

        public static final int DEV_STS = 71;
        public static final int TASK_STS = 72;
        public static final int JOB_STS = 73;

        public static final int FACE_POSITION = 9000;
        public static final int ENDING = 9999;
    }

    /**
     * 数据来源类型
     */
    public static class DataSrcType {
        public static final int AUTO_CAPTURE = 1;
        public static final int MANUAL_SUBMIT = 2;
    }


    /**
     * 设备状态
     */
    public static class DeviceStatus {

        public static final int NONE = -1;
        public static final int UNKOWN = 0;
        public static final int ONLINE = 1;
        public static final int OFFLINE = 2;
        public static final int ERROR = 9;

    }

    /**
     * 设备类型
     */
    public static class DeviceType {

        public static final int FRONT = 1;
        public static final int PLATFORM = 2;
        public static final int ANLSVR = 3;
        public static final int MEDIASVR = 4;
        public static final int TRANSSVR = 5;

    }

    /**
     * 数据筛选动作类型
     */
    public static class DataFilterActionType {
        public static final int ACTION_DOWNLOAD = 1;
        public static final int ACTION_FILTER = 2;
    }

    /**
     * 采集任务类型
     */
    public static class JobType {
        // 人脸
        public static final int FACE = 1;
        // 车辆
        public static final int VEHICLE = 2;
        // 人脸车辆
        public static final int FACE_AND_VEHICLE = 3;
        // 人体
        public static final int BODY = 4;
        // 人脸人体
        public static final int FACE_AND_BODY = 5;
        // 人体车辆
        public static final int BODY_AND_VEHICLE = 6;
        // 人脸人体车辆
        public static final int PERSON_AND_VEHICLE = 7;
    }

    /**
     * 任务类型
     */
    public static class TaskType {
        public static final int TASK_VIDEO_DOWNLOAD_AUTO = 1;
        public static final int TASK_VIDEO_DOWNLOAD_MANUAL = 2;
        public static final int TASK_SEARCH_FACE_BY_IMAGE = 3;
        public static final int TASK_SEARCH_VEHICLE_BY_IMAGE = 4;
        public static final int TASK_VIDEO_TRANS_MANUAL = 5;
        public static final int TASK_VIDEO_UPLOAD_MANUAL = 6;
    }

    /**
     * API 返回结果状态代码
     */
    public static class ApiReturnCode {

        public static final int OK = 0;
        public static final int ERROR_API_NOT_FOUND = 1;
        public static final int ERROR_PARAM_WRONG = 2;
        public static final int ERROR_API_ERROR = 3;
        public static final int ERROR_TARGET_DATA_NOT_FOUND = 4;
        public static final int ERROR_DATA_NOT_FOUND = 100;
        public static final int ERROR_SYSTEM = 999;
    }

    /**
     * 存储结果返回代码
     */
    public static class StorageReturnCode {

        public static final int OK = 0;
        public static final int ERROR_SERVICE_NOT_FOUND = 1;
        public static final int ERROR_PARAM_WRONG = 2;
        public static final int ERROR_SERVICE_ERROR = 3;
        public static final int ERROR_RESOURCE_NOT_FOUND = 4;
        public static final int ERROR_SYSTEM = 999;
    }

    /**
     * 数据ID长度
     */
    public static class DataIdLength {
        public static final int FACE_FULL = 34;
        public static final int FACE = 36;
        public static final int FACE_COMPARE = 52;
        public static final int VEH_FULL = 34;
        public static final int VEH = 36;
        public static final int VEH_COMPARE = 52;
        public static final int BODY_FULL = 34;
        public static final int BODY = 36;
        public static final int BODY_COMPARE = 52;
        public static final int COMPARE_WITH_DB = 46;
        public static final int FILE = 43;
    }

    /**
     * 系统配置
     */
    public static class SysConfig {

        public static final String SERVICE_INQUIRY_DEFAULT_TIME_BEFORE = "ServiceInquiryDefaultTimeBefore";
        public static final String SERVICE_INQUIRY_DEFAULT_LIMIT = "ServiceInquiryDefaultLimit";
        public static final String COMPARE_RESULT_TOP_COUNT = "CompareResultTopCount";
        public static final String COMPARE_RESULT_MIN_SCORE = "CompareResultMinScore";
        public static final String COMPARE_RESULT_ONLY_TOP_SCORE = "CompareResultOnlyTopScore";
        public static final String COMPARE_RESULT_DO_REPORT = "CompareResultDoReport";
        public static final String COMPARE_RESULT_DO_REPORT_WHEN_NOT_MATCHED = "CompareResultDoReportWhenNotMatched";

        public static final String CAPTURE_RESULT_MIN_SCORE = "CaptureResultMinScore";
        public static final String COMPARE_RESULT_NO_MATCH_VEHICLE_SCORE = "CompareResultNoMatchVehicleScore";
        public static final String COMPARE_USE_EXTERNAL_IMAGE_ID = "CompareUseExternalImageId";
        public static final String JOB_STATUS_CHECK_INTERVAL = "JobStatusCheckInterval";
        public static final String JOB_CAPTURE_ACTIVE_QUERY_INTERVAL = "JobCaptureActiveQueryInterval";
        public static final String JOB_CAPTURE_ACTIVE_QUERY_COUNT = "JobCaptureActiveQueryCount";
        public static final String VIDEO_DOWNLOAD_TIME_RANGE = "VideoDownloadTimeRange";
        public static final String VIDEO_DOWNLOAD_TIME_DELAY = "VideoDownloadTimeDelay";
        public static final String VIDEO_DOWNLOAD_TIME_PADDING = "VideoDownloadTimePadding";
        public static final String VIDEO_DOWNLOAD_MIN_SCORE = "VideoDownloadMinScore";
        public static final String TASK_THREAD_COUNT = "TaskThreadCount";
        public static final String PREPROCESS_THREAD_COUNT = "PreprocessThreadCount";
        public static final String PROCESS_THREAD_COUNT = "ProcessThreadCount";
        public static final String DATA_STATISTICS_UPDATE_INTERVAL = "DataStatisticsUpdateInterval";

        public static final String TARGET_DATABASE_DEFAULT_NAME = "TargetDatabaseDefaultName";
        public static final String SEARCH_DATABASE_MIN_SCORE = "SearchDatabaseMinScore";
        public static final String SEARCH_FACE_MODE = "SearchFaceMode";
        public static final String SEARCH_FACE_IGNORE_CAPTURE_SERVER = "SearchFaceIgnoreCaptureServer";
        public static final String SEARCH_FACE_IGNORE_COMPARE_SERVER = "SearchFaceIgnoreCompareServer";
        public static final String SEARCH_FACE_DATABASE_CALCULATE_INTERVAL = "SearchFaceDatabaseCalculateInterval";
        public static final String SEARCH_FACE_DATABASE_COUNT_PER_SERVER = "SearchFaceDatabaseCountPerServer";
        public static final String SEARCH_FACE_DATABASE_FACE_MAX_COUNT = "SearchFaceDatabaseFaceMaxCount";
        public static final String SEARCH_FACE_DATABASE_REMOVE_COUNT = "SearchFaceDatabaseRemoveCount";
        public static final String SEARCH_FACE_TEMPORARY_DATABASE_REMOVE_COUNT = "SearchFaceTemporaryDatabase";

        public static final String TASK_STATUS_REPORT_INTERVAL = "TaskStatusReportInterval";
        public static final String TASK_FACE_SEARCH_SPEED = "TaskFaceSearchSpeed";
        public static final String TASK_VEHICLE_SEARCH_SPEED = "TaskVehicleSearchSpeed";
        public static final String TASK_RETRY_INTERVAL = "TaskRetryInterval";
        public static final String ENCODING_OS_DEFAULT = "EncodingOsDefault";
        public static final String CACHE_NAME = "*CacheName*";

        public static final String YITU_VEHICLE_DATABASE_CONFIG = "YituVehicleDatabaseConfig";
        public static final String YITU_VEHICLE_ANNO_JSON = "YituVehicleAnnoJson";
        public static final String YITU_FACE_QUERY_TIME_PADDING = "YituFaceQueryTimePadding";

        public static final String TARGET_FACE_EXIST_REPOSITORY_NAME = "TargetFaceExistRepositoryName";
//        public static final String TARGET_FACE_LOCAL_REPOSITORY_PATH = "TargetFaceLocalRepositoryPath";

        public static final String HIK_VIDEO_DOWNLOAD_TIME = "HikVideoDownloadTime";
        public static final String DAHUA_VIDEO_DOWNLOAD_TIME = "DahuaVideoDownloadTime";

        public static final String ST_CAPTURE_IMAGE_MODE = "StCaptureImageMode";
        public static final String RMS_UPLOAD_WAIT_TIMEOUT = "RmsUploadWaitTimeout";

        public static final String OPEN_STRUCTURE_MODE = "OpenStructureMode";

        public static final String SYSTEM_STYLE = "SystemStyle";

        // 旷视人像以图搜图阈值
        public static final String MEGVII_THRESHOLD = "MegviiThreshold";
    }

    /**
     * 系统存储key
     */
    public static class TemporaryStorageKey {
        public static final String PASSER_DATABASE_LAST_REMOVE_ID = "PasserDatabaseLastRemoveId";
    }

    /**
     * 任务频度
     */
    public static class JobFrequency {
        public static final int ONE_TIME = 9;
        public static final int EVERY_DAY = 0;
        public static final int EVERY_WEEK = 1;
        public static final int EVERY_MONTH = 2;
    }

    /**
     * 转码任务状态
     */
    public static class MediaCoderTaskStatus {
        public static final int TASK_CREATED = 0;
        public static final int FILE_UPLOADED = 2;
        public static final int TRANS_ING = 4;
        public static final int TRANS_END = 6;
        public static final int DOWNLOADED = 8;
        public static final int REMOVED = 900;
        public static final int ERROR = 999;
    }

    /**
     * 文件后缀
     */
    public static class FileExtension {
        public static final String JPG = "jpg";
    }

    /**
     * 区域分组ID
     */
    public static class AreaGroup {
        public static final String DEFAULT_ROOT = "0000000000";
    }

    /**
     * 文件类型
     */
    public static class FileType {
        public static final int VIDEO = 1;
        public static final int IMAGE = 2;
        public static final int WORD = 3;
        public static final int EXCEL = 4;
        public static final int TEXT = 5;
        public static final int OTHER = 9;
    }

    /**
     * 文件上传类型
     */
    public static class FileUploadType {
        public static final int CAPTURE = 1;
        public static final int TARGET = 2;
        public static final int OTHER = 9;
    }

    /**
     * 排序
     */
    public static class Order {
        public static final String ASC = "1";
        public static final String DESC = "2";
    }

    /**
     * 以图搜图模式
     */
    public static class SearchFaceMode {
        public static final String IMAGE = "image";
        public static final String ENGINE = "engine";
        public static final String FEATURE = "feature";
    }

    /**
     * 消息代码
     */
    public static class MessageCode {
        // 未知错误
        public static final String UNKNOWN = "1000000";

        // -------------WebService错误代码定义----开始----------------------------------
        // API未找到
        public static final String API_NOT_FOUND = "1001001";
        // 服务内部错误
        public static final String SERVICE_INNER_ERROR = "1001002";
        // 服务参数错误
        public static final String SERVICE_PARAM_ERROR = "1001003";
        // 必须输入项目为空
        public static final String MUST_INPUT = "1001101";

        // 必须输入项目为空
        public static final String CONTENT_BIG = "上传文件过大，请重新上传";
        // 指定项目不存在
        public static final String NOT_EXSITS = "1001102";
        // 指定项目已经存在
        public static final String HAS_EXSITED = "1001103";
        // 指定项目不是数字
        public static final String NOT_NUMBERIC = "1001104";
        // 指定项目格式不正确
        public static final String WRONG_FORMAT = "1001105";
        // 指定设备使用中
        public static final String DEVIE_IN_USE = "1001201";
        // -------------WebService错误代码定义-----结束---------------------------------

        // -------------视频采集任务错误代码定义----开始----------------------------------
        // 任务系统异常
        public static final String JOB_SYS_ERROR = "1002001";
        // 任务启动中
        public static final String JOB_STARTING = "1002002";
        // 任务运行中
        public static final String JOB_ONLINE = "1002003";
        // 任务离线
        public static final String JOB_OFFLINE = "1002004";
        // 任务不存在
        public static final String JOB_NOT_FOUND = "1002005";
        // 任务未启用
        public static final String JOB_NOT_ENABLED = "1002006";
        // 任务不在工作时间
        public static final String JOB_NOT_IN_TIME = "1002007";
        // 前端设备连接失败
        public static final String JOB_FRT_CONN_FAIL = "1002101";
        // 抓拍设备连接失败
        public static final String JOB_CAP_CONN_FAIL = "1002102";
        // 抓拍设备适配程序配置不正确
        public static final String JOB_CAP_PROG_WRONG = "1002103";
        // 布控失败
        public static final String JOB_SUR_FAIL = "1002104";
        // -------------视频采集任务错误代码定义-----结束---------------------------------
    }

    /**
     * 缓存操作
     */
    public static class CacheAction {
        public static final String CLEAR = "clear";
    }

    /**
     * 数据表
     */
    public static class DataTable {
        // 文件输入源基础信息
        public static final String MST_INPUT = "MST_INPUT";
        // 前端设备基础信息
        public static final String MST_FRONT = "MST_FRONT";
        // 视频平台基础信息
        public static final String MST_PLATFORM = "MST_PLATFORM";
        // 分析服务器基础信息
        public static final String MST_ANL_SVR = "MST_ANL_SVR";
        // 流媒体服务器基础信息
        public static final String MST_RMS_SVR = "MST_RMS_SVR";
        // 转码服务器基础信息
        public static final String MST_MC_SVR = "MST_MC_SVR";
        // 程序基础信息
        public static final String MST_PROG = "MST_PROG";
        // 设备适配程序信息
        public static final String MST_DEV_PROG = "MST_DEV_PROG";
        // 数据类型
        public static final String MST_META_DAT_TYPE = "MST_META_DAT_TYPE";
        // 数据属性
        public static final String MST_META_DAT_ATTR = "MST_META_DAT_ATTR";
        // 数据字典
        public static final String MST_META_DIC = "MST_META_DIC";
        // 数据字典属性
        public static final String MST_META_DIC_ATTR = "MST_META_DIC_ATTR";
        // 数据字典代码
        public static final String MST_META_DIC_CODE = "MST_META_DIC_CODE";
        // 数据属性转换方案
        public static final String MST_ATTR_TRANS_PLAN = "MST_ATTR_TRANS_PLAN";
        // 数据属性转换配置
        public static final String MST_ATTR_TRANS_CONF = "MST_ATTR_TRANS_CONF";
        // 图片结构化方案
        public static final String MST_RECOG_PLAN = "MST_RECOG_PLAN";
        // 图片结构化配置
        public static final String MST_RECOG_CONF = "MST_RECOG_CONF";
        // 图片比对方案
        public static final String MST_COMP_PLAN = "MST_COMP_PLAN";
        // 图片比对配置
        public static final String MST_COMP_CONF = "MST_COMP_CONF";
        // 系统参数配置
        public static final String MST_SYS_PARAM_CONF = "MST_SYS_PARAM_CONF";
        // 人脸比对底库
        public static final String MST_FACE_TARGET_DB = "MST_FACE_TARGET_DB";
        // 人脸比对底库对象
        public static final String MST_FACE_TARGET_DB_OBJ = "MST_FACE_TARGET_DB_OBJ";
        // 人脸目标
        public static final String MST_FACE_TARGET = "MST_FACE_TARGET";
        // 人脸目标对象
        public static final String MST_FACE_TARGET_OBJ = "MST_FACE_TARGET_OBJ";
        // 车辆比对底库
        public static final String MST_VEH_TARGET_DB = "MST_VEH_TARGET_DB";
        // 车辆比对底库对象
        public static final String MST_VEH_TARGET_DB_OBJ = "MST_VEH_TARGET_DB_OBJ";
        // 车辆目标
        public static final String MST_VEH_TARGET = "MST_VEH_TARGET";
        // 车辆目标对象
        public static final String MST_VEH_TARGET_OBJ = "MST_VEH_TARGET_OBJ";
        // 人体比对底库
        public static final String MST_BODY_TARGET_DB = "MST_BODY_TARGET_DB";
        // 人体比对底库对象
        public static final String MST_BODY_TARGET_DB_OBJ = "MST_BODY_TARGET_DB_OBJ";
        // 人体目标
        public static final String MST_BODY_TARGET = "MST_BODY_TARGET";
        // 人体目标对象
        public static final String MST_BODY_TARGET_OBJ = "MST_BODY_TARGET_OBJ";
        // 文件存储方案
        public static final String MST_STORAGE_PLAN = "MST_STORAGE_PLAN";
        // 文件存储配置
        public static final String MST_STORAGE_CONF = "MST_STORAGE_CONF";
        // 文件存储通道
        public static final String MST_STORAGE_CHANEL = "MST_STORAGE_CHANEL";
        // 采集任务配置
        public static final String MST_JOB_CONF = "MST_JOB_CONF";
        // 任务运行状态
        public static final String MST_TASK_STS = "MST_TASK_STS";
        // 设备运行状态
        public static final String MST_DEV_STS = "MST_DEV_STS";
        // 数据统计
        public static final String MST_DATA_STATS = "MST_DATA_STATS";
        // 结果筛选方案
        public static final String MST_RSLT_FILTER_PLAN = "MST_RSLT_FILTER_PLAN";
        // 结果筛选配置
        public static final String MST_RSLT_FILTER_CONF = "MST_RSLT_FILTER_CONF";
        // 任务流程方案
        public static final String MST_TASK_FLOW_PLAN = "MST_TASK_FLOW_PLAN";
        // 任务流程配置
        public static final String MST_TASK_FLOW_CONF = "MST_TASK_FLOW_CONF";
        // 人脸命中结果
        public static final String MST_FACE_HIT = "MST_FACE_HIT";
        // 车辆命中结果
        public static final String MST_VEH_HIT = "MST_VEH_HIT";
        // 区域分组
        public static final String MST_AREA_GRP = "MST_AREA_GRP";
        // 人脸抓拍原图
        public static final String WHS_FACE_FULL = "WHS_FACE_FULL";
        // 人脸抓拍结果
        public static final String WHS_FACE_CAP = "WHS_FACE_CAP";
        // 人脸比对结果
        public static final String WHS_FACE_COMP = "WHS_FACE_COMP";
        // 文件存储
        public static final String WHS_FILE_STORAGE = "WHS_FILE_STORAGE";
        // 车辆抓拍原图
        public static final String WHS_VEH_FULL = "WHS_VEH_FULL";
        // 车辆抓拍结果
        public static final String WHS_VEH_CAP = "WHS_VEH_CAP";
        // 车辆比对结果
        public static final String WHS_VEH_COMP = "WHS_VEH_COMP";
        // 以图搜图结果
        public static final String WHS_FACE_SEARCH = "WHS_FACE_SEARCH";
    }

    /**
     * 采集任务运行模式
     */
    public static class JobMode {
        public static final String FRONT = "front";
        public static final String BACKEND = "backend";
    }

    /**
     * 是否需要进行比对
     */
    public static class NeedCompare {
        public static final String YES = "1";
        public static final String NO = "2";
    }

    /**
     * 是否需要进行结构化
     */
    public static class NeedRecognize {
        public static final String YES = "1";
        public static final String NO = "2";
    }

    /**
     * 适配程序类型
     */
    public static class DevProg {
        public static final Integer FRONT = 1;
        public static final Integer PLATFORM = 2;
        public static final Integer SERVER_ANL = 3;
        public static final Integer SERVER_STREAM = 4;
        public static final Integer SERVER_TRANS = 5;
        public static final Integer FLOW = 21;
    }

    /**
     * 适配程序类型
     */
    public static class TaskStatusCode {
        public static final Integer PREPARED = 0;
        public static final Integer STARTED = 1;
        public static final Integer DONE = 2;
        public static final Integer ERROR = 9;
    }

    /**
     * 各厂商前缀
     */
    public static class Prefix {
        // 商汤
        public static final String SENSETIME = "sensetime";
        // 依图
        public static final String YITU = "yitu";
        // 旷世
        public static final String MEGVII = "megvii";
    }

    /**
     * 对象类型
     */
    public static class ObjType {
        // 人
        public static final int PERSON = 1;
        // 车
        public static final int VEHICLE = 2;
        // 物
        public static final int THING = 4;
        // 行为
        public static final int BEHAVIOR = 8;
    }

    /**
     * 对象类型
     */
    public static class StructureObjType {
        // 人体
        public static final int BODY = 2;
        // 车
        public static final int VEHICLE = 4;
    }

    /**
     * 布控任务类型
     */
    public static class SurveillanceJobType {
        // 人脸
        public static final int FACE = 1;
        // 人身
        public static final int BODY = 2;
        // 车
        public static final int VEHICLE = 4;
        // 物
        public static final int THING = 8;
        // 行为
        public static final int BEHAVIOR = 16;
    }

    public static class MsgFromType {
        // 网页告警
        public static final int WEB_ALARM = 0;
        // 统一接口布控任务告警
        public static final int UNIVERSAL_ALARM = 1;
    }

    /**
     * 年龄
     */
    public static class Age {
        // 老年
        public static final String OLD = "0";
        public static final String OLD_NAME = "老年";
        // 成人
        public static final String ADULT = "1";
        public static final String ADULT_NAME = "成人";
        // 儿童
        public static final String CHILD = "2";
        public static final String CHILD_NAME = "成人";
    }

    /**
     * 角度
     */
    public static class Angle {
        // 正面
        public static final String FRONT = "0";
        public static final String FRONT_NAME = "正面";
        // 侧面
        public static final String SIDE = "1";
        public static final String SIDE_NAME = "侧面";
        // 背面
        public static final String BACK = "2";
        public static final String BACK_NAME = "背面";
    }

    /**
     * 包
     */
    public static class Bag {
        // 手提包
        private static final String HANDCARRY = "0";
        public static final String HANDCARRY_NAME = "手提包";
        // 单肩包
        private static final String SHOULDERBAG = "1";
        public static final String SHOULDERBAG_NAME = "单肩包";
        // 背包
        private static final String BACKPACK = "2";
        public static final String BACKPACK_NAME = "背包";
        // 未检测出包
        private static final String NOBAG = "3";
        public static final String NOBAG_NAME = "未检测出包";
        // 未知
        private static final String UNKNOW = "4";
        public static final String UNKNOW_NAME = "未知";
    }

    /**
     * 帽子
     */
    public static class Hat {
        // 无
        private static final String NOTHING = "0";
        public static final String NOTHING_NAME = "无";
        // 有
        private static final String HAVE = "1";
        public static final String HAVE_NAME = "有";
    }

    /**
     * 胸前是否抱东西
     */
    public static class HoldObjectInFront {
        // 无
        private static final String NOTHING = "0";
        public static final String NOTHING_NAME = "无";
        // 有
        private static final String HAVE = "1";
        public static final String HAVE_NAME = "有";
    }

    /**
     * 裤子
     */
    public static class Pants {
        // 长裤
        private static final String TROUSERS = "0";
        public static final String TROUSERS_NAME = "长裤";
        // 短裤
        private static final String SHORTS = "1";
        public static final String SHORTS_NAME = "短裤";
        // 裙子
        private static final String SKIRT = "2";
        public static final String SKIRT_NAME = "裙子";
        // 没检测到裤子
        private static final String NOPANTS = "3";
        public static final String NOPANTS_NAME = "没检测到裤子";
    }

    /**
     * 塑料袋
     */
    public static class PlasticBag {
        // 无
        private static final String NOTHING = "0";
        public static final String NOTHING_NAME = "无";
        // 有
        private static final String HAVE = "1";
        public static final String HAVE_NAME = "有";
    }

    /**
     * 鞋子
     */
    public static class Shoes {
        // 靴子
        public static final String BOOTS = "0";
        public static final String BOOTS_NAME = "靴子";
        // 普通鞋子
        public static final String NONBOOTS = "1";
        public static final String NONBOOTS_NAME = "普通鞋子";
        // 未检测到鞋子
        public static final String NOSHOES = "2";
        public static final String NOSHOES_NAME = "未检测到鞋子";
    }

    /**
     * 塑料袋
     */
    public static class UpperClothing {
        // 短袖
        private static final String SHORTSLEEVE = "0";
        public static final String SHORTSLEEVE_NAME = "短袖";
        // 长袖
        private static final String LONGSLEEVE = "1";
        public static final String LONGSLEEVE_NAME = "长袖";
    }

    /**
     * 眼镜
     */
    public static class Glasses {
        // 未戴眼镜
        private static final String NOGLASS = "0";
        public static final String NOGLASS_NAME = "未戴眼镜";
        // 太阳眼镜
        private static final String SUNGLASSES = "1";
        public static final String SUNGLASSES_NAME = "太阳眼镜";
        // 普通眼镜
        private static final String GLASSES = "2";
        public static final String GLASSES_NAME = "普通眼镜";
    }

    /**
     * 是否戴口罩
     */
    public static class Mask {
        // 否
        private static final String NO = "0";
        public static final String NO_NAME = "否";
        // 是
        private static final String YES = "1";
        public static final String YES_NAME = "是";
    }

    /**
     * 是否微笑
     */
    public static class Smile {
        // 否
        private static final String NO = "0";
        public static final String NO_NAME = "否";
        // 是
        private static final String YES = "1";
        public static final String YES_NAME = "是";
    }

    /**
     * 人种
     */
    public static class Race {
        // 黄种人
        private static final String YELLOW = "0";
        public static final String YELLOW_NAME = "黄种人";
        // 黑人
        private static final String BLACK = "1";
        public static final String BLACK_NAME = "黑人";
        // 白人
        private static final String WHITE = "2";
        public static final String WHITE_NAME = "白人";
    }

    /**
     * 是否睁眼
     */
    public static class EyeOpen {
        // 否
        private static final String NO = "0";
        public static final String NO_NAME = "否";
        // 是
        private static final String YES = "1";
        public static final String YES_NAME = "是";
    }

    /**
     * 是否睁眼
     */
    public static class MouthOpen {
        // 否
        private static final String NO = "0";
        public static final String NO_NAME = "否";
        // 是
        private static final String YES = "1";
        public static final String YES_NAME = "是";
    }

    /**
     * 是否有胡子
     */
    public static class Beard {
        // 否
        private static final String NO = "0";
        public static final String NO_NAME = "否";
        // 是
        private static final String YES = "1";
        public static final String YES_NAME = "是";
    }

}
