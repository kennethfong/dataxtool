package com.corolla.form;

import com.corolla.model.Datasource;
import com.corolla.model.Datasources;
import com.corolla.util.DatasourceUtil;
import com.corolla.util.DocumentUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;

import javax.sql.DataSource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class DatasourceForm {
    private JPanel outerPanel;
    private JComboBox dsTypeComboBox;
    private JTextField jdbcUrlTextField;
    private JTextField userTextField;
    private JTextField passTextField;
    private JButton cancelDsBtn;
    private JButton saveDsBtn;
    private JTextField nameTextField;



    public DatasourceForm() {
        this.saveDsBtn.addActionListener(e -> {
            try {
                DatasourceUtil.saveDatasource(createDatasource());
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null,
                        "保存数据源失败：" + ioe.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    private Datasource createDatasource() {
        Datasource entity = new Datasource();
        entity.setName(nameTextField.getText());
        entity.setType(dsTypeComboBox.getSelectedItem().toString());
        entity.setUrl(jdbcUrlTextField.getText());
        entity.setUsername(userTextField.getText());
        entity.setPassword(passTextField.getText());
        entity.setIndex(DatasourceUtil.generateIndex());
        return entity;
    }


}
