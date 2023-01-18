package com.github.iqpizza6349.cloverytdownloader.frame.util;

import org.ini4j.Ini;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

final class IniUtil {

    private IniUtil() {}

    private static final Map<IniData, Map<String, String>> INI_DATA_LIST_MAP = new HashMap<>();

    private static Map<String, String> initMap(IniData iniData) {
        if (!(INI_DATA_LIST_MAP.containsKey(iniData))) {
            INI_DATA_LIST_MAP.put(iniData, new HashMap<>());
        }
        return INI_DATA_LIST_MAP.get(iniData);
    }

    private static void addData(IniData iniData, String key, String data) {
        if (!(iniData.foundArg(key))) {
            throw new IllegalArgumentException("no key such as " + key + " in " + iniData + " section!");
        }

        initMap(iniData).put(key, data);
    }

    static {
        try (final InputStream iniResource = IniUtil.class.getClassLoader()
                .getResourceAsStream("data.ini")) {
            //noinspection MismatchedQueryAndUpdateOfCollection
            final Ini ini = new Ini(iniResource);
            Map<String, Map<String, String>> iniMap = ini.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            for (String section : iniMap.keySet()) {
                section = section.toUpperCase();
                if (!IniData.found(section)) {
                    throw new RuntimeException("cannot found section such as " + section);
                }

                for (Map.Entry<String, String> entry : iniMap.get(section).entrySet()) {
                    addData(IniData.valueOf(section), entry.getKey(), entry.getValue());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, String> foundSection(IniData iniData) {
        return INI_DATA_LIST_MAP.get(iniData);
    }

    static String foundValue(IniData iniData, String arg) {
        return foundSection(iniData).get(arg);
    }

}
