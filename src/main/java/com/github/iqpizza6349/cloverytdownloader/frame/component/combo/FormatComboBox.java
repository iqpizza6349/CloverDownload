package com.github.iqpizza6349.cloverytdownloader.frame.component.combo;

import com.github.iqpizza6349.cloverytdownloader.constant.YoutubeFormat;

import javax.swing.*;

public class FormatComboBox extends JComboBox<YoutubeFormat> {

    public FormatComboBox() {
        super(YoutubeFormat.values());
    }
}
