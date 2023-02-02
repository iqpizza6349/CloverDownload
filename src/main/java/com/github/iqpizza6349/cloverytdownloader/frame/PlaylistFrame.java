package com.github.iqpizza6349.cloverytdownloader.frame;

import com.github.iqpizza6349.cloverytdownloader.constant.ConfigMenuItem;
import com.github.iqpizza6349.cloverytdownloader.core.PlaylistRange;
import com.github.iqpizza6349.cloverytdownloader.frame.component.slider.PlaylistSlider;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ComponentUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PlaylistFrame extends JFrame {

    private final PlaylistSlider playlistSlider;

    public PlaylistFrame(JFrame mainFrame) {
        this.playlistSlider = new PlaylistSlider();

        setTitle("playlist settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        add(playlistPanel());

        addWindowListener(disposeEvent());

        pack();
        setSize(230, 100);
        setResizable(false);
        setLocationRelativeTo(mainFrame);

        setVisible(true);
    }

    private JPanel playlistPanel() {
        final JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));
        container.add(playlistSlider);
        return container;
    }

    private WindowAdapter disposeEvent() {
        return new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                if (e.getWindow() != getInstance()) {
                    return;
                }

                ComponentUtil.returnMenuItem(ConfigMenuItem.PLAYLIST);
                PlaylistRange.getInstance().setRange(playlistSlider.getValue(),
                        playlistSlider.getUpperValue());
            }
        };
    }

    private PlaylistFrame getInstance() {
        return this;
    }

}
