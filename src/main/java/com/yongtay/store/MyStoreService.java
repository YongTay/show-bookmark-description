package com.yongtay.store;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.annotations.OptionTag;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import com.yongtay.convert.ColorConverter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@State(
        name = "settings",
        storages = @Storage(StoragePathMacros.CACHE_FILE)
)
public final class MyStoreService implements PersistentStateComponent<MyStoreService.State> {

    private State myState = new State();

    public static State settings() {
        return ApplicationManager.getApplication().getService(MyStoreService.class).getState();
    }

    @Override
    public @Nullable MyStoreService.State getState() {
        if (Objects.nonNull(myState.rgbColor)) {
            myState.fontColor = new Color(myState.rgbColor.get("r"), myState.rgbColor.get("g"), myState.rgbColor.get("b"));
        }
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }

    public static void setFontColor(Color c) {
        State store = MyStoreService.settings();
        Map<String, Integer> rgb = new HashMap<>(3);
        rgb.put("r", c.getRed());
        rgb.put("g", c.getGreen());
        rgb.put("b", c.getBlue());
        store.rgbColor = rgb;
    }

    public static Color getFontColor() {
        State store = MyStoreService.settings();
        return new Color(store.rgbColor.get("r"), store.rgbColor.get("g"), store.rgbColor.get("b"));
    }

    public static class State {
        public String startComment;
        public Map<String, Integer> rgbColor;
        public Boolean enable;
        @OptionTag(converter = ColorConverter.class)
        public Color fontColor;
    }
}
