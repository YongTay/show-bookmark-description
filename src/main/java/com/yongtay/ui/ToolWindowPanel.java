package com.yongtay.ui;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.treeView.NodeRenderer;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.tools.SimpleActionGroup;
import com.intellij.ui.JBColor;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.components.JBScrollPane;
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
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
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
        JSplitPane container = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        container.add(createLeftPanel());
        container.add(crateLeftPanel());
        container.setBorder(null);
        container.setDividerSize(1);
        BasicSplitPaneUI ui = (BasicSplitPaneUI) container.getUI();
        ui.getDivider().setBorder(BorderFactory.createLineBorder(JBColor.border()));
        container.setContinuousLayout(true);
        Content content = tw.getContentManager().getFactory().createContent(container, "", false);
        content.setCloseable(false);
        return content;
    }

    public Component createLeftPanel() {
        SimpleToolWindowPanel panel = new SimpleToolWindowPanel(true);
        panel.setPreferredSize(new Dimension(200, 0));

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
        actionOp.add(new AnAction("编辑", "编辑文件夹", AllIcons.Actions.Edit) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e) {

            }
        });
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("dirOp", actionOp, true);
        Box toolbarbox = Box.createVerticalBox();
        toolbar.setTargetComponent(toolbarbox);
        toolbarbox.add(toolbar.getComponent());
        toolbarbox.setBorder(BorderFactory.createMatteBorder(0,0,1,0, JBColor.border()));
        panel.setToolbar(toolbarbox);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new BookmarkX("/"));
        root.add(new DefaultMutableTreeNode(new BookmarkX("spring 点击历史")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        root.add(new DefaultMutableTreeNode(new BookmarkX("节点1")));
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(new BookmarkX("节点2"));
        node2.add(new DefaultMutableTreeNode(new BookmarkX("2-1")));
        root.add(node2);

        Tree tree = new Tree();
        tree.setRowHeight(24);
        tree.setCellRenderer((tree1, value, selected, expanded, leaf, row, hasFocus) -> {
            Box box = Box.createHorizontalBox();
            box.add(new JLabel(AllIcons.Nodes.Folder));
            box.add(Box.createHorizontalStrut(5));
            box.add(new JLabel(String.valueOf(value)));
            return box;
        });
        tree.setModel(new DefaultTreeModel(root));
        tree.setRootVisible(false);
        DefaultTreeSelectionModel selectionModel = new DefaultTreeSelectionModel();
        selectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setSelectionModel(selectionModel);

        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] selections = tree.getSelectionPath().getPath();
                DefaultMutableTreeNode leafNode = (DefaultMutableTreeNode) selections[selections.length - 1];
                BookmarkX data = (BookmarkX) leafNode.getUserObject();
                System.out.println(data);
            }
        });

        JScrollPane scrollPane = ScrollPaneFactory.createScrollPane(tree);
        scrollPane.setBorder(null);
        panel.add(scrollPane);
        return panel;
    }

    public Component crateLeftPanel() {
        JPanel panel = new JPanel(new VerticalLayout(1));
        return panel;
    }

}
