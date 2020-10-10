package com.example.app;

import java.io.IOException;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class MainView extends VerticalLayout {

    public MainView(@Autowired NodeIntegration node) {
        TextField textField = new TextField("Number of items to generate");

        Button button = new Button("Generate", e -> {
            long start = System.currentTimeMillis();
            int count = Integer.parseInt(textField.getValue());
            for (int i = 0; i < count; i++) {
                Div result = new Div();
                try {
                    result.setText(node.runScript("require('casual').first_name;"));
                } catch (InterruptedException | IOException e1) {
                    result.setText(e1.getMessage());
                }
                add(result);
            }
            long end = System.currentTimeMillis();
            Div time = new Div();
            time.setText(count + " invocations took " + (end - start) + "ms");
            add(time);
        });
        add(textField, button);
    }

}
