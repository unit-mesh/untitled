package cc.unitmesh.untitled.demo.utils;

import org.springframework.beans.BeanUtils;

public class CopyUtils {

    public static void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
}
