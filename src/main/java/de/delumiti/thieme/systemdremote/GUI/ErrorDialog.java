package de.delumiti.thieme.systemdremote.GUI;

import javax.swing.*;

public class ErrorDialog {
    private String message;
    private Object[] options;
    private String title;
    private int option;
    private int type;

    public ErrorDialog(String message) {
        this.message = message;
        options = new Object[]{ "ok" };
        title = "Error";
        option = JOptionPane.OK_CANCEL_OPTION;
        type = JOptionPane.ERROR_MESSAGE;
    }

    public void show() {
        JOptionPane.showOptionDialog(null,
                this.message,
                this.title,
                this.option,
                this.type,
                null,
                this.options,
                this.options[0]);
    }
}
