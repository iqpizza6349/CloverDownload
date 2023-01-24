package com.github.iqpizza6349.cloverytdownloader.youtubedl.mapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VideoThumbnail {

    public String url;
    public String id;

}
