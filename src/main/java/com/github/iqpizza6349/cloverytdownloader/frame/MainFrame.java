package com.github.iqpizza6349.cloverytdownloader.frame;

import com.github.iqpizza6349.cloverytdownloader.core.DownloadCore;
import com.github.iqpizza6349.cloverytdownloader.frame.component.bar.DownloadProgressBar;
import com.github.iqpizza6349.cloverytdownloader.frame.component.button.DirectoryButton;
import com.github.iqpizza6349.cloverytdownloader.frame.component.button.DownloadButton;
import com.github.iqpizza6349.cloverytdownloader.frame.component.combo.FormatComboBox;
import com.github.iqpizza6349.cloverytdownloader.frame.component.text.TextInputField;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ComponentUtil;
import com.github.iqpizza6349.cloverytdownloader.frame.util.IniData;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ResourceUtil;

import javax.swing.*;
import java.awt.*;

import static com.github.iqpizza6349.cloverytdownloader.frame.util.ComponentUtil.findComponent;

public class MainFrame extends JFrame implements Runnable {

    public MainFrame() {
        init();
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            new DownloadCore().start();
        }
    }

    private void init() {
        setIconImage(ResourceUtil.getLogoIcon());
        setTitle(
                ResourceUtil.getValueFromIni(IniData.PROJECT, "name") + " " +
                ResourceUtil.getValueFromIni(IniData.VERSION, "version") + " " +
                ResourceUtil.getValueFromIni(IniData.VERSION, "scheme")
        );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container topContainer = getContentPane();
        topContainer.setLayout(new FlowLayout());

        JPanel userPanel = new JPanel();
        userPanel.setName("userPanel");
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));

        final JPanel directoryPanel = setDirectoryPanel();
        final JPanel youtubePanel = setYoutubePanel();
        final JPanel buttonPanel = setButtonPanel(directoryPanel.getComponents(), youtubePanel.getComponents());

        userPanel.add(directoryPanel);
        userPanel.add(youtubePanel);
        userPanel.add(buttonPanel);

        // add download progress bar panel
        final JPanel progressPanel = setProgressPanel();

        topContainer.add(userPanel);
        topContainer.add(progressPanel);

        pack();
        setSize(540, 360);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel setDirectoryPanel() {
        JPanel container = new JPanel();
        TextInputField inputField = new TextInputField("파일을 다운받을 저장 공간을 입력해주세요.", 64);
        DirectoryButton directoryButton = new DirectoryButton(inputField);

        container.add(inputField);
        container.add(directoryButton);

        return container;
    }

    private JPanel setYoutubePanel() {
        JPanel container = new JPanel();
        TextInputField inputField = new TextInputField("Youtube 주소");
        FormatComboBox comboBox = new FormatComboBox();

        container.add(inputField);
        container.add(comboBox);

        return container;
    }

    private JPanel setButtonPanel(Component[] pathData, Component[] youtubeData) {
        JPanel container = new JPanel(new FlowLayout());
        TextInputField downloadPath = findComponent(pathData, TextInputField.class);
        TextInputField youtubePath = findComponent(youtubeData, TextInputField.class);
        FormatComboBox format = findComponent(youtubeData, FormatComboBox.class);
        DownloadButton button = new DownloadButton(downloadPath, youtubePath, format);

        container.add(button);
        return container;
    }

    private JPanel setProgressPanel() {
        JPanel container = new JPanel();
        container.setName("progressPanel");
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(new DownloadProgressBar());
        container.add(new DownloadProgressBar());
        container.add(new DownloadProgressBar());
        container.add(new DownloadProgressBar());
        container.add(new DownloadProgressBar());

        ComponentUtil.addJProgressBar(container.getComponents());
        return container;
    }
}
