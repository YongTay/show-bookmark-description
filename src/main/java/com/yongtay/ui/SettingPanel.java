package com.yongtay.ui;

import com.intellij.ui.ColorPanel;
import com.intellij.ui.components.panels.HorizontalLayout;
import com.intellij.ui.components.panels.VerticalLayout;
import com.yongtay.store.SettingStore;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel {

    private JTextField startText;
    private ColorPanel colorPanel;
    private JCheckBox enableBtn;

    SettingStore store = SettingStore.settingStore;

    public SettingPanel() {
        this.init();
    }

    public void init() {
        this.setLayout(new VerticalLayout(5));

        JLabel enableLabel = new JLabel("是否显示书签描述");
        enableBtn = new JCheckBox(enableLabel.getText());
        enableBtn.setSelected(store.getEnable());
        this.add(enableBtn);

        startText = new JTextField();
        startText.setText(store.getStartComment());
        int h = 30;
        int labelWidth = 150;
        JLabel startTextLabel = new JLabel("设置注释开始标识");
        startTextLabel.setPreferredSize(new Dimension(labelWidth, h));
        startText.setPreferredSize(new Dimension(100, h));
        JPanel row1 = new JPanel(new HorizontalLayout(5));
        row1.add(startTextLabel);
        row1.add(startText);
        this.add(row1);

        JPanel row2 = new JPanel(new HorizontalLayout(5));
        JLabel colorLabel = new JLabel("设置文字颜色");
        colorPanel = new ColorPanel();
        colorPanel.setEditable(true);
        colorPanel.setSelectedColor(store.getFontColor());
        colorLabel.setPreferredSize(new Dimension(labelWidth, h));
        colorPanel.setPreferredSize(new Dimension(100, h));
        row2.add(colorLabel);
        row2.add(colorPanel);
        this.add(row2);

    }

    public void reset() {
        resetEnable();
        resetStartComment();
        resetFontColor();
    }

    public String getStartComment() {
        return startText.getText();
    }

    public void resetStartComment() {
        startText.setText(store.getStartComment());
    }

    public Color getFontColor() {
        return colorPanel.getSelectedColor();
    }

    public void resetFontColor() {
        colorPanel.setSelectedColor(store.getFontColor());
    }

    public Boolean getEnable() {
        return enableBtn.isSelected();
    }

    public void resetEnable() {
        enableBtn.setSelected(store.getEnable());
    }
}
