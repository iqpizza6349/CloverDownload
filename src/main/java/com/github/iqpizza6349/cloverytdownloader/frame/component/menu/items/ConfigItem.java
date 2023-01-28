package com.github.iqpizza6349.cloverytdownloader.frame.component.menu.items;

import com.github.iqpizza6349.cloverytdownloader.constant.ConfigMenuItem;

public class ConfigItem extends EnumMenuItem<ConfigMenuItem> {

    private final ConfigMenuItem identity;

    public ConfigItem(ConfigMenuItem menuItem) {
        super(menuItem.getName());
        identity = menuItem;
    }

    protected ConfigItem getInstance() {
        return this;
    }

    public ConfigMenuItem getIdentity() {
        return identity;
    }
}
