package day12;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utilities.ExcelUtil;

import java.util.List;
import java.util.Map;

public class P07_ExcelUtilPractice {

    @Test
    public void test1() {

        ExcelUtil library = new ExcelUtil
                ("src/test/resources/Library.xlsx", "Library1-short");
        List<Map<String, String>> allUserInfo = library.getDataList();

        for (Map<String, String> eachUser : allUserInfo) {
            System.out.println("eachUser = " + eachUser);
        }
    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void credentialsTest(Map<String, String> userInfo) {

        System.out.println("userInfo = " + userInfo);
        System.out.println("userInfo.get(\"Email\") = " + userInfo.get("Email"));
        System.out.println("userInfo.get(\"Password\") = " + userInfo.get("Password"));
        System.out.println("------------------------");
    }

    public static List<Map<String, String>> getExcelData() {

        ExcelUtil library = new ExcelUtil
                ("src/test/resources/Library.xlsx", "Library1-short");

        return library.getDataList();
    }
}


