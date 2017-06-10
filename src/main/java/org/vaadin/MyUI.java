package org.vaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        TreeTable ttable = new TreeTable();
        ttable.setSizeFull();

        ttable.addContainerProperty("Name", String.class, null);
        ttable.addContainerProperty("Number", Integer.class, null);

        ttable.addItemClickListener(event -> {
            Object itemId = event.getItemId();
            ttable.setCollapsed(itemId, !ttable.isCollapsed(itemId));
        });

        // Create the tree nodes and set the hierarchy
        ttable.addItem(new Object[]{"Menu", null}, 0);
        ttable.addItem(new Object[]{"Beverages", null}, 1);
        ttable.setParent(1, 0);
        ttable.addItem(new Object[]{"Foods", null}, 2);
        ttable.setParent(2, 0);
        ttable.addItem(new Object[]{"Coffee", 23}, 3);
        ttable.addItem(new Object[]{"Tea", 42}, 4);
        ttable.setParent(3, 1);
        ttable.setParent(4, 1);
        ttable.addItem(new Object[]{"Bread", 13}, 5);
        ttable.addItem(new Object[]{"Cake", 11}, 6);
        ttable.setParent(5, 2);
        ttable.setParent(6, 2);

        layout.addComponents(ttable);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
