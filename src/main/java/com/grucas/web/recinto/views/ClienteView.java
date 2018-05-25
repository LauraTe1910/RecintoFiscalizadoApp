/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.views;

import com.grucas.domain.model.Usuario;
import com.grucas.recinto.domain.ClienteService;
import com.grucas.recinto.model.Cliente;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;
import com.grucas.web.recinto.fragments.FragmentTop;
import com.grucas.web.recinto.windows.ClienteWindow;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GrucasDev
 */
public class ClienteView extends Panel implements View {
    Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    public static final String NAME = "CLIENTE";
    VerticalLayout container = new VerticalLayout();

    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Grid<Cliente> gridCliente = new Grid<>();
    List<Cliente> listCliente = new ArrayList<>();

    public ClienteView() {
        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(btnAdd, btnModify);

        Label lblTitulo = new Label("CLIENTES") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                gridCliente);

        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_LEFT);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);

    }

    public void initComponents() {
        setSizeFull();

        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");

        gridCliente.setItems(getCliente());
        gridCliente.setSizeFull();
        gridCliente.setHeight("350px");
        gridCliente.setSelectionMode(SelectionMode.SINGLE);

        gridCliente.addColumn(Cliente::getNombre).setCaption("NOMBRE").setId("NOMBRE");
        gridCliente.addColumn(Cliente::getTipo).setCaption("TIPO").setId("TIPO");
        gridCliente.addColumn(Cliente::getRazon_social).setCaption("RAZON SOCIAL").setId("RAZON SOCIAL");
        gridCliente.addColumn(Cliente::getRfc).setCaption("RFC").setId("RFC");
//        gridCliente.addColumn(Cliente::getDomicilio).setCaption("DOMICILIO").setId("DOMICILIO");
//        gridCliente.addColumn(Cliente::getCiudad).setCaption("CIUDAD").setId("CIUDAD");
//        gridCliente.addColumn(Cliente::getEstado).setCaption("ESTADO").setId("ESTADO");
//        gridCliente.addColumn(Cliente::getCp).setCaption("CP").setId("CP");
        gridCliente.addColumn(Cliente::getTel).setCaption("TEL").setId("TEL");
//        gridCliente.addColumn(Cliente::getRecepcion_factura).setCaption("RECEPCION FACTURAA").setId("RECEPCION FACTURA");
//        gridCliente.addColumn(Cliente::getReferencia_factura).setCaption("REFERENCIA FACTURA").setId("REFERENCIA FACTURA");
//        gridCliente.addColumn(Cliente::getAdicional_factura).setCaption("ADICIONAL FACTURA").setId("ADICIONAL FACTURA");
        gridCliente.addColumn(Cliente::getCorreo).setCaption("CORREO").setId("CORREO");

        Column<Cliente, String> rFactura = gridCliente.addColumn(det -> ((det.getRecepcion_factura() != null) ? dt.format(det.getRecepcion_factura()) : ""));
        rFactura.setCaption("RECEPCION FACTURA");
        rFactura.setId("RECEPCION FACTURA");

        gridCliente.addItemClickListener((evt) -> {
            if (evt.getMouseEventDetails().isDoubleClick()) {
                ClienteWindow windows = new ClienteWindow(evt.getItem());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridCliente.setItems(getCliente());
                    }
                });
                getUI().addWindow(windows);
            }
        });

        btnAdd.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                ClienteWindow windows = new ClienteWindow();
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridCliente.setItems(getCliente());
                    }
                });
                getUI().addWindow(windows);
            }
        });

        btnModify.addClickListener(event -> {

            if (gridCliente.getSelectedItems().size() == 1) {
                ClienteWindow windows = new ClienteWindow(gridCliente.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridCliente.setItems(getCliente());
                    }
                });
                getUI().addWindow(windows);

            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener un Cliente seleccionado para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getCliente() {
        ClienteService service = new ClienteService();
        service.getCliente("", "", " id ");
        listCliente = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listCliente;

    }
}
