package com.github.iqpizza6349.cloverytdownloader.frame;

import com.github.iqpizza6349.cloverytdownloader.core.YoutubeDownload;
import com.github.iqpizza6349.cloverytdownloader.frame.progress.YoutubeProgress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainFrame extends JFrame {

    private final String[] FORMAT_TYPES
            = {"mp3"};

    public MainFrame() {
        setTitle("Clover Youtube Downloader 1.1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JProgressBar progressBar = getProgressBar();
        TextField pathField = getPathField("파일을 다운받을 저장 공간을 입력해주세요.", 65);
        JLabel resultLabel = new JLabel("");

        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(pathField);
        container.add(getPathButton(pathField));
        container.add(getPathField("Youtube 주소", 62, "YOUTUBE"));
        container.add(getFormatType());
        container.add(getBlankLabel(2));
        container.add(convertButton(resultLabel));
        container.add(progressBar);
        container.add(getBlankLabel(4));
        container.add(resultLabel);

        pack();
        setSize(540, 360);
        setLocationRelativeTo(null);
        setVisible(true);

        youtubeProgress(progressBar);
        youtubeDownload(resultLabel);
    }

    private Label getBlankLabel(int repeat) {
        return new Label(ResourceUtil.BLANK.repeat(repeat));
    }

    private JButton getPathButton(TextField pathField) {
        JButton button = new JButton(getFolderIcon());
        button.setPreferredSize(new Dimension(28, 28));
        button.addActionListener((actionEvent) -> openFolder(pathField));
        return button;
    }

    private Icon getFolderIcon() {
        return new ImageIcon(ResourceUtil.getImage());
    }

    private TextField getPathField(String defaultText, int columns, String name) {
        TextField textField = new TextField(defaultText, columns);
        textField.setName(name);
        textField.setSize(14, 40);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
            }
        });
        textField.addTextListener((event) -> {
            TextField field = (TextField) event.getSource();
            if (field.getName().equalsIgnoreCase("YOUTUBE")) {
                ResourceUtil.setYoutubeURL(field.getText());
            }
        });

        return textField;
    }

    private TextField getPathField(String defaultText, int columns) {
        return getPathField(defaultText, columns, "");
    }

    private void openFolder(TextField pathField) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.showDialog(this, null);
        File directory = jFileChooser.getSelectedFile();
        String path = (directory == null) ? "" : directory.getPath();
        ResourceUtil.setDownloadPath(path);
        if (path.length() > 60) {
            path = path.substring(0, 57);
            path += "...";
        }
        pathField.setText(path);
    }

    private JComboBox<String> getFormatType() {
        return new JComboBox<>(FORMAT_TYPES);
    }

    private JProgressBar getProgressBar() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setLocation(400, 200);
        progressBar.setVisible(false);
        return progressBar;
    }

    private void youtubeProgress(JProgressBar progressBar) {
        YoutubeProgress youtubeProgress = new YoutubeProgress(progressBar);
        Thread progressBarThread = new Thread(youtubeProgress, "progress");
        progressBarThread.setDaemon(true);
        progressBarThread.start();
    }

    private void youtubeDownload(JLabel label) {
        YoutubeDownload youtubeDownload = new YoutubeDownload(label);
        Thread thread = new Thread(youtubeDownload, "youtube");
        thread.setDaemon(true);
        thread.start();
    }

    private JButton convertButton(JLabel resultLabel) {
        JButton jButton = new JButton("다운로드");
        jButton.setPreferredSize(new Dimension(90, 28));
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (ResourceUtil.isTrigger()) {
                    resultLabel.setText("다운로드 중입니다!");
                    return;
                }

                ResourceUtil.initializationProgress();
                ResourceUtil.switchTrigger(true);
                ResourceUtil.switchDownloadTrigger(true);
                resultLabel.setText("");
            }
        });
        return jButton;
    }
}
