package com.lyp;

import org.yaml.snakeyaml.Yaml;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-19 12:18
 **/
public class YamlUtils {

    public static String toYamlString(Object obj) {
        return new Yaml().dump(obj);
    }

    public static Object fromYaml(String yamlString, Class c) {
        return new Yaml().loadAs(yamlString, c);
    }

}
