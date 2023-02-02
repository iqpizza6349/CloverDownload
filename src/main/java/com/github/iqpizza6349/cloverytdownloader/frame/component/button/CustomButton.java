package com.github.iqpizza6349.cloverytdownloader.frame.component.button;

import com.github.iqpizza6349.cloverytdownloader.CloverComponent;
import com.github.iqpizza6349.cloverytdownloader.frame.component.CustomComponent;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton implements CustomComponent, CloverComponent {

    public CustomButton(Icon icon) {
        super(icon);
        init();
    }

    public CustomButton(String text) {
        super(text);
        init();
    }

    private void init() {
        setName(getHexHashCode());
        setPreferredSize(new Dimension(28, 28));
    }

}
