package com.github.iqpizza6349;

import com.github.iqpizza6349.cloverytdownloader.core.DownloadQueue;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeLink;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeOption;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DownloadQueueTest {

    private static final DownloadQueue QUEUE = DownloadQueue.getInstance();

    @Test
    void addTest() {
        // given
        Path path = Path.of("");
        YoutubeOption option = YoutubeOption.builder()
                .build();
        YoutubeLink link = new YoutubeLink("aa", path.toString(), option);

        // when
        QUEUE.add(link);

        // verify
        assertEquals(link, QUEUE.peek());

        // then
        YoutubeLink element = QUEUE.getElement();

        // verify
        assertEquals(link, element);

        System.out.println(element);
    }

}
