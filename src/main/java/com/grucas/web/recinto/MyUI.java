package com.grucas.web.recinto;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;
import java.util.Locale;
import com.grucas.web.recinto.views.ClienteView;
import com.grucas.web.recinto.views.EmbarcadorView;
import com.grucas.web.recinto.views.MainView;
import com.grucas.web.recinto.views.ProductoView;
import com.grucas.web.recinto.views.SolicitudDeServicioView;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    Navigator navigator;
    CssLayout ccsLayout = new CssLayout();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setLocale(Locale.US);
        
        
        
        
        Responsive.makeResponsive(this);
        setContent(ccsLayout);
        ccsLayout.setSizeFull();

       navigator = new Navigator(this, ccsLayout);
       navigator.addView(MainView.NAME, MainView.class);
       navigator.addView(ClienteView.NAME, ClienteView.class);
       navigator.addView(EmbarcadorView.NAME, EmbarcadorView.class); 
       navigator.addView(ProductoView.NAME, ProductoView.class);
       navigator.addView(SolicitudDeServicioView.NAME, SolicitudDeServicioView.class);
       
       navigator.navigateTo(MainView.NAME);
       

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
