package com.github.iqpizza6349.cloverytdownloader.frame;

import com.github.iqpizza6349.cloverytdownloader.frame.component.button.DirectoryButton;
import com.github.iqpizza6349.cloverytdownloader.frame.component.text.TextInputField;
import com.github.iqpizza6349.cloverytdownloader.frame.container.DirectoryContainer;
import com.github.iqpizza6349.cloverytdownloader.frame.util.IniData;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ResourceUtil;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Runnable {

    @Override
    public void run() {
        // JFrame 을 구성해야한다.
        // 비동기 progress bar 가 동작해야한다.
        init();
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
        topContainer.add(setDirectoryPanel());


        pack();
        setSize(540, 360);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private DirectoryContainer setDirectoryPanel() {
        DirectoryContainer container = new DirectoryContainer();
        TextInputField inputField = new TextInputField("파일을 다운받을 저장 공간을 입력해주세요.");
        DirectoryButton directoryButton = new DirectoryButton(inputField);

        container.add(inputField);
        container.add(directoryButton);

        return container;
    }

}
