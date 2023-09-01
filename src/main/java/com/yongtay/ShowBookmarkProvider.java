package com.yongtay;

import com.intellij.ide.bookmark.Bookmark;
import com.intellij.ide.bookmark.BookmarkGroup;
import com.intellij.ide.bookmark.BookmarksManager;
import com.intellij.openapi.editor.EditorLinePainter;
import com.intellij.openapi.editor.LineExtensionInfo;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.Font;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShowBookmarkProvider extends EditorLinePainter {
    @Override
    public @Nullable Collection<LineExtensionInfo> getLineExtensions(@NotNull Project project, @NotNull VirtualFile file, int lineNumber) {
        BookmarksManager bm = BookmarksManager.getInstance(project);
        List<Bookmark> bookmarks = bm.getBookmarks();
        for (Bookmark b : bookmarks) {
            String url = b.getAttributes().get("url");
            String line = b.getAttributes().get("line");
            if(file.getUrl().equals(url) && String.valueOf(lineNumber).equals(line)) {
                List<BookmarkGroup> groups = bm.getGroups(b);
                for (BookmarkGroup g : groups) {
                    String description = g.getDescription(b);
                    if(null != description && !description.isBlank()) {
                        return Collections.singleton(new LineExtensionInfo(" // ".concat(description), JBColor.ORANGE, null, null, Font.ITALIC));
                    }
                }
            }
        }
        return null;
    }
}
