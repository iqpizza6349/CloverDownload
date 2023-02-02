package com.github.iqpizza6349.cloverytdownloader.youtubedl.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class YoutubeOptionUtil {

    private final Map<String, String> options;

    public YoutubeOptionUtil() {
        this.options = new HashMap<>(12);
    }

    public void addOption(String key) {
        options.put(key, "");
    }

    public void addOption(String key, String value) {
        options.put(key, value);
    }

    public void addOption(String key, int value) {
        options.put(key, String.valueOf(value));
    }

    public void removeOption(String key) {
        options.remove(key);
    }

    public String buildOptions() {
        final StringBuilder buildOptions = new StringBuilder();

        Iterator<Map.Entry<String, String>> it = options.entrySet().iterator();
        while ((it.hasNext())) {
            Map.Entry<String, String> option = it.next();
            final String name = option.getKey();
            final String value = option.getValue();

            String optionFormatted = String.format("--%s %s", name, value).trim();
            buildOptions.append(optionFormatted)
                    .append(" ");
            it.remove();
        }

        return buildOptions.toString()
                .trim();
    }
}
