package com.yongtay.ui;

import com.intellij.ui.ColorPanel;
import com.yongtay.store.SettingStore;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel {

    private JTextField startText;
    private ColorPanel colorPanel;
    private JCheckBox enableBtn;

    public SettingPanel() {
        this.init();
    }

    public void init() {
        int h  = 30;
        int w = 120;
        this.setLayout(null);
        startText = new JTextField();
        startText.setText(SettingStore.getStartComment());

        JLabel startTextLabel = new JLabel("设置注释开始标识");

        startTextLabel.setBounds(0, 0, w, h);
        startText.setBounds(startTextLabel.getWidth(), 0, 100, h);
        this.add(startTextLabel);
        this.add(startText);

        JLabel colorLabel = new JLabel("设置文字颜色");

        colorPanel = new ColorPanel();
        colorPanel.setEditable(true);
        colorPanel.setSelectedColor(SettingStore.getFontColor());

        colorLabel.setBounds(0, h, w, h);
        colorPanel.setBounds(colorLabel.getWidth(), h, 100, h);
        this.add(colorLabel);
        this.add(colorPanel);

        JLabel enableLabel = new JLabel("是否显示书签描述");
        enableLabel.setBounds(0, 2*h, w, h);
        enableBtn = new JCheckBox();
        enableBtn.setSelected(true);
        enableBtn.setBounds(enableLabel.getWidth(), 2*h, 100, h);
        this.add(enableLabel);
        this.add(enableBtn);
    }

    public void reset() {
        resetFontColor();
        resetStartText();
        resetEnable();
    }

    public String getStartText() {
        return startText.getText();
    }

    public void resetStartText() {
        startText.setText(SettingStore.getStartComment());
    }

    public Color getFontColor() {
        return colorPanel.getSelectedColor();
    }

    public void resetFontColor() {
        colorPanel.setSelectedColor(SettingStore.getFontColor());
    }

    public Boolean getEnable() {
        return enableBtn.isSelected();
    }

    public void resetEnable() {
        enableBtn.setSelected(SettingStore.getEnabled());
    }
}
