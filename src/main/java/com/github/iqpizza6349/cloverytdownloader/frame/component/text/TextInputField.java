package com.github.iqpizza6349.cloverytdownloader.frame.component.text;

import com.github.iqpizza6349.cloverytdownloader.frame.component.CustomComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextListener;
import java.util.function.Consumer;

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

    private TextListener textListeningEvent(Consumer<TextComponent> action) {
        return (event) -> {
            Object eventComponent = event.getSource();
            if (!(eventComponent instanceof TextInputField)) {
                return;
            }

            TextInputField inputField = (TextInputField) eventComponent;
            if (!(inputField.getName().equals(getHexHashCode()))) {
                return;
            }

            // TODO: 2023-01-18 작동해야하는 순수 코드 (Consumer)
            action.accept(inputField);
        };
    }

    public void addTextListeningEvent(Consumer<TextComponent> action) {
        this.addTextListener(textListeningEvent(action));
    }




}
