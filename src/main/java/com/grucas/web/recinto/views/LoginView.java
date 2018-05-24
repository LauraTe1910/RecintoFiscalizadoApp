/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.views;

import com.grucas.domain.services.UsuarioService;
import com.grucas.web.recinto.ConfigApp;
import com.grucas.web.recinto.windows.WindowRecoveryPass;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import de.steinwedel.messagebox.MessageBox;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class LoginView extends VerticalLayout implements View {

    public static final String NAME = "LOGIN";
    
    VerticalLayout loginPanel = new VerticalLayout();
    Button btnRecuperarPass = new Button("¿Olvidaste tu contraseña?");
    
    public LoginView() {
        setSizeFull();

        Image logoGrucas = new Image(null, new ThemeResource("grucas_logistica.png"));
        logoGrucas.setWidth("400px");
        
        CssLayout labels = new CssLayout();
        labels.addComponents(new Label("R E C I N T O  .  F I S C A L I Z A D O"){{addStyleName(ValoTheme.LABEL_HUGE);}});
        
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);

        TextField txtUser = new TextField("Usuario:");
        txtUser.setIcon(Fam3SilkIcon.USER);
        txtUser.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        txtUser.focus();
        txtUser.setMaxLength(12);

        final PasswordField txtPass = new PasswordField("Contraseña:");
        txtPass.setIcon(Fam3SilkIcon.LOCK);
        txtPass.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        txtPass.setMaxLength(10);

        final Button btnAceptar = new Button("Entrar");
        btnAceptar.addStyleName(ValoTheme.BUTTON_DANGER);
        btnAceptar.setIcon(Fam3SilkIcon.DOOR_IN);
        btnAceptar.focus();

        fields.addComponents(txtUser, txtPass, btnAceptar);
        fields.setComponentAlignment(btnAceptar, Alignment.BOTTOM_LEFT);

        btnRecuperarPass.addClickListener(ev -> {
            WindowRecoveryPass windows = new WindowRecoveryPass();
            windows.center();
            windows.setModal(true);
            windows.addCloseListener((Window.CloseEvent e) -> {
            });
            getUI().addWindow(windows);
        });

        btnAceptar.addClickListener( ev -> {
            
            if (txtUser.getValue().length() == 0 || txtPass.getValue().length() == 0) {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe colocar un nombre de usuario y contraseña validos.")
                        .withRetryButton()
                        .open();
            } else {
                
                UsuarioService service = new UsuarioService();
                service.login(txtUser.getValue(), txtPass.getValue(), ConfigApp.APLICATION_CODE);
                if (service.getOk() && service.getObject() != null) {
                    
                    MessageBox.createInfo()
                            .withCaption("Bienvenido!")
                            .withMessage(service.getNotification())
                            .open();
                    txtUser.setValue("");
                    txtPass.setValue("");
                    txtUser.focus();
                    
                    VaadinSession.getCurrent().getSession().setAttribute("USUARIO_ACTIVO", service.getObject());

                    getUI().getNavigator().navigateTo(MainView.NAME);
                    
                } else {
                    MessageBox.createError()
                            .withCaption("-Error: ")
                            .withMessage(service.getNotification())
                            .withRetryButton()
                            .open();
                    txtUser.setValue("");
                    txtPass.setValue("");
                    txtUser.focus();
                }
            }
        });
        
        
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        loginPanel.addComponents(logoGrucas, labels, fields, btnRecuperarPass);
        loginPanel.setComponentAlignment(logoGrucas, Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(loginPanel.getComponent(1), Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(loginPanel.getComponent(2), Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(loginPanel.getComponent(3), Alignment.MIDDLE_RIGHT);
        
        Responsive.makeResponsive(loginPanel);
        
        btnRecuperarPass.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);

        addComponent(loginPanel);
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        txtUser.focus();
    }
    
}