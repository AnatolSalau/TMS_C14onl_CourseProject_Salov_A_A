package by.salov.tms.courseproject.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void ifKeyStartWithTrimIt() {
        Map<String, String[]> stringStringMap = new HashMap<>();
        String[] entryArr = {"userEntry-1","userEntry-2","userEntry-3"};
        stringStringMap.put("userId-1", entryArr);
        stringStringMap.put("userId-2", entryArr);
        stringStringMap.put("userId-3", entryArr);
        stringStringMap.put("userId-4", entryArr);
        StringUtils stringUtils = new StringUtils();
        List<String> strings = stringUtils.ifKeyStartWithTrimIt(stringStringMap, "userId-");
        System.out.println(strings);
    }
}