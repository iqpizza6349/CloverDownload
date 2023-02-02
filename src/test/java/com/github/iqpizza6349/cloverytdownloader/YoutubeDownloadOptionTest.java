package com.github.iqpizza6349.cloverytdownloader;

import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeOption;
import org.junit.jupiter.api.Test;

public class YoutubeDownloadOptionTest {

    @Test
    void optionTest() {
        YoutubeOption option = YoutubeOption.builder()
                .build();

        System.out.println(option.getOptions());
    }

}
