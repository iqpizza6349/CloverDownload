package com.github.iqpizza6349.cloverytdownloader.frame.component.text;

import com.github.iqpizza6349.cloverytdownloader.frame.component.CustomComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextInputField extends TextField implements CustomComponent {

    public TextInputField(String initializeText) {
        this(initializeText, 60);
    }

    public TextInputField(String initializeText, int columns) {
        super(initializeText, columns);
        setName(getHexHashCode());
        setPreferredSize(new Dimension(14, 40));
        addMouseListener(clickEvent());
    }

    private MouseAdapter clickEvent() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component eventComponent = e.getComponent();
                if (!(eventComponent instanceof TextInputField)) {
                    return;
                }

                TextInputField inputField = (TextInputField) eventComponent;
                inputField.setText("");
            }
        };
    }
}
