package com.yongtay.store;

import com.intellij.util.xmlb.annotations.OptionTag;
import com.yongtay.convert.ColorConverter;

import java.awt.*;

public class SettingState {
    public String startComment;
    public Boolean enable;
    @OptionTag(converter = ColorConverter.class)
    public Color fontColor;

    public String getStartComment() {
        return startComment;
    }

    public void setStartComment(String startComment) {
        this.startComment = startComment;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }
}
