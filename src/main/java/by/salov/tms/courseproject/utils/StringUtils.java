package by.salov.tms.courseproject.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StringUtils {
    public List<String> ifKeyStartWithTrimIt(Map<String,String[]> requestParamMap, String deletedSubstring) {
        int deletedSubstringLength = deletedSubstring.length();
        List<String> keyList = new ArrayList<>();
        requestParamMap.keySet().forEach(
                key -> {
                    if(key.startsWith(deletedSubstring)) {
                        int keyLength = key.length();
                        String result = key.substring(deletedSubstringLength, keyLength);// count start in 0 and 8 is excluded;
                        keyList.add(result);
                    }
                }
        );
        return keyList;
    }
}
