package com.yongtay.store;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.JBColor;
import com.yongtay.ui.SettingPanel;

import java.awt.*;
import java.util.Objects;

public class SettingStore extends SettingState{
    // 默认参数配置
    public static final String START_COMMENT = "//";
    public static final Color FONT_COLOR = JBColor.ORANGE;
    public static final boolean ENABLED = Boolean.TRUE;

    public static SettingStore settingStore = new SettingStore();

    private SettingStore() {
        // 获取混存的配置参数
        SettingState store = MyStoreService.settings();
        enable = store.enable;
        startComment = store.startComment;
        fontColor = store.fontColor;
    }

    public String getStartComment() {
        if(Objects.isNull(startComment)) {
            return START_COMMENT;
        }
        return startComment;
    }

    public Color getFontColor() {
        if(Objects.isNull(fontColor)) {
            return FONT_COLOR;
        }
        return fontColor;
    }

    public Boolean getEnable() {
        if(Objects.isNull(enable)) {
            return ENABLED;
        }
        return enable;
    }

    public Boolean isModify(SettingPanel settings) {
        return !(getEnable().equals(settings.getEnable())
                && getFontColor().equals(settings.getFontColor())
                && getStartComment().equals(settings.getStartComment()));
    }
}
