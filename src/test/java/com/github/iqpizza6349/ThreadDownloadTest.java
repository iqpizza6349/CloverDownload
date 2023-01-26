package com.github.iqpizza6349;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDL;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeOption;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeResponse;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.exception.YoutubeException;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.concurrent.Callable;

public class ThreadDownloadTest {

    private static final YoutubeDL DOWNLOAD = new YoutubeDL();

    private static final String DIRECTORY = Paths.get("").toAbsolutePath().toString();

    private static final YoutubeLink FIRST_REQUEST = new YoutubeLink(
            "https://www.youtube.com/watch?v=-tJtsKngXJU",
            DIRECTORY, YoutubeOption.builder().build()
    );

    private static final YoutubeLink SECOND_REQUEST = new YoutubeLink(
            "https://www.youtube.com/watch?v=qQzdAsjWGPg",
            DIRECTORY, YoutubeOption.builder().build()
    );

    @Test
    void syncTest() throws YoutubeException {
        // given
        // already given in static variable.

        // when
        final YoutubeResponse firstResponse = DOWNLOAD.execute(FIRST_REQUEST);
        final YoutubeResponse secondResponse = DOWNLOAD.execute(SECOND_REQUEST);

        // then
        System.out.println(firstResponse);
        System.out.println(secondResponse);
    }

    @Test
    void asyncTest() throws Exception {
        System.out.println(FIRST_REQUEST);
        System.out.println(SECOND_REQUEST);
        System.out.println("====================");

        // given
        // already given in static variable.
        Callable<YoutubeResponse> firstResponse = () -> {
            try {
                return DOWNLOAD.execute(FIRST_REQUEST);
            } catch (YoutubeException e) {
                throw new RuntimeException(e);
            }
        };

        Callable<YoutubeResponse> secondResponse = () -> {
            try {
                return DOWNLOAD.execute(SECOND_REQUEST);
            } catch (YoutubeException e) {
                throw new RuntimeException(e);
            }
        };


        // when
        YoutubeResponse first = firstResponse.call();
        YoutubeResponse second = secondResponse.call();

        // then
        System.out.println(first);
        System.out.println(second);
    }


}
