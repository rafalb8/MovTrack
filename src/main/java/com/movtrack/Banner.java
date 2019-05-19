package com.movtrack;

public class Banner extends TextLayout {
    private static final String text =
            "<pre>"+
            "8888ba.88ba                    d888888P                            dP      \n" +
            "88  `8b  `8b                      88                               88      \n" +
            "88   88   88 .d8888b. dP   .dP    88    88d888b. .d8888b. .d8888b. 88  .dP \n" +
            "88   88   88 88'  `88 88   d8'    88    88'  `88 88'  `88 88'  `\"\" 88888\"  \n" +
            "88   88   88 88.  .88 88 .88'     88    88       88.  .88 88.  ... 88  `8b.\n" +
            "dP   dP   dP `88888P' 8888P'      dP    dP       `88888P8 `88888P' dP   `YP"+
            "</pre>";


    public Banner() {
        super(text);
        getElement().addEventListener("click", event -> click());
    }

    // Click event
    public void click() {
        // Return home
        getUI().ifPresent(ui -> ui.navigate(""));
    }
}
