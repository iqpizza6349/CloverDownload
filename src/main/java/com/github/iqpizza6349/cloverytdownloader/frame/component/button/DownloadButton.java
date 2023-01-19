package com.github.iqpizza6349.cloverytdownloader.frame.component.button;

import com.github.iqpizza6349.cloverytdownloader.frame.component.combo.FormatComboBox;
import com.github.iqpizza6349.cloverytdownloader.frame.component.text.TextInputField;

import java.awt.*;
import java.awt.event.ActionListener;

public class DownloadButton extends CustomButton {

    private final TextInputField downloadPath;
    private final TextInputField youtubePath;
    private final FormatComboBox formatComboBox;

    public DownloadButton(TextInputField downloadPath, TextInputField youtubeURL, FormatComboBox comboBox) {
        super("다운로드");
        super.setPreferredSize(new Dimension(90, 28));
        this.downloadPath = downloadPath;
        this.youtubePath = youtubeURL;
        this.formatComboBox = comboBox;
        super.addActionListener(addOnDownloadQueue());
    }

    private ActionListener addOnDownloadQueue() {
        // TODO: 2023-01-19 async dequeue 에 추가된다.
        return super.actionListener;
    }

}
