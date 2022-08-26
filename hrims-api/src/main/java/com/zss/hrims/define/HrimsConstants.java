package com.zss.hrims.define;

/**
 * @author ZSS
 * @date 2022/7/22 14:12
 * @desc 系统常量类
 */
@SuppressWarnings("unused")
public class HrimsConstants {

    public static final String HRIMS_TOKEN = "hrims-token";

    /**
     * 时间戳: 2022-07-01
     */
    public static final String START_TIME = "1656633600000";

    /**
     * 分割线
     */
    public static final String SEPARATOR = "/";

    /**
     * 用户权限
     */
    public interface Role {
        /**
         * 管理员
         */
        int ADMIN = 1;
        /**
         * 普通用户
         */
        int ORDINARY = 0;
    }

    public enum RatingEnum {
        /**
         * 评级 -- 初、中、高
         */
        PRIMARY("初级", 1),
        INTERMEDIATE("中级", 2),
        ADVANCED("高级", 3);

        private String level;
        private Integer code;

        RatingEnum(String level, Integer code) {
            this.level = level;
            this.code = code;
        }

        RatingEnum() {
        }

        public String getLevel() {
            return level;
        }

        public Integer getCode() {
            return code;
        }

        public static boolean contains(String rating) {
            for (RatingEnum e : RatingEnum.values()) {
                if (myEquals(e.getLevel(), rating)) {
                    return true;
                }
            }
            return false;
        }

        public static String getLevel(Integer code) {
            for (RatingEnum e : RatingEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e.getLevel();
                }
            }
            return "初级";
        }

        public static Integer getCode(String rating) {
            for (RatingEnum e : RatingEnum.values()) {
                if (myEquals(e.getLevel(), rating)) {
                    return e.getCode();
                }
            }
            return null;
        }
    }

    public enum InterviewStateEnum {
        /**
         * 面试状态枚举
         */
        RECOMMEND("推荐", 1),
        INTERVIEWING("面试中", 2),
        SUCCESS("通过", 3),
        FAIL("失败", 4);

        private String desc;
        private Integer code;

        InterviewStateEnum(String desc, Integer code) {
            this.desc = desc;
            this.code = code;
        }

        InterviewStateEnum() {
        }

        public String getDesc() {
            return desc;
        }

        public Integer getCode() {
            return code;
        }

        public static Integer getCodeByDesc(String desc) {
            for (InterviewStateEnum e : InterviewStateEnum.values()) {
                if (e.getDesc().equals(desc)) {
                    return e.getCode();
                }
            }
            return 1;
        }

        public static String getDescByCode(Integer code) {
            for (InterviewStateEnum e : InterviewStateEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e.getDesc();
                }
            }
            return null;
        }
    }

    /**
     * 字符串比对
     *
     * @param cs1 t1
     * @param cs2 t2
     * @return 是否相同
     */
    public static boolean myEquals(CharSequence cs1, CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        } else if (cs1 != null && cs2 != null) {
            if (cs1.length() != cs2.length()) {
                return false;
            } else if (cs1 instanceof String && cs2 instanceof String) {
                return cs1.equals(cs2);
            } else {
                int length = cs1.length();
                for (int i = 0; i < length; ++i) {
                    if (cs1.charAt(i) != cs2.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }

    public enum DateEnum {
        /**
         * 天
         */
        DAY("天"),
        /**
         * 周
         */
        WEEK("周"),
        /**
         * 月
         */
        MONTH("月"),
        /**
         * 六个月
         */
        SIX_MONTH("半年"),
        /**
         * 年
         */
        YEAR("年");

        private String desc;

        DateEnum(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public static DateEnum getEnum(String desc) {
            for (DateEnum e : DateEnum.values()) {
                if (myEquals(e.getDesc(), desc)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum RelationEnum {
        // 用户 oneToMany 企业
        U2E,
        // 企业 oneToMany 候选人
        E2C,
        // 候选人 oneToMany 面试
        C2I;
    }
}
