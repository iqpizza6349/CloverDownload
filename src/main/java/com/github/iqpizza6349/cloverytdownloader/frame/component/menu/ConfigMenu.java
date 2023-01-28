package com.github.iqpizza6349.cloverytdownloader.frame.component.menu;

import com.github.iqpizza6349.cloverytdownloader.frame.component.menu.items.playlist.PlaylistItem;
import com.github.iqpizza6349.cloverytdownloader.frame.util.ComponentUtil;

import javax.swing.*;

import java.util.List;

import static com.github.iqpizza6349.cloverytdownloader.constant.ConfigMenuItem.*;

public class ConfigMenu extends JMenu {

    public ConfigMenu(JFrame frame) {
        super("Configuration");
        addMenuItems(frame);
        // after add menu-items
        ComponentUtil.initializesMenuItems(List.of(values()));
    }

    private void addMenuItems(JFrame frame) {
        add(new PlaylistItem(frame));
    }
}
