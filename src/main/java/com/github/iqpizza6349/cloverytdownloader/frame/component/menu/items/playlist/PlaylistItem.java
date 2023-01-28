package com.github.iqpizza6349.cloverytdownloader.frame.component.menu.items.playlist;

import com.github.iqpizza6349.cloverytdownloader.constant.ConfigMenuItem;
import com.github.iqpizza6349.cloverytdownloader.frame.PlaylistFrame;
import com.github.iqpizza6349.cloverytdownloader.frame.component.menu.items.ConfigItem;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ComponentUtil;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PlaylistItem extends ConfigItem {

    public PlaylistItem(JFrame parentFrame) {
        super(ConfigMenuItem.PLAYLIST);
        super.addActionListener(openPlaylistOptions(parentFrame));
    }

    private ActionListener openPlaylistOptions(JFrame parentFrame) {
        return (event) -> {
            if (event.getSource() != getInstance()
                    || !ComponentUtil.isAvailableMenuItem(getIdentity())) {
                return;
            }

            new PlaylistFrame(parentFrame);
            ComponentUtil.useAvailableMenuItem(getIdentity());
        };
    }
}
