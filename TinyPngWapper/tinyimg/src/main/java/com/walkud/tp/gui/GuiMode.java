package com.walkud.tp.gui;

import com.walkud.tp.bean.ArgValue;
import com.walkud.tp.gui.panel.PngImgPanel;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 * 界面模式
 * Created by Walkud on 16/5/12.
 */
public class GuiMode {

    public static void with(final Map<String, ArgValue> argValues) {
        javax.swing.SwingUtilities.invokeLater(() -> buildAndShowTinyImg(argValues));
    }

    private static void buildAndShowTinyImg(Map<String, ArgValue> argValues) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | ClassNotFoundException
                | InstantiationException | SecurityException ex) {
            ex.printStackTrace();
        }

        JFrame frame = buildTinyImgFrame(argValues);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static JFrame buildTinyImgFrame(Map<String, ArgValue> argValues) {
        JFrame rootJFrame = new JFrame("TinyImg");

        rootJFrame.getContentPane().add(new PngImgPanel(rootJFrame));

        rootJFrame.setSize(700, 500);
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); // 获得显示器大小对象
        Dimension frameSize = rootJFrame.getSize();             // 获得窗口大小对象
        if (frameSize.width > displaySize.width)
            frameSize.width = displaySize.width;           // 窗口的宽度不能大于显示器的宽度

        rootJFrame.setLocation((displaySize.width - frameSize.width) / 2,
                (displaySize.height - frameSize.height) / 2); // 设置窗口居中显示器显示
        return rootJFrame;
    }
}
