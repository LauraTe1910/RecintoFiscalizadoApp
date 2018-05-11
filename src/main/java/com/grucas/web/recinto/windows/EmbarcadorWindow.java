/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.windows;

import com.grucas.recinto.domain.EmbarcadorService;
import com.grucas.recinto.model.Embarcador;
import com.rubik.model.Usuario;
import com.vaadin.data.Binder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
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
public class EmbarcadorWindow extends Window {

    Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    VerticalLayout cont = new VerticalLayout();
    String title_window = "EMBARCADOR_WINDOW";
    public Embarcador embarcador = new Embarcador();
    public Boolean isEdit = false;

    TextField txtNombre = new TextField("Nombre:");
    NativeSelect<String> cboTipo = new NativeSelect("Tipo:");
    Label title;
    FormLayout form = new FormLayout();

    public EmbarcadorWindow() {
        setCaption("ALTA DE EMBARCADOR");
        initComponents();
    }

    public EmbarcadorWindow(Embarcador embarca) {
        setCaption("MODIFICACION DE EMBARCADOR");
        isEdit = true;
        this.embarcador = embarca;
        initComponents();

    }

    public void initComponents() {

        setContent(cont);
        setHeight("275px");
        setWidth("420px");

        Button btnGuardar = new Button("Guardar", Fam3SilkIcon.DISK);
        Button btnCancelar = new Button("Cancelar", Fam3SilkIcon.CANCEL);

        txtNombre.setWidth("300");
        cboTipo.setItems("prueba1", "prueba2");
        cboTipo.setWidth("300");
        cboTipo.setEmptySelectionAllowed(false);

        form.addComponents(txtNombre, cboTipo);

        Binder<Embarcador> binder = new Binder<>();

        binder.forField(txtNombre).withValidator(nombre -> nombre.length() >= 1, "El campo no puede estar vacio").bind(Embarcador::getNombre, Embarcador::setNombre);
        binder.forField(cboTipo).bind(Embarcador::getTipo, Embarcador::setTipo);

        if (isEdit) {
            binder.readBean(embarcador);

            cboTipo.setValue(embarcador.getTipo());
        }

        cont.addComponents(form,
                new HorizontalLayout(btnGuardar, btnCancelar));

        cont.setComponentAlignment(cont.getComponent(0), Alignment.MIDDLE_CENTER);
        cont.setComponentAlignment(cont.getComponent(1), Alignment.MIDDLE_CENTER);

        btnCancelar.addClickListener((ev) -> {
            close();
        });

        btnGuardar.addClickListener((e) -> {
            try {
                binder.writeBean(embarcador);

                EmbarcadorService service = new EmbarcadorService();

//                cliente.setNombre(operador.getNombre().toUpperCase());
                embarcador.setTipo(cboTipo.getValue());
                embarcador.setUnidad_id(0);
                embarcador.setUnidad("Tampico");
                embarcador.setUsuario_id(0);
                embarcador.setUsuario("laura");
                embarcador.setEmpresa("Grucas");
                embarcador.setEmpresa_id(0);
                embarcador.setActivo(true);

                if (isEdit) {
                    service.EmbarcadorUpdate(embarcador);
                } else {
                    service.EmbarcadorInsert(embarcador);
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
