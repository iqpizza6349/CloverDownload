package com.github.iqpizza6349.cloverytdownloader;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDL;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.exception.YoutubeException;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.mapper.VideoInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class YoutubeDownloadTest {

    private static final YoutubeDL DOWNLOAD = new YoutubeDL();

    @Test
    void versionParseTest() throws YoutubeException {
        // when
        final String version = DOWNLOAD.getVersion();

        System.out.println(version);
    }

    @Test
    void downloadTest() throws YoutubeException {
        // given
        final String url = "https://www.youtube.com/watch?v=K9muFrREepg&list=RDK9muFrREepg";
        final YoutubeDL youtubeDL = new YoutubeDL();
        final String title = "조pd(ZoPD) - 친구여 (Feat. 인순이) [가사/Lyrics]";

        // when
        VideoInfo videoInfo = youtubeDL.getVideoInfo(url);

        // verify
        assertEquals(title, videoInfo.title);
    }

    @Test
    void videoInfoTest() throws YoutubeException {
        // given
        final String url = "https://www.youtube.com/playlist?list=PLfI752FpVCS9hh_FE8uDuRVgPPnAivZTY";
        final YoutubeDL youtubeDL = new YoutubeDL();

        // when
        final VideoInfo videoInfo = youtubeDL.getVideoInfo(url);

        // then
        System.out.println(videoInfo);

    }

}
