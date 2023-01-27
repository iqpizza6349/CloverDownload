package com.github.iqpizza6349.cloverytdownloader.frame.component.button;

import com.github.iqpizza6349.cloverytdownloader.core.DownloadQueue;
import com.github.iqpizza6349.cloverytdownloader.frame.component.combo.FormatComboBox;
import com.github.iqpizza6349.cloverytdownloader.frame.component.text.TextInputField;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.YoutubeDL;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.domain.YoutubeRequest;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.exception.YoutubeException;
import com.github.iqpizza6349.cloverytdownloader.youtubedl.mapper.VideoInfo;

import java.awt.*;
import java.awt.event.ActionListener;

public class DownloadButton extends CustomButton {
    private static final YoutubeDL YOUTUBE_INSTANCE = new YoutubeDL();

    private final TextInputField downloadPath;
    private final TextInputField youtubePath;
    private final FormatComboBox formatComboBox;

    public DownloadButton(TextInputField downloadPath, TextInputField youtubeURL, FormatComboBox comboBox) {
        super("다운로드");
        super.setPreferredSize(new Dimension(90, 28));

        assert (downloadPath != null) : "download path must not be null";
        assert (youtubeURL != null) : "youtube text input field must not be null";
        assert (comboBox != null) : "combo box must not be null";

        this.downloadPath = downloadPath;
        this.youtubePath = youtubeURL;
        this.formatComboBox = comboBox;
        super.addActionListener(addOnDownloadQueue());
    }

    private ActionListener addOnDownloadQueue() {
        return (event) -> {
            final String youtubeUrl = youtubePath.getText();
            final String downloadDirectory = downloadPath.getText();
            @SuppressWarnings("ConstantConditions")
            final String format = formatComboBox.getSelectedItem().toString()
                            .toLowerCase();

            if (youtubeUrl == null || youtubeUrl.isBlank()) {
                // youtube url is null
                System.err.println("youtube url is null or empty");
                return;
            }
            else if (downloadDirectory == null || downloadDirectory.isBlank()) {
                // download directory is null
                System.err.println("download directory is null or empty");
                return;
            }

            YoutubeRequest request;
            try {
                request = new YoutubeRequest(youtubeUrl, downloadDirectory);
            } catch (IllegalArgumentException e) {
                System.err.printf("download path or youtube url isn't valid. %s", e);
                return;
            }

            final String url = request.getYoutubeUrl();

            DownloadQueue.getInstance()
                    .add(request.toLink(format), getTitle(url));
            System.out.println(DownloadQueue.getInstance().peek());
        };
    }

    private String getTitle(String url) {
        final String title;

        try {
            title = findVideoInfo(url).title;
        } catch (YoutubeException e) {
            throw new RuntimeException(e);
        }
        return title;
    }

    private VideoInfo findVideoInfo(String url) throws YoutubeException {
        return YOUTUBE_INSTANCE.getVideoInfo(url);
    }
}
