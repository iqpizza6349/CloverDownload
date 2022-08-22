package com.github.iqpizza6349.cloverytdownloader.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainFrame extends JFrame {

    private String downloadPath = "";
    private final TextField pathField = getPathField();

    public MainFrame() {
        setTitle("Clover Youtube Downloader 1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(pathField);
        container.add(getPathButton());

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
        return new ImageIcon(ResourceUtil.getResource());
    }

    private TextField getPathField() {
        TextField textField = new TextField("파일을 다운받을 저장 공간을 입력해주세요.", 60);
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

    public String getDownloadPath() {
        return downloadPath;
    }
}
