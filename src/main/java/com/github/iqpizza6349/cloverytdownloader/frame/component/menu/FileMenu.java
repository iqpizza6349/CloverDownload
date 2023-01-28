package com.github.iqpizza6349.cloverytdownloader.frame.component.menu;

import javax.swing.*;

public class FileMenu extends JMenu {

    public FileMenu() {
        super("File");
        addMenuItem();
    }

    private void addMenuItem() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener((event) -> {
            if (event.getSource() != exitItem) {
                return;
            }

            System.exit(0);
        });
        add(exitItem);
    }

}
