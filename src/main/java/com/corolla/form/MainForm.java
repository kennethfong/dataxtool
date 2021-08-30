package com.corolla.form;

import com.corolla.model.Datasource;
import com.corolla.model.Datasources;
import com.corolla.util.DatasourceUtil;
import com.thoughtworks.xstream.XStream;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

@Getter
public class MainForm {
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton addDsBtn;
    private JPanel outerPanel;
    private JTable table1;



    public MainForm() {
        try {
            DatasourceUtil.init();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "数据源初始化失败：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }

        addDsBtn.addActionListener(e -> {
            JFrame frame = new JFrame("CreateDatasourceForm");
            frame.setContentPane(new DatasourceForm().getOuterPanel());
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        });
    }




}
