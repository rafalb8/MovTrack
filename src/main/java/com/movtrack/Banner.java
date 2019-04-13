package com.movtrack;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Banner extends HorizontalLayout {
    private static final String text =
            "<pre>"+
            "8888ba.88ba                    d888888P                            dP      \n" +
            "88  `8b  `8b                      88                               88      \n" +
            "88   88   88 .d8888b. dP   .dP    88    88d888b. .d8888b. .d8888b. 88  .dP \n" +
            "88   88   88 88'  `88 88   d8'    88    88'  `88 88'  `88 88'  `\"\" 88888\"  \n" +
            "88   88   88 88.  .88 88 .88'     88    88       88.  .88 88.  ... 88  `8b.\n" +
            "dP   dP   dP `88888P' 8888P'      dP    dP       `88888P8 `88888P' dP   `YP"+
            "</pre>";

    private static Html lblText;


    public Banner() {
        super();
        lblText = new Html(text);
        add(lblText);
    }
}
