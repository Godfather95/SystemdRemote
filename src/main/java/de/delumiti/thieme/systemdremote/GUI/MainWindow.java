package de.delumiti.thieme.systemdremote.GUI;

import de.delumiti.thieme.systemdremote.SSH;
import de.delumiti.thieme.systemdremote.Tools.Service;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by denni on 15.04.2016.
 */
public class MainWindow {
    public JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel pnlConnection;
    private JTextField txtHostname;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblHostname;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JButton btnConnect;
    private JLabel lblStatus;
    private JPanel pnlStatus;
    private JLabel lblIp;
    private JLabel lblOs;
    private JLabel lblConnectionStatus;
    private JList lstServices;
    private JLabel lblName;
    private JLabel lblDescription;
    private JLabel lblState;
    private JTextField txtName;
    private JTextField txtDescription;
    private JTextField txtState;
    private JTextArea txtServiceStatus;
    private JLabel lblServiceStatus;
    private JButton btnStart;
    private JButton btnReload;
    private JButton btnStop;
    private JButton btnRestart;

    private final SSH ssh;
    private ArrayList<Service> services;

    public MainWindow(SSH sshP) {
        ssh = sshP;
        services = new ArrayList<Service>();

        txtHostname.setText(ssh.getParams().getHostname());
        txtUsername.setText(ssh.getParams().getUser());
        txtPassword.setText(ssh.getParams().getPassword());
        btnConnect.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnConnect.getText().equals("Connect")) {
                    ssh.connect();
                    if (ssh.getSession().isConnected()) {
                        lblConnectionStatus.setText("Connected to: " + ssh.getSession().getUserName() + "@" + ssh.getSession().getHost());
                        lblConnectionStatus.setForeground(Color.GREEN);
                        try {
                            lblIp.setText("IP: " + InetAddress.getByName(ssh.getSession().getHost()).getHostAddress());
                        } catch (UnknownHostException e1) {
                            e1.printStackTrace();
                        }
                        lblOs.setText("OS: " + ssh.getRemoteOS());
                        btnConnect.setText("Disconnect");
                        services = ssh.getSystemdServices();
                        DefaultListModel<String> model = new DefaultListModel<String>();
                        for (Service service: services) {
                            model.addElement(service.getName());
                        }
                        lstServices.setModel(model);
                    }
                } else if (btnConnect.getText().equals("Disconnect")) {
                    ssh.disconnect();
                    if (!ssh.getSession().isConnected()) {
                        lblConnectionStatus.setText("");
                        lblConnectionStatus.setForeground(Color.BLACK);
                        lblIp.setText("");
                        lblOs.setText("");
                        btnConnect.setText("Connect");
                    }
                }
            }
        });
        lstServices.addListSelectionListener(new ListSelectionListener() {
            /**
             * Called whenever the value of the selection changes.
             *
             * @param e the event that characterizes the change.
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = lstServices.getSelectedIndex();
                    Service service = services.get(selectedIndex);
                    txtName.setText(service.getName());
                    txtDescription.setText(service.getDescription());
                    txtState.setText(service.getState());
                    String status = ssh.getServiceState(service.getName());
                    txtServiceStatus.setText(status);
                    txtDescription.setText(status.split("\n")[0].split(" - ")[1]);
                    if (ssh.getServiceActiveState(service.getName())) {
                        txtName.setBackground(Color.GREEN);
                    } else {
                        txtName.setBackground(Color.RED);
                    }
                }
            }
        });
        btnStart.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println(ssh.execute("sudo systemctl start "+ lstServices.getSelectedValue()));
                services = ssh.getSystemdServices();
                DefaultListModel<String> model = new DefaultListModel<String>();
                for (Service service: services) {
                    model.addElement(service.getName());
                }
                lstServices.setModel(model);
            }
        });
        btnStop.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println(ssh.execute("sudo systemctl stop "+ lstServices.getSelectedValue()));
                services = ssh.getSystemdServices();
                DefaultListModel<String> model = new DefaultListModel<String>();
                for (Service service: services) {
                    model.addElement(service.getName());
                }
                lstServices.setModel(model);
            }
        });
        btnReload.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println(ssh.execute("sudo systemctl reload "+ lstServices.getSelectedValue()));
                services = ssh.getSystemdServices();
                DefaultListModel<String> model = new DefaultListModel<String>();
                for (Service service: services) {
                    model.addElement(service.getName());
                }
                lstServices.setModel(model);
            }
        });
        btnRestart.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.err.println(ssh.execute("sudo systemctl restart "+ lstServices.getSelectedValue()));
                services = ssh.getSystemdServices();
                DefaultListModel<String> model = new DefaultListModel<String>();
                for (Service service: services) {
                    model.addElement(service.getName());
                }
                lstServices.setModel(model);
            }
        });
    }

    private void createUIComponents() {
        DefaultListModel<String> model = new DefaultListModel<String>();
        lstServices = new JList<String>(model);
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
     * Sets new txtUsername.
     *
     * @param txtUsername New value of txtUsername.
     */
    public void setTxtUsername(JTextField txtUsername) {
        this.txtUsername = txtUsername;
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
     * Gets mainPanel.
     *
     * @return Value of mainPanel.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Gets txtPassword.
     *
     * @return Value of txtPassword.
     */
    public JPasswordField getTxtPassword() {
        return txtPassword;
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
     * Sets new lblUsername.
     *
     * @param lblUsername New value of lblUsername.
     */
    public void setLblUsername(JLabel lblUsername) {
        this.lblUsername = lblUsername;
    }

    /**
     * Gets txtUsername.
     *
     * @return Value of txtUsername.
     */
    public JTextField getTxtUsername() {
        return txtUsername;
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
     * Sets new txtPassword.
     *
     * @param txtPassword New value of txtPassword.
     */
    public void setTxtPassword(JPasswordField txtPassword) {
        this.txtPassword = txtPassword;
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
     * Gets txtHostname.
     *
     * @return Value of txtHostname.
     */
    public JTextField getTxtHostname() {
        return txtHostname;
    }

    /**
     * Sets new txtHostname.
     *
     * @param txtHostname New value of txtHostname.
     */
    public void setTxtHostname(JTextField txtHostname) {
        this.txtHostname = txtHostname;
    }

    /**
     * Sets new lblConnectionStatus.
     *
     * @param lblConnectionStatus New value of lblConnectionStatus.
     */
    public void setLblConnectionStatus(JLabel lblConnectionStatus) {
        this.lblConnectionStatus = lblConnectionStatus;
    }

    /**
     * Gets lblConnectionStatus.
     *
     * @return Value of lblConnectionStatus.
     */
    public JLabel getLblConnectionStatus() {
        return lblConnectionStatus;
    }

    /**
     * Gets ssh.
     *
     * @return Value of ssh.
     */
    public SSH getSsh() {
        return ssh;
    }

    /**
     * Sets new services.
     *
     * @param services New value of services.
     */
    public void setServices(ArrayList<Service> services) {
        this.services = services;
    }

    /**
     * Gets services.
     *
     * @return Value of services.
     */
    public ArrayList<Service> getServices() {
        return services;
    }

    /**
     * Gets lstServices.
     *
     * @return Value of lstServices.
     */
    public JList getLstServices() {
        return lstServices;
    }

    /**
     * Sets new lstServices.
     *
     * @param lstServices New value of lstServices.
     */
    public void setLstServices(JList lstServices) {
        this.lstServices = lstServices;
    }
}
