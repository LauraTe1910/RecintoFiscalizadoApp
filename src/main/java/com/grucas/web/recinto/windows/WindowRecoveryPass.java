/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.windows;

import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.regex.Pattern;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author Grucas
 */
public class WindowRecoveryPass extends Window {

    CssLayout content = new CssLayout();
    VerticalLayout cont = new VerticalLayout();
    Notification msgInfo = new Notification("Atencion!", "", Notification.Type.TRAY_NOTIFICATION);
    Notification msgError = new Notification("Error!", "", Notification.Type.ERROR_MESSAGE);
    Button btnEnviar = new Button("Enviar");
    Button btnCancelar = new Button("Cancelar");
    Button btnValidarEmail = new Button("Validar");
    TextField txtEmail = new TextField("Email:");
    Label lblResult = new Label("");

    public WindowRecoveryPass() {
        super("Recuperar Contraseña");
        initComponents();
    }

    public void initComponents() {
        msgInfo.setPosition(Position.BOTTOM_CENTER);
        msgInfo.setDelayMsec(4);
        msgError.setPosition(Position.BOTTOM_CENTER);
        msgError.setDelayMsec(4);

        content.setSizeFull();
        setContent(content);
        setHeight("280px");
        setWidth("500px");
        setResizable(false);

        txtEmail.setWidth("350px");
        txtEmail.setIcon(Fam3SilkIcon.EMAIL);
        txtEmail.focus();

//        btnCancelar.setImmediate(true);
//        btnEnviar.setImmediate(true);
        btnEnviar.setEnabled(false);

//        lblResult.setImmediate(true);
        btnCancelar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });

        btnValidarEmail.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (txtEmail.getValue().length() > 0) {
                    if (validEmail()) {
                        if (getEmailValid()) {
                            btnEnviar.setEnabled(true);
                            lblResult.setCaption("Excelente! Hemos identificado tu correo, presiona el boton Enviar.");
                            lblResult.setVisible(true);
                        } else {
                            lblResult.setCaption("No hemos encontrado el email en el sistema.");
                            lblResult.setVisible(true);
                            btnEnviar.setEnabled(false);
                        }
                    } else {
                        lblResult.setCaption("El email no es valido.");
                        lblResult.setVisible(true);
                        btnEnviar.setEnabled(false);
                    }
                } else {
                    lblResult.setCaption("Coloque un correo electronico valido para continuar.");
                    lblResult.setVisible(true);
                    btnEnviar.setEnabled(false);
                }
            }
        });

//        txtEmail.setImmediate(true);
//        txtEmail.setValidationVisible(true);
        btnEnviar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

            }
        });

        btnEnviar.setCaption("Enviar");
        btnCancelar.setCaption("Cancelar");
        btnValidarEmail.setCaption("Validar");
        btnEnviar.setIcon(Fam3SilkIcon.EMAIL_GO);
        btnCancelar.setIcon(Fam3SilkIcon.CANCEL);
        btnValidarEmail.setIcon(Fam3SilkIcon.ACCEPT);

        content.addComponent(cont);
        cont.setSpacing(true);
        cont.setMargin(true);
        cont.addComponents(txtEmail, lblResult, new HorizontalLayout(btnCancelar, btnValidarEmail, btnEnviar) {
            {
                setSpacing(true);
                setMargin(false);
            }
        });
        cont.setComponentAlignment(txtEmail, Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(2), Alignment.MIDDLE_CENTER);
    }

    public boolean validEmail() {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String value = txtEmail.getValue();

        if (Pattern.matches(EMAIL_PATTERN, value)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean getEmailValid() {

        return true;
    }

}
