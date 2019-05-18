package com.movtrack;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LabelLayout extends HorizontalLayout {

    protected final Html label;

    public LabelLayout(String html) {
        super();

        label = new Html(html);
        getElement().getStyle().set("background", "#ffffff");
        label.getElement().getStyle().set("background", "#ffffff");

        add(label);
    }

    public void setLabel(String html){
        label.getElement().setProperty("innerHTML", html);
    }

    public String getLabel(){
        return label.getInnerHtml();
    }

    public void setBackground(String color){
        getElement().getStyle().set("background", color);
        label.getElement().getStyle().set("background", color);
    }

//    public void setVisible(boolean visible){
//        label.setVisible(visible);
//        setVisible(visible);
//    }
}
