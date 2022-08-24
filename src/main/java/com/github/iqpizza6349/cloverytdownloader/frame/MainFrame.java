package com.github.iqpizza6349.cloverytdownloader.frame;

import com.github.iqpizza6349.cloverytdownloader.core.YTRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainFrame extends JFrame {

    public static Integer currentProgress = 0;

    private String downloadPath = "";
    private final TextField pathField
            = getPathField("파일을 다운받을 저장 공간을 입력해주세요.", 65);
    private final TextField youtubeField
            = getPathField("Youtube 주소", 62);
    private final String[] FORMAT_TYPES
            = {"mp3"};
    private String formatType = "mp3";
    private static final String BLANK = "                     ";
    private final JLabel resultLabel = new JLabel("");
    private final JProgressBar progressBar = getProgressBar();
    private boolean trigger = false;
    private boolean downloadTrigger = false;

    public MainFrame() {
        setTitle("Clover Youtube Downloader 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(pathField);
        container.add(getPathButton());
        container.add(youtubeField);
        container.add(getFormatType());
        container.add(getBlankLabel(2));
        container.add(convertButton());
        container.add(progressBar);
        container.add(getBlankLabel(4));
        container.add(resultLabel);

        pack();
        setSize(540, 360);
        setLocationRelativeTo(null);
        setVisible(true);

        youtubeProgress();
        youtubeDownload();
    }

    private Label getBlankLabel(int repeat) {
        return new Label(BLANK.repeat(repeat));
    }

    private JButton getPathButton() {
        JButton button = new JButton(getFolderIcon());
        button.setPreferredSize(new Dimension(28, 28));
        button.addActionListener((actionEvent) -> openFolder());
        return button;
    }

    private Icon getFolderIcon() {
        return new ImageIcon(ResourceUtil.getImage());
    }

    private TextField getPathField(String defaultText, int columns) {
        TextField textField = new TextField(defaultText, columns);
        textField.setSize(14, 40);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
            }
        });
        return textField;
    }

    private void openFolder() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.showDialog(this, null);
        File directory = jFileChooser.getSelectedFile();
        String path = (directory == null) ? "" : directory.getPath();
        downloadPath = path;
        if (path.length() > 60) {
            path = path.substring(0, 57);
            path += "...";
        }
        pathField.setText(path);
    }

    private JComboBox<String> getFormatType() {
        JComboBox<String> jComboBox = new JComboBox<>(FORMAT_TYPES);
        jComboBox.addItemListener(
                (event) -> formatType = event.getItemSelectable().toString()
        );
        return jComboBox;
    }

    private JProgressBar getProgressBar() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setLocation(400, 200);
        progressBar.setVisible(false);
        return progressBar;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void youtubeProgress() {
        Runnable runnable = () -> {
            while (true) {
                if (!trigger) {
                    Thread.yield();
                } else {
                    if (!progressBar.isVisible()) {
                        progressBar.setVisible(true);
                    }

                    progressBar.setValue(currentProgress);
                    if (currentProgress >= 100) {
                        trigger = false;
                    }
                }
            }
        };
        Thread progressBarThread = new Thread(runnable, "progress");
        progressBarThread.setDaemon(true);
        progressBarThread.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void youtubeDownload() {
        Runnable runnable = () -> {
            while (true) {
                if (!trigger && !downloadTrigger) {
                    Thread.yield();
                } else {
                    downloadTrigger = false;
                    downloadFile();
                }
            }
        };
        Thread thread = new Thread(runnable, "youtube");
        thread.setDaemon(true);
        thread.start();
    }

    private JButton convertButton() {
        JButton jButton = new JButton("다운로드");
        jButton.setPreferredSize(new Dimension(90, 28));
        jButton.addActionListener((event) -> {
            if (trigger) {
                resultLabel.setText("다운로드 중입니다!");
                return;
            }

            currentProgress = 0;
            trigger = true;
            downloadTrigger = true;
            resultLabel.setText("");
        });
        return jButton;
    }

    private void downloadFile() throws IllegalArgumentException {
        new YTRequest(
                youtubeField.getText(), downloadPath
        ).downloadYT();
    }
}
