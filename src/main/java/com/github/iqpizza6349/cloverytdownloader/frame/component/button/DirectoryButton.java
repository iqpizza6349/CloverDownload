package com.github.iqpizza6349.cloverytdownloader.frame.component.button;

import com.github.iqpizza6349.cloverytdownloader.frame.component.text.TextInputField;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class DirectoryButton extends CustomButton {

    private final TextInputField inputField;

    public DirectoryButton(TextInputField inputField) {
        super();
        this.inputField = inputField;
        super.addActionListener(openFolder());
    }

    private ActionListener openFolder() {
        return e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showDialog(this, null);

            File directory = fileChooser.getSelectedFile();
            String path = (directory == null) ? "" : directory.getPath();
            if (path.length() > inputField.getColumns()) {
                path = path.substring(0, 57);
                path += "...";
            }

            inputField.setText(path);
        };
    }



}
