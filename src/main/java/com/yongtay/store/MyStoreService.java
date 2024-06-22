package com.yongtay.store;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service
@State(
        name = "settings",
        storages = @Storage(StoragePathMacros.CACHE_FILE)
)
public final class MyStoreService implements PersistentStateComponent<SettingState> {

    private SettingState myState = new SettingState();

    public static SettingState settings() {
        return ApplicationManager.getApplication().getService(MyStoreService.class).getState();
    }


    @Override
    public @Nullable SettingState getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull SettingState state) {
        myState = state;
    }

    public static void store(SettingStore store) {
        SettingState settings = settings();
        settings.setEnable(store.getEnable());
        settings.setStartComment(store.getStartComment());
        settings.setFontColor(store.getFontColor());
    }
}
