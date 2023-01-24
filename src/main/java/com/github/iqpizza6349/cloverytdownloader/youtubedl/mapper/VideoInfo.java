package com.github.iqpizza6349.cloverytdownloader.youtubedl.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoInfo {

    public String id;

    @JsonProperty("fulltitle")
    public String fullTitle;

    public String title;

    @JsonProperty("upload_date")
    public String uploadDate;

    @JsonProperty("display_id")
    public String displayId;

    public int duration;

    public String description;

    public String thumbnail;

    public String license;

    @JsonProperty("uploader_id")
    public String uploaderId;

    public String uploader;

    @JsonProperty("player_url")
    public String playerUrl;

    @JsonProperty("webpage_url")
    public String webpageUrl;

    @JsonProperty("webpage_url_basename")
    public String webpageUrlBasename;

    public String resolution;

    public int width;

    public int height;

    public String format;

    public String ext;

    @JsonProperty("http_headers")
    public HttpHeader httpHeader;

    public List<String> categories;

    public List<String> tags;

    public List<VideoFormat> formats;

    public List<VideoThumbnail> thumbnails;

}
