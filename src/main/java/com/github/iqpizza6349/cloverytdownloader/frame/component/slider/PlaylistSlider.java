package com.github.iqpizza6349.cloverytdownloader.frame.component.slider;

import com.github.iqpizza6349.cloverytdownloader.frame.component.CustomComponent;

public class PlaylistSlider extends RangeSlider implements CustomComponent {

    public PlaylistSlider() {
        super(1, 25);
        super.setValue(1);
        super.setUpperValue(10);
        super.setMinorTickSpacing(1);
        super.setMajorTickSpacing(4);
        super.setPaintTicks(true);
        super.setPaintLabels(true);
        super.setVisible(true);
    }
}
