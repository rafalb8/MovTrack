package com.movtrack;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class TextLayout extends HorizontalLayout {

    protected final Html label;

    public TextLayout(String html) {
        super();

        label = new Html(html);
        getElement().getStyle().set("background", "#ffffff");
        label.getElement().getStyle().set("background", "#ffffff");

        setDefaultVerticalComponentAlignment(Alignment.CENTER);
        add(label);
    }

    public void setText(String html){
        label.getElement().setProperty("innerHTML", html);
    }

    public String getText(){
        return label.getInnerHtml();
    }

    public void setBackground(String color){
        getElement().getStyle().set("background", color);
        label.getElement().getStyle().set("background", color);
    }

    public void setColor(String color){
        label.getElement().getStyle().set("color", color);
    }
}
