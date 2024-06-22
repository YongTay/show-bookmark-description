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
    SettingStore store = SettingStore.settingStore;

    @Override
    public @Nullable JComponent createComponent() {
        return settings;
    }

    @Override
    public boolean isModified() {
        return store.isModify(settings);
    }

    @Override
    public void apply() throws ConfigurationException {
        store.setStartComment(settings.getStartComment());
        store.setEnable(settings.getEnable());
        store.setFontColor(settings.getFontColor());

        ApplicationManager.getApplication().invokeLater(() -> {
            MyStoreService.store(store);
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
