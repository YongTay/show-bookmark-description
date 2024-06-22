package com.yongtay.store;

import com.intellij.ui.JBColor;
import com.jgoodies.common.base.Strings;

import java.awt.*;
import java.util.Objects;

public class SettingStore {
    public static final String START_COMMENT = "//";
    public static final Color FONT_COLOR = JBColor.orange;
    public static final boolean ENABLED = Boolean.TRUE;

    public static String startComment = START_COMMENT;
    public static Color fontColor = FONT_COLOR;
    public static Boolean enable = ENABLED;

    static {
        MyStoreService.State store = MyStoreService.settings();
        SettingStore.enable = store.enable;
        SettingStore.startComment = store.startComment;
        SettingStore.fontColor = store.fontColor;
    }

    public static String getStartComment()  {
        if(Strings.isBlank(startComment)) {
            return START_COMMENT;
        }
        return startComment;
    }

    public static Color getFontColor() {
        if(Objects.isNull(fontColor)) {
            return FONT_COLOR;
        }
        return fontColor;
    }

    public static Boolean getEnabled() {
        if(Objects.isNull(enable)) {
            return ENABLED;
        }
        return enable;
    }
}
