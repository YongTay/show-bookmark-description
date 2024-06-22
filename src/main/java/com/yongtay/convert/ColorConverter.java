package com.yongtay.convert;


import com.intellij.util.xmlb.Converter;
import com.jgoodies.common.base.Strings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;

public class ColorConverter extends Converter<Color> {
    @Override
    public @Nullable Color fromString(@NotNull String value) {
        if(Strings.isBlank(value)) {
            return null;
        }
        String[] rgb = value.split(",");
        if(rgb.length != 3) {
            return null;
        }
        return new Color(Integer.valueOf(rgb[0]), Integer.valueOf(rgb[1]), Integer.valueOf(rgb[2]));
    }

    @Override
    public @Nullable String toString(@NotNull Color value) {
        if(Objects.isNull(value)) {
            return "";
        }
        return value.getRed() + "," + value.getGreen() + "," + value.getBlue();
    }
}
