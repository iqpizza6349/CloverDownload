package com.github.iqpizza6349.cloverytdownloader.frame;

import com.github.iqpizza6349.cloverytdownloader.frame.component.button.DirectoryButton;
import com.github.iqpizza6349.cloverytdownloader.frame.component.button.DownloadButton;
import com.github.iqpizza6349.cloverytdownloader.frame.component.combo.FormatComboBox;
import com.github.iqpizza6349.cloverytdownloader.frame.component.text.TextInputField;
import com.github.iqpizza6349.cloverytdownloader.frame.util.IniData;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ResourceUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class MainFrame extends JFrame implements Runnable {

    public MainFrame() {
        init();
    }

    @Override
    public void run() {
        // JFrame 을 구성해야한다.
        // 비동기 progress bar 가 동작해야한다.
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

        final JPanel panel = new JPanel(new FlowLayout());

        final JPanel directoryPanel = setDirectoryPanel();
        panel.add(directoryPanel);
        final JPanel youtubePanel = setYoutubePanel();
        panel.add(youtubePanel);
        panel.add(setButtonPanel(directoryPanel.getComponents(), youtubePanel.getComponents()));

        topContainer.add(panel);


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
        JPanel container = new JPanel(new BorderLayout());
        TextInputField downloadPath = findComponent(pathData, TextInputField.class);
        TextInputField youtubePath = findComponent(youtubeData, TextInputField.class);
        FormatComboBox format = findComponent(youtubeData, FormatComboBox.class);
        DownloadButton button = new DownloadButton(downloadPath, youtubePath, format);

        container.add(button);
        return container;
    }

    private static <T extends Component> T findComponent(final Component[] data, Class<T> clazz) {
        return (T) Arrays.stream(data).filter(component -> component.getClass() == clazz)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("cannot found such find of " + clazz + "type in elements!"));

    }

}
