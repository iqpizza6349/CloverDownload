package com.github.iqpizza6349.cloverytdownloader.youtubedl.domain;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.utils.YoutubeOptionUtil;

public class YoutubeOption {

    public static YoutubeOptionBuilder builder() {
        return new YoutubeOptionBuilder();
    }

    private final String options;

    public YoutubeOption(String options) {
        this.options = options;
    }

    public String getOptions() {
        return options;
    }

    public static class YoutubeOptionBuilder {
        private final YoutubeOptionUtil optionUtil;

        public YoutubeOptionBuilder() {
            this.optionUtil = new YoutubeOptionUtil();
            optionUtil.addOption("format", "\"bestaudio/best[height<=?720]\"");
            optionUtil.addOption("no-cache-dir");
            optionUtil.addOption("no-mtime");
            optionUtil.addOption("extract-audio");
            optionUtil.addOption("audio-format", "mp3");
            optionUtil.addOption("output", "\"%(title)s.%(ext)s\"");
            optionUtil.addOption("prefer-ffmpeg");
            optionUtil.addOption("audio-quality", 0);
            optionUtil.addOption("add-metadata");
            optionUtil.addOption("embed-thumbnail");
            optionUtil.addOption("ignore-errors");
            optionUtil.addOption("continue");
            optionUtil.addOption("retries", 10);
        }

        public YoutubeOptionBuilder videoFormat(String videoFormat) {
            optionUtil.addOption("format", videoFormat);
            return this;
        }

        public YoutubeOptionBuilder cache(boolean cache) {
            if (cache) {
                optionUtil.removeOption("no-cache-dir");
            }
            else {
                optionUtil.addOption("no-cache-dir");
            }

            return this;
        }

        public YoutubeOptionBuilder audioOnly(boolean audioOnly) {
            if (audioOnly) {
                optionUtil.addOption("extract-audio");
            }
            else {
                optionUtil.removeOption("extract-audio");
            }

            return this;
        }

        public YoutubeOptionBuilder audioFormat(String audioFormat) {
            optionUtil.addOption("audio-format", audioFormat);
            return this;
        }

        public YoutubeOptionBuilder audioQuality(int quality) {
            if (quality < 0 || quality > 9) {
                throw new IllegalArgumentException("AudioQuality must be 0 ~ 9");
            }

            optionUtil.addOption("audio-quality", quality);
            return this;
        }

        public YoutubeOptionBuilder outputFormat(String outputFormat) {
            optionUtil.addOption("output", outputFormat);
            return this;
        }

        public YoutubeOptionBuilder preferNative(boolean preferNative) {
            optionUtil.removeOption("prefer-ffmpeg");
            if (preferNative) {
                optionUtil.addOption("hls-prefer-native");
            }
            else {
                optionUtil.addOption("hls-prefer-ffmpeg");
            }

            return this;
        }

        public YoutubeOptionBuilder metaData(boolean metaData) {
            if (metaData) {
                optionUtil.addOption("add-metadata");
            }
            else {
                optionUtil.removeOption("add-metadata");
            }

            return this;
        }

        public YoutubeOptionBuilder thumbnail(boolean thumbnail) {
            if (thumbnail) {
                optionUtil.addOption("embed-thumbnail");
            }
            else {
                optionUtil.removeOption("embed-thumbnail");
            }

            return this;
        }

        public YoutubeOptionBuilder ignoreErrors(boolean ignoreErrors) {
            if (ignoreErrors) {
                optionUtil.addOption("ignore-errors");
            }
            else {
                optionUtil.removeOption("ignore-errors");
            }

            return this;
        }

        public YoutubeOptionBuilder continued(boolean continued) {
            if (continued) {
                optionUtil.addOption("continue");
            }
            else {
                optionUtil.removeOption("continue");
            }

            return this;
        }

        public YoutubeOptionBuilder retries(int retries) {
            if (retries <= 0 || retries > 100) {
                throw new IllegalArgumentException("retries must be 1 ~ 99");
            }

            optionUtil.addOption("retries", retries);
            return this;
        }

        public YoutubeOption build() {
            return new YoutubeOption(optionUtil.buildOptions());
        }
    }

}
