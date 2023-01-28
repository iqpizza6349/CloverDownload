package com.github.iqpizza6349.cloverytdownloader.frame.component.menu.items;

import javax.swing.*;

public class EnumMenuItem<T extends Enum<T>> extends JMenuItem {

    public EnumMenuItem(T t) {
        super(t.name().toLowerCase());
    }

    public EnumMenuItem(String name) {
        super(name);
    }
}
