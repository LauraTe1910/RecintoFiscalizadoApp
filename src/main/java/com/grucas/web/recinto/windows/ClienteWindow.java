/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.windows;

import com.grucas.recinto.domain.ClienteService;
import com.grucas.recinto.model.Cliente;
import com.rubik.model.Usuario;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;

/**
 *
 * @author GrucasDev
 */
public class ClienteWindow extends Window {

    Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    String title_window = "CLIENTE_WINDOW";
    VerticalLayout cont = new VerticalLayout();
    public Cliente cliente = new Cliente();
    public Boolean isEdit = false;

    TextField txtNombre = new TextField("Nombre:");
    NativeSelect<String> cboTipo = new NativeSelect("Tipo:");
    TextField txtRazon = new TextField("Razon Social:");
    TextField txtRFC = new TextField("RFC:");
    TextField txtDomicilio = new TextField("Domicilio:");
    TextField txtCiudad = new TextField("Ciudad:");
    TextField txtEstado = new TextField("Estado:");
    TextField txtCp = new TextField("Cp:");
    TextField txtTel = new TextField("Telefono:");
    TextField txtCorreo = new TextField("Correo:");
    DateField txtRecepcion = new DateField("Recepcion Factura:");
    DateField txtReferencia = new DateField("Referencia Factura:");
    TextField txtAdicional = new TextField("Adicional Factura:");
    Label title;
    FormLayout form = new FormLayout();
    FormLayout form2 = new FormLayout();

    public ClienteWindow() {
        setCaption("ALTA DE CLIENTES");
        initComponents();

    }

    public ClienteWindow(Cliente client) {
        setCaption("MODIFICACION DE CLIENTES");
        isEdit = true;
        this.cliente = client;
        initComponents();

    }

    public void initComponents() {
        setContent(cont);
        setHeight("515px");
        setWidth("830px");

        Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
        Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

        txtNombre.setWidth("250");
        txtRazon.setWidth("250");
        txtRFC.setWidth("250");
        txtDomicilio.setWidth("250");
        txtCiudad.setWidth("250");
        txtEstado.setWidth("250");
        txtCp.setWidth("250");
        txtTel.setWidth("250");
        txtCorreo.setWidth("250");
        txtRecepcion.setWidth("250");
        txtReferencia.setWidth("250");
        txtAdicional.setWidth("250");

        cboTipo.setItems("prueba1", "prueba2", "prueba3");
        cboTipo.setWidth("250");
        cboTipo.setEmptySelectionAllowed(false);

        form.addComponents(txtNombre, cboTipo, txtRazon, txtRFC, txtDomicilio, txtCiudad);
        form2.addComponents(txtEstado, txtCp, txtTel, txtCorreo, txtRecepcion, txtReferencia, txtAdicional);

        Binder<Cliente> binder = new Binder<>();

        binder.forField(txtNombre).withValidator(nombre -> nombre.length() >= 1, "El campo no puede estar vacio").bind(Cliente::getNombre, Cliente::setNombre);
        binder.forField(cboTipo).bind(Cliente::getTipo, Cliente::setTipo);
        binder.forField(txtRazon).withValidator(razon -> razon.length() >= 1, "El campo no puede estar vacio").bind(Cliente::getRazon_social, Cliente::setRazon_social);
        binder.forField(txtRFC).withValidator(rfc -> rfc.length() >= 1, "El campo no puede estar vacio").bind(Cliente::getRfc, Cliente::setRfc);
        binder.forField(txtDomicilio).bind(Cliente::getDomicilio, Cliente::setDomicilio);
        binder.forField(txtCiudad).bind(Cliente::getCiudad, Cliente::setCiudad);
        binder.forField(txtEstado).bind(Cliente::getEstado, Cliente::setEstado);
        binder.forField(txtCp).bind(Cliente::getCp, Cliente::setCp);
        binder.forField(txtTel).bind(Cliente::getTel, Cliente::setTel);
        binder.forField(txtCorreo).bind(Cliente::getCorreo, Cliente::setCorreo);
        binder.forField(txtRecepcion).withConverter(new LocalDateToDateConverter()).bind(Cliente::getRecepcion_factura, Cliente::setRecepcion_factura);
        binder.forField(txtReferencia).withConverter(new LocalDateToDateConverter()).bind(Cliente::getReferencia_factura, Cliente::setReferencia_factura);
        binder.forField(txtAdicional).bind(Cliente::getAdicional_factura, Cliente::setAdicional_factura);

        if (isEdit) {
            binder.readBean(cliente);

            cboTipo.setValue(cliente.getTipo());
        }

        cont.addComponents(new HorizontalLayout(form, form2), new HorizontalLayout(btnGuardar, btnCancelar));

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);

        btnCancelar.addClickListener((ev) -> {
            close();
        });

        btnGuardar.addClickListener((e) -> {
            try {
                binder.writeBean(cliente);

                ClienteService service = new ClienteService();

//                cliente.setNombre(cliente.getNombre().toUpperCase());
                cliente.setTipo(cboTipo.getValue());
                cliente.setUnidad_id(0);
                cliente.setUnidad("Tampico");
                cliente.setUsuario_id(0);
                cliente.setUsuario("laura");
                cliente.setEmpresa("Grucas");
                cliente.setEmpresa_id(0);
                cliente.setActivo(true);
                
                if (isEdit) {
                    service.ClienteUpdate(cliente);
                } else {
                    service.ClienteInsert(cliente);
                }

                if (service.getOk()) {

                    MessageBox.createInfo()
                            .withCaption("Atencion")
                            .withMessage(service.getNotification())
                            .open();

                    close();
                } else {
                    MessageBox.createError()
                            .withCaption("Error!")
                            .withMessage(service.getNotification())
                            .withRetryButton()
                            .open();
                }

            } catch (Exception ex) {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Verifique que la informacion este completa o sea correcta. ")
                        .withRetryButton()
                        .open();
            }
        });

    }

}
