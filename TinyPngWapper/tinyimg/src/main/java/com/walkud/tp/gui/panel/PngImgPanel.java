package com.walkud.tp.gui.panel;

import com.walkud.tp.bean.ImageInfo;
import com.walkud.tp.common.CompressManager;
import com.walkud.tp.common.FileManager;
import com.walkud.tp.utils.FileUtil;
import com.walkud.tp.utils.MLog;
import com.walkud.tp.utils.StringUtil;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * UI面板
 * Created by Walkud on 16/5/12.
 */
public class PngImgPanel extends JPanel {

    private final String[] headers = {"文件名", "文件路径", "状态结果", "大小", "压缩后大小", "压缩率"};
    private JFrame parentFrame;
    private JTable table;
    private int useCount;//已使用次数值
    private JLabel useCountInfoLabel;//已压缩次数
    private JLabel imgAllCountLabel;//选择的图片总数
    private JLabel apiKeyLabel;//Apikey值
    private JLabel progressLabel;//压缩进度

    private JScrollPane scroolPane;

    private List<ImageInfo> imageInfos = new ArrayList<>();


    public PngImgPanel(JFrame jFrame) {
        super();
        this.parentFrame = jFrame;
        buildUI();
    }

    /**
     * 构建UI
     */
    private void buildUI() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        //设置ApiKey按钮布局
        JButton apiKeySetButton = buildApiKeySetButton();
        apiKeyLabel = buildJLabel();
        JPanel apiKeyPanel = new JPanel();
        apiKeyPanel.setLayout(new BorderLayout());
        apiKeyPanel.add(apiKeySetButton, BorderLayout.LINE_START);
        apiKeyPanel.add(apiKeyLabel, BorderLayout.CENTER);
        this.add(apiKeyPanel);

        //设置文件目录选择按钮布局
        JButton fileChooserBtn = buildFileChooserButton();
        JPanel fileChooserPanel = new JPanel();
        fileChooserPanel.setLayout(new BorderLayout());
        fileChooserPanel.add(fileChooserBtn, BorderLayout.LINE_START);

        //构建压缩开始按钮
        JButton compressButton = buildCompressButton();
        fileChooserPanel.add(compressButton, BorderLayout.LINE_END);

        this.add(fileChooserPanel);

        //设置Table布局
        table = buildTable(headers, null);
        scroolPane = new JScrollPane(table);
        this.add(scroolPane);


        //设置底部布局
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));

        //构建已压缩图片数Layout
        useCountInfoLabel = buildJLabel();
        bottomPanel.add(useCountInfoLabel);

        //构建已选择图片总数
        imgAllCountLabel = buildJLabel();
        bottomPanel.add(imgAllCountLabel);


        progressLabel = buildJLabel();
        bottomPanel.add(progressLabel);

        this.add(bottomPanel);
    }

    /**
     * 构建文本展现标签
     *
     * @return
     */
    private JLabel buildJLabel() {
        JLabel label = new JLabel();

        return label;
    }

    /**
     * 构建数据表
     *
     * @param headers
     * @param cellData
     * @return
     */
    private JTable buildTable(String[] headers, Object[][] cellData) {

        JTable table = new JTable(new DefaultTableModel(cellData, headers) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        // 设置表格调整尺寸模式
        table.setCellSelectionEnabled(false);
        // 设置单元格选择方式
        table.setShowVerticalLines(true);
        // 设置是否显示单元格间的分割线
        table.setShowHorizontalLines(true);

        //设置表头居中显示
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        //设置表数据居中显示
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, cr);
        return table;
    }

    private JButton buildApiKeySetButton() {
        JButton button = new JButton("设置ApiKey");
        button.addActionListener(e -> {
                    button.setEnabled(false);
                    String apiKey = JOptionPane.showInputDialog(parentFrame, "输入TinyPng ApiKey");
                    //获取ApiKey 验证是否为空
                    if (apiKey != null && apiKey.trim().length() > 0) {
                        apiKeyLabel.setText("验证中...");
                        useCountInfoLabel.setText("");
                        CompressManager.getInstance().setApiKeyAndValidate(apiKey, (isValidate, count) -> {
                            button.setEnabled(true);
                            if (isValidate) {
                                useCount = count;
                                MLog.p("count:" + count);
                                apiKeyLabel.setText("有效ApiKey:" + apiKey);
                                setUseCountText();
                            } else {
                                apiKeyLabel.setText("");
                                JOptionPane.showMessageDialog(parentFrame, "ApiKey 验证失败");
                            }
                        });
                    } else {
                        button.setEnabled(true);
                    }
                }
        );
        return button;
    }

    /**
     * 创建选择目录Button
     *
     * @return
     */
    private JButton buildFileChooserButton() {
        JButton button = new JButton("选择目录");
        button.addActionListener(e -> {
                    JFileChooser jfc = new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    // 限制文件类型
                    //去掉显示所有文件这个过滤器。
                    jfc.setAcceptAllFileFilterUsed(false);
                    jfc.addChoosableFileFilter(new FileManager.SelectFileFilter());
                    jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
                    int intRetVal = jfc.showOpenDialog(PngImgPanel.this);
                    if (intRetVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println(jfc.getSelectedFile().getAbsolutePath());
                        List<File> fileList = FileUtil.getFileList(jfc.getSelectedFile().getAbsolutePath());

                        updateDataUI(fileList);
                    }
                }
        );
        return button;
    }

    /**
     * 创建开始按钮
     *
     * @return
     */
    private JButton buildCompressButton() {
        JButton button = new JButton("开始");
        button.addActionListener(e -> {
                    button.setText("压缩中");
                    button.setEnabled(false);

                    progressLabel.setText("进度:0%");

                    CompressManager.getInstance().compress(imageInfos, new CompressManager.Callback() {

                        private volatile int count = 0;//完成个数
                        private volatile int pressCount = 0;//压缩个数

                        @Override
                        public void complete(int position, ImageInfo imageInfo) {
                            table.setValueAt(imageInfo.getStatus(), position, 2);
                            table.setValueAt(imageInfo.getCompressSize(), position, 4);
                            table.setValueAt(imageInfo.getCompressRate(), position, 5);

                            count++;

                            //判断是否已经压缩完成
                            if (count == imageInfos.size()) {
                                button.setText("开始");
                                button.setEnabled(true);
                            }

                            if ("成功".equals(imageInfo.getStatus())) {
                                pressCount++;
                                useCount += pressCount;
                                setUseCountText();
                            }
                            progressLabel.setText("进度:" + (int) ((double) count / imageInfos.size() * 100) + "%");
                        }

                        @Override
                        public void startStatus(int position, String status) {
                            table.setValueAt(status, position, 2);
                        }
                    });
                }

        );

        return button;
    }


    /**
     * 绑定选择的文件
     */
    private void updateDataUI(List<File> fileList) {
        imageInfos.clear();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.getDataVector().clear
                ();
        for (File file : fileList) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.setFileName(file.getName());
            imageInfo.setPath(file.getAbsolutePath());
            imageInfo.setFileSize(file.length());
            imageInfo.setSize(StringUtil.converFileSize(file.length()));
            String[] data = new String[headers.length];
            data[0] = imageInfo.getFileName();
            data[1] = imageInfo.getPath();
            data[3] = imageInfo.getSize();
            model.addRow(data);
            imageInfos.add(imageInfo);
        }
        model.fireTableDataChanged();
        imgAllCountLabel.setText("已选择图片数：" + imageInfos.size());
    }

    private void setUseCountText() {
        useCountInfoLabel.setText("当月已压缩次数：" + useCount);
    }

}
