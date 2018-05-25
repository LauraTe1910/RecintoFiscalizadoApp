/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.fragments;

import com.grucas.domain.model.Usuario;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.grucas.web.recinto.views.ClienteView;
import com.grucas.web.recinto.views.EmbarcadorView;
import com.grucas.web.recinto.views.ProductoView;
import com.grucas.web.recinto.views.SolicitudDeServicioView;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class FragmentTop extends HorizontalLayout {

    MenuBar menubar = new MenuBar();
    Label lblInfoSession;
    Usuario usuario;

    public FragmentTop() {

        setHeight("37px");
        setWidth("100%");

        usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

        Image logo = new Image(null, new ThemeResource("favicon.jpg"));
        logo.setHeight("37px");
        Image profilePic = new Image(null, new ThemeResource("user.png"));
        profilePic.setWidth("37px");

        HorizontalLayout rolePanel = new HorizontalLayout();

        Label lblusuario = new Label();  //usuario.getNombre() + "   |   " + usuario.getRol()+ "  "
        rolePanel.addComponents(profilePic, lblusuario);
        rolePanel.setComponentAlignment(lblusuario, Alignment.MIDDLE_CENTER);

        MenuBar.Command actionMenu = (MenuBar.MenuItem selectedItem) -> {
            
            switch (selectedItem.getText()) {
                
                case "Cliente":{
                    getUI().getNavigator().navigateTo(ClienteView.NAME);
                    break;
                }
                case "Embarcador":{
                    getUI().getNavigator().navigateTo(EmbarcadorView.NAME);
                    break;
                }
                case "Producto":{
                    getUI().getNavigator().navigateTo(ProductoView.NAME);
                    break;
                }
                case "Servicio":{
                    getUI().getNavigator().navigateTo(SolicitudDeServicioView.NAME);
                    break;
                }
                default:{
                    break;
                }
            }
        };

           // Menu Catalogos
        MenuBar.MenuItem menuCatalogos = menubar.addItem("Catalogos", null);
        menuCatalogos.setIcon(Fam3SilkIcon.BOOK);
        
        MenuBar.MenuItem subCliente = menuCatalogos.addItem("Cliente", actionMenu);
        subCliente.setIcon(Fam3SilkIcon.USER_SUIT);
        
        MenuBar.MenuItem subEmbarcador = menuCatalogos.addItem("Embarcador", actionMenu);
        subEmbarcador.setIcon(Fam3SilkIcon.USER);
        
        MenuBar.MenuItem subProducto = menuCatalogos.addItem("Producto", actionMenu);
        subProducto.setIcon(Fam3SilkIcon.PACKAGE);

        MenuBar.MenuItem menuOperaciones = menubar.addItem("Operaciones", null);
        menuOperaciones.setIcon(Fam3SilkIcon.PAGE_REFRESH);
        
        MenuBar.MenuItem servicio = menuOperaciones.addItem("Servicio", null, actionMenu);
        servicio.setIcon(Fam3SilkIcon.OVERLAYS);
        
        MenuBar.MenuItem salir = menubar.addItem("Salir", null, actionMenu);
        salir.setIcon(Fam3SilkIcon.DOOR_OUT);

        addComponents(new HorizontalLayout(logo, menubar) {
            {
            }
        }, rolePanel);
        setSpacing(true);
        setComponentAlignment(rolePanel, Alignment.MIDDLE_RIGHT);

    }
}
