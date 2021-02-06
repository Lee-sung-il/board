package common;

import java.util.regex.Pattern;

public class RegExp {
    public  static  final  int  TYPE_MEMBER_ID = 1;
    public  static  final  int  TYPE_MEMBER_PWD = 2;
    public  static  final  int  TYPE_MEMBER_NAME = 3;
    public  static  final  int  TYPE_MEMBER_AGE = 4;

    public static final int TYPE_NUMBER = 11;
    public static final  int TYPE_SEARCH = 12;
    public static final int TYPE_SUB = 13;


    public  static final  String EXP_MEMBER_ID = "^[a-z0-9]{4,8}$";
    public  static final  String EXP_MEMBER_PWD = "^[a-zA-Z0-9!@#$%^&*]{4,12}$";
    public  static final  String EXP_MEMBER_NAME = "^[가-힣]{2,5}$";
    public  static final  String EXP_MEMBER_AGE = "^[0-9]{1,3}$";

    public static final String EXP_NUMBER = "^[0-9]*$";

    public static final String EXP_SEARCH = "^[0-9a-zA-Z가-힣 ]{2,10}$";

    public static final String EXP_SUB = "^.{2,50}$";

    public  boolean isNotEmpty(String data) {
        if (data == null || data.equals("")) {
            return false;
        }else {
            return true;
        }

    }

    public boolean validateCheck(int type, String data) {
        boolean result = false;
        if (!isNotEmpty(data)) {
            return result;
        }

        switch (type) {
            case TYPE_MEMBER_ID:
                result = Pattern.matches(EXP_MEMBER_ID,data);
                break;
            case TYPE_MEMBER_PWD:
                result = Pattern.matches(EXP_MEMBER_PWD,data);
                break;
            case TYPE_MEMBER_NAME:
                result = Pattern.matches(EXP_MEMBER_NAME,data);
                break;
            case TYPE_MEMBER_AGE:
                result = Pattern.matches(EXP_MEMBER_AGE,data);
                break;
            case TYPE_NUMBER:
                result = Pattern.matches(EXP_NUMBER,data);
                break;
            case TYPE_SEARCH:
                result = Pattern.matches(EXP_SEARCH,data);
                break;
            case TYPE_SUB:
                result = Pattern.matches(EXP_SUB,data);
                break;

        }
        return result;

    }
}
