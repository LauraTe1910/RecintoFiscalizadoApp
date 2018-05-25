/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grucas.web.recinto.views;

import com.grucas.domain.model.Usuario;
import com.grucas.recinto.domain.EmbarcadorService;
import com.grucas.recinto.model.Embarcador;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.rubicone.vaadin.fam3.silk.Fam3SilkIcon;
import com.grucas.web.recinto.fragments.FragmentTop;
import com.grucas.web.recinto.windows.EmbarcadorWindow;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Window;
import de.steinwedel.messagebox.MessageBox;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GrucasDev
 */
public class EmbarcadorView extends Panel implements View {

    Usuario usuario = (Usuario) VaadinSession.getCurrent().getSession().getAttribute("USUARIO_ACTIVO");

    public static final String NAME = "EMBARCADOR";
    VerticalLayout container = new VerticalLayout();

    Button btnAdd = new Button("Agregar", Fam3SilkIcon.ADD);
    Button btnModify = new Button("Modificar", Fam3SilkIcon.PENCIL);
    Grid<Embarcador> gridEmbarcador = new Grid<>();
    List<Embarcador> listEmbarcador = new ArrayList<>();

    public EmbarcadorView() {

        initComponents();

        HorizontalLayout hLayoutAux = new HorizontalLayout(btnAdd, btnModify) {
            {
                setSpacing(true);
            }
        };

        Label lblTitulo = new Label("EMBARCADOR") {
            {
                setStyleName("h1");
            }
        };
        lblTitulo.setSizeUndefined();

        container.setMargin(false);
        container.addComponents(new FragmentTop(),
                lblTitulo,
                hLayoutAux,
                gridEmbarcador);

        container.setComponentAlignment(container.getComponent(0), Alignment.MIDDLE_LEFT);
        container.setComponentAlignment(container.getComponent(1), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(2), Alignment.MIDDLE_CENTER);
        container.setComponentAlignment(container.getComponent(3), Alignment.MIDDLE_CENTER);

        setContent(container);

    }

    public void initComponents() {
        setSizeFull();
        
        gridEmbarcador.setItems(getEmbarcado());
        gridEmbarcador.addColumn(Embarcador::getNombre).setCaption("NOMBRE").setId("NOMBRE");
        gridEmbarcador.addColumn(Embarcador::getTipo).setCaption("TIPO").setId("TIPO");

        gridEmbarcador.setHeight("350px");
        gridEmbarcador.setSelectionMode(SelectionMode.SINGLE);

        gridEmbarcador.addItemClickListener((evt) -> {
            if (evt.getMouseEventDetails().isDoubleClick()) {
                EmbarcadorWindow windows = new EmbarcadorWindow(evt.getItem());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridEmbarcador.setItems(getEmbarcado());
                    }
                });
                getUI().addWindow(windows);
            }
        });

        btnAdd.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                EmbarcadorWindow windows = new EmbarcadorWindow();
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridEmbarcador.setItems(getEmbarcado());
                    }
                });
                getUI().addWindow(windows);
            }
        });

        btnModify.addClickListener(event -> {

            if (gridEmbarcador.getSelectedItems().size() == 1) {
                EmbarcadorWindow windows = new EmbarcadorWindow(gridEmbarcador.getSelectedItems().iterator().next());
                windows.center();
                windows.setModal(true);
                windows.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        gridEmbarcador.setItems(getEmbarcado());
                    }
                });
                getUI().addWindow(windows);

            } else {
                MessageBox.createError()
                        .withCaption("Error!")
                        .withMessage("Debe tener un Embarcador seleccionado para poder modificarlo.")
                        .withRetryButton()
                        .open();
            }
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    public List getEmbarcado() {
        EmbarcadorService service = new EmbarcadorService();
        service.getEmbarcador("", "", "");
        listEmbarcador = service.getObjects();

        if (!service.getOk()) {
            MessageBox.createError()
                    .withCaption("Error al cargar la informacion!")
                    .withMessage("Err: " + service.getNotification())
                    .withRetryButton()
                    .open();
        }
        return listEmbarcador;

    }

}
