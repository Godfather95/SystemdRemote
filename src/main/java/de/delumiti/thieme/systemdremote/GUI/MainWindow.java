package de.delumiti.thieme.systemdremote.GUI;

import com.jcraft.jsch.JSch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by denni on 15.04.2016.
 */
public class MainWindow {
    public JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel pnlConnection;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JLabel lblHostname;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JButton btnConnect;
    private JLabel lblStatus;
    private JPanel pnlStatus;
    private JLabel lblIp;
    private JLabel lblOs;
    private JLabel lblIpValue;
    private JLabel lblOsValue;
    private JTree trServices;

    public MainWindow() {
        btnConnect.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        trServices = new JTree();
        trServices.removeAll();
        trServices.setModel(null);
    }


    /**
     * Sets new pnlStatus.
     *
     * @param pnlStatus New value of pnlStatus.
     */
    public void setPnlStatus(JPanel pnlStatus) {
        this.pnlStatus = pnlStatus;
    }

    /**
     * Sets new lblIpValue.
     *
     * @param lblIpValue New value of lblIpValue.
     */
    public void setLblIpValue(JLabel lblIpValue) {
        this.lblIpValue = lblIpValue;
    }

    /**
     * Gets pnlStatus.
     *
     * @return Value of pnlStatus.
     */
    public JPanel getPnlStatus() {
        return pnlStatus;
    }

    /**
     * Gets tabbedPane.
     *
     * @return Value of tabbedPane.
     */
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    /**
     * Sets new textField2.
     *
     * @param textField2 New value of textField2.
     */
    public void setTextField2(JTextField textField2) {
        this.textField2 = textField2;
    }

    /**
     * Gets lblOs.
     *
     * @return Value of lblOs.
     */
    public JLabel getLblOs() {
        return lblOs;
    }

    /**
     * Sets new lblPassword.
     *
     * @param lblPassword New value of lblPassword.
     */
    public void setLblPassword(JLabel lblPassword) {
        this.lblPassword = lblPassword;
    }

    /**
     * Sets new mainPanel.
     *
     * @param mainPanel New value of mainPanel.
     */
    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    /**
     * Sets new lblStatus.
     *
     * @param lblStatus New value of lblStatus.
     */
    public void setLblStatus(JLabel lblStatus) {
        this.lblStatus = lblStatus;
    }

    /**
     * Gets lblOsValue.
     *
     * @return Value of lblOsValue.
     */
    public JLabel getLblOsValue() {
        return lblOsValue;
    }

    /**
     * Gets mainPanel.
     *
     * @return Value of mainPanel.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Gets passwordField1.
     *
     * @return Value of passwordField1.
     */
    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    /**
     * Sets new tabbedPane.
     *
     * @param tabbedPane New value of tabbedPane.
     */
    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    /**
     * Sets new lblIp.
     *
     * @param lblIp New value of lblIp.
     */
    public void setLblIp(JLabel lblIp) {
        this.lblIp = lblIp;
    }

    /**
     * Gets lblIpValue.
     *
     * @return Value of lblIpValue.
     */
    public JLabel getLblIpValue() {
        return lblIpValue;
    }

    /**
     * Sets new lblUsername.
     *
     * @param lblUsername New value of lblUsername.
     */
    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    /**
     * Gets textField2.
     *
     * @return Value of textField2.
     */
    public JTextField getTextField2() {
        return textField2;
    }

    /**
     * Gets pnlConnection.
     *
     * @return Value of pnlConnection.
     */
    public JPanel getPnlConnection() {
        return pnlConnection;
    }

    /**
     * Sets new pnlConnection.
     *
     * @param pnlConnection New value of pnlConnection.
     */
    public void setPnlConnection(JPanel pnlConnection) {
        this.pnlConnection = pnlConnection;
    }

    /**
     * Gets btnConnect.
     *
     * @return Value of btnConnect.
     */
    public JButton getBtnConnect() {
        return btnConnect;
    }

    /**
     * Gets lblIp.
     *
     * @return Value of lblIp.
     */
    public JLabel getLblIp() {
        return lblIp;
    }

    /**
     * Sets new passwordField1.
     *
     * @param passwordField1 New value of passwordField1.
     */
    public void setPasswordField1(JPasswordField passwordField1) {
        this.passwordField1 = passwordField1;
    }

    /**
     * Sets new lblOs.
     *
     * @param lblOs New value of lblOs.
     */
    public void setLblOs(JLabel lblOs) {
        this.lblOs = lblOs;
    }

    /**
     * Gets lblUsername.
     *
     * @return Value of lblUsername.
     */
    public JLabel getLblUsername() {
        return lblUsername;
    }

    /**
     * Gets lblPassword.
     *
     * @return Value of lblPassword.
     */
    public JLabel getLblPassword() {
        return lblPassword;
    }

    /**
     * Gets lblHostname.
     *
     * @return Value of lblHostname.
     */
    public JLabel getLblHostname() {
        return lblHostname;
    }

    /**
     * Sets new btnConnect.
     *
     * @param btnConnect New value of btnConnect.
     */
    public void setBtnConnect(JButton btnConnect) {
        this.btnConnect = btnConnect;
    }

    /**
     * Sets new lblHostname.
     *
     * @param lblHostname New value of lblHostname.
     */
    public void setLblHostname(JLabel lblHostname) {
        this.lblHostname = lblHostname;
    }

    /**
     * Gets lblStatus.
     *
     * @return Value of lblStatus.
     */
    public JLabel getLblStatus() {
        return lblStatus;
    }

    /**
     * Gets trServices.
     *
     * @return Value of trServices.
     */
    public JTree getTrServices() {
        return trServices;
    }

    /**
     * Gets textField1.
     *
     * @return Value of textField1.
     */
    public JTextField getTextField1() {
        return textField1;
    }

    /**
     * Sets new textField1.
     *
     * @param textField1 New value of textField1.
     */
    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    /**
     * Sets new trServices.
     *
     * @param trServices New value of trServices.
     */
    public void setTrServices(JTree trServices) {
        this.trServices = trServices;
    }

    /**
     * Sets new lblOsValue.
     *
     * @param lblOsValue New value of lblOsValue.
     */
    public void setLblOsValue(JLabel lblOsValue) {
        this.lblOsValue = lblOsValue;
    }
}
