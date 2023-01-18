package com.github.iqpizza6349.cloverytdownloader.frame.component.button;

import com.github.iqpizza6349.cloverytdownloader.frame.component.CustomComponent;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ResourceUtil;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton implements CustomComponent {

    public CustomButton() {
        super(new ImageIcon(ResourceUtil.getFolderImage()));
        setName(getHexHashCode());
        setPreferredSize(new Dimension(28, 28));
    }

    

}
