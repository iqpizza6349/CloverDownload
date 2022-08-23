package com.github.iqpizza6349.cloverytdownloader.frame;

import com.github.iqpizza6349.cloverytdownloader.core.YTRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainFrame extends JFrame {

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
        container.add(new Label(BLANK.repeat(3)));
        container.add(convertButton());
        container.add(new Label(BLANK.repeat(3)));
        container.add(resultLabel);

        pack();
        setSize(540, 360);
        setLocationRelativeTo(null);
        setVisible(true);
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

    private JButton convertButton() {
        JButton jButton = new JButton("다운로드");
        jButton.setPreferredSize(new Dimension(90, 28));
        jButton.addActionListener((event) -> {
            // TODO 다운로드 중에는 title 역시 수정할 예정
            boolean isSuccess = false;
            try {
                isSuccess = downloadFile();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("유튜브 주소 혹은 파일 경로가 이상합니다.");
                return;
            }

            if (isSuccess) {
                resultLabel.setText("다운로드 성공");
            }
            else {
                resultLabel.setText("다운로드 실패.. 다시 시도해주세요.");
            }
        });
        return jButton;
    }

    private boolean downloadFile() throws IllegalArgumentException {
        return new YTRequest(
                youtubeField.getText(), formatType, downloadPath
        ).downloadYT();
    }
}
