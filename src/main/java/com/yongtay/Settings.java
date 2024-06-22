package com.yongtay;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import com.yongtay.store.MyStoreService;
import com.yongtay.store.SettingStore;
import com.yongtay.ui.SettingPanel;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class Settings implements Configurable {

    SettingPanel settings = new SettingPanel();

    @Override
    public @Nullable JComponent createComponent() {
        MyStoreService.State store = MyStoreService.settings();
        SettingStore.enable = store.enable;
        SettingStore.startComment = store.startComment;
        SettingStore.fontColor = store.fontColor;
        return settings;
    }

    @Override
    public boolean isModified() {
        return !SettingStore.getStartComment().equals(settings.getStartText())
                || !SettingStore.getFontColor().equals(settings.getFontColor())
                || !SettingStore.getEnabled().equals(settings.getEnable());
    }

    @Override
    public void apply() throws ConfigurationException {
        SettingStore.startComment = settings.getStartText();
        SettingStore.fontColor = settings.getFontColor();
        SettingStore.enable = settings.getEnable();

        ApplicationManager.getApplication().invokeLater(() -> {
            MyStoreService.State store = MyStoreService.settings();
            store.enable = settings.getEnable();
            MyStoreService.setFontColor(settings.getFontColor());
            store.startComment = settings.getStartText();
        });
    }

    @Override
    public void reset() {
        settings.reset();
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "ShowBookmark Settings";
    }
}
