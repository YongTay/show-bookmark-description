package com.yongtay.ui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.tools.SimpleActionGroup;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.panels.HorizontalLayout;
import com.intellij.ui.components.panels.VerticalLayout;
import com.intellij.ui.content.Content;
import com.intellij.ui.tree.ui.DefaultTreeUI;
import com.intellij.ui.treeStructure.Tree;
import com.vladsch.flexmark.formatter.internal.CoreNodeFormatter;
import com.yongtay.domain.BookmarkX;
import kotlinx.html.VAR;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class ToolWindowPanel implements ToolWindowFactory {
    private Project project;
    private ToolWindow tw;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        this.project = project;
        this.tw = toolWindow;
        toolWindow.getContentManager().addContent(createContent());
    }

    public Content createContent() {
        JPanel container = new JPanel(new HorizontalLayout(1));
        container.add(createLeftPanel());
        container.add(crateLeftPanel());
        Content content = tw.getContentManager().getFactory().createContent(container, "", false);
        content.setCloseable(false);
        return content;
    }

    public Component createLeftPanel() {
        SimpleToolWindowPanel panel = new SimpleToolWindowPanel(true);
        panel.setBorder(BorderFactory.createMatteBorder(0,0,0,1, JBColor.border()));
        panel.setPreferredSize(new Dimension(150, 0));

        DefaultActionGroup actionOp = new DefaultActionGroup();
        actionOp.add(new AnAction("新增", "新增文件夹", AllIcons.General.Add) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {

            }
        });
        actionOp.add(new AnAction("删除", "删除文件夹", AllIcons.General.Remove) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {

            }
        });
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("dirOp", actionOp, true);
        Box toolbarbox = Box.createVerticalBox();
        toolbarbox.add(toolbar.getComponent());
        toolbarbox.setBorder(BorderFactory.createMatteBorder(0,0,1,0, JBColor.border()));
        panel.setToolbar(toolbarbox);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new BookmarkX("/"));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        Tree tree = new Tree();
        DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
        render.setLeafIcon(AllIcons.Nodes.Folder);
        tree.setRowHeight(24);

        tree.setCellRenderer(render);
        tree.setModel(new DefaultTreeModel(root));
        tree.setRootVisible(false);
        DefaultTreeSelectionModel selectionModel = new DefaultTreeSelectionModel();
        selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setSelectionModel(selectionModel);
        tree.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TreePath selectionPath = tree.getSelectionPath();
                Object[] path = selectionPath.getPath();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path[path.length - 1];
                BookmarkX row = (BookmarkX) node.getUserObject();
                System.out.println(row);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.add(tree);
        return panel;
    }

    public Component crateLeftPanel() {
        JPanel panel = new JPanel(new VerticalLayout(1));
        return panel;
    }

}
