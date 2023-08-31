package entity;

public interface MessageTypes {
    //学生学籍模块(2)
    //学籍信息操作模块(20)
    public static final int STUDENTROLL_INFO_QUERY_ID = 201;
    public static final int STUDENTROLL_INFO_QUERY_ID_SUCCESS = 2011;
    public static final int STUDENTROLL_INFO_QUERY_ID_FAIL = 2012;

    public static final int STUDENTROLL_ADD = 202;
    public static final int STUDENTROLL_ADD_SUCCESS = 2021;
    public static final int STUDENTROLL_ADD_FAIL = 2022;

    public static final int STUDENTROLL_DELETE = 203;
    public static final int STUDENTROLL_DELETE_SUCCESS = 2031;
    public static final int STUDENTROLL_DELETE_FAIL = 2032;

    public static final int STUDENTROLL_MODIFY = 204;
    public static final int STUDENTROLL_MODIFY_SUCCESS = 2041;
    public static final int STUDENTROLL_MODIFY_FAIL = 2042;

    public static final int STUDENTROLL_INFO_QUERY_NAME = 205;
    public static final int STUDENTROLL_INFO_QUERY_NAME_SUCCESS = 2051;
    public static final int STUDENTROLL_INFO_QUERY_NAME_FAIL = 2052;

    public static final int STUDENTROLL_INFO_QUERY_ALL = 206;
    public static final int STUDENTROLL_INFO_QUERY_ALL_SUCCESS = 2061;
    public static final int STUDENTROLL_INFO_QUERY_ALL_FAIL = 2062;

    //学籍信息响应模块(21)
    public static final int STUDENT_STATUS_INFORMATION = 211;
    public static final int STUDENT_STATUS_INFORMATION_SUCCESS = 211;
    public static final int STUDENT_STATUS_INFORMATION_FAIL = 212;
}
