/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vaadin.components;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

/**
 *
 * @author GrucasDev
 */
public class SelectableWindow extends CustomComponent {

    private Object object;
    private String value;
    private Button btnOpenWindow;
    private TextField txtField;
    private Label lblCaption;

    public SelectableWindow(String txtCaption, String width) {
        object = null;
        value = "";
        txtField = new TextField();
        lblCaption = new Label(txtCaption);
        btnOpenWindow = new Button("...");
        txtField.setWidth(width);
        HorizontalLayout layout = new HorizontalLayout();
        layout.setMargin(false);
        layout.setSpacing(true);
        layout.addComponents(lblCaption, txtField, btnOpenWindow);
        layout.setComponentAlignment(lblCaption, Alignment.TOP_LEFT);
        layout.setComponentAlignment(txtField, Alignment.TOP_LEFT);
        layout.setComponentAlignment(btnOpenWindow, Alignment.TOP_LEFT);
        setCompositionRoot(layout);
        txtField.setReadOnly(true);

    }

    public Button getBtnOpenWindow() {
        return btnOpenWindow;
    }

    public void setBtnOpenWindow(Button btnOpenWindow) {
        this.btnOpenWindow = btnOpenWindow;
    }

    public TextField getTxtField() {
        return txtField;
    }

    public void setTxtField(TextField txtField) {
        this.txtField = txtField;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        txtField.setReadOnly(false);
        this.value = value;
        txtField.setValue(value);
        txtField.setReadOnly(true);
    }

    public void setNullAll() {
        object = null;
        value = "";
        txtField.setValue("");
    }

}
