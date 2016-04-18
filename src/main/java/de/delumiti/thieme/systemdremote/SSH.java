package de.delumiti.thieme.systemdremote;

import com.jcraft.jsch.*;
import de.delumiti.thieme.systemdremote.GUI.ErrorDialog;
import de.delumiti.thieme.systemdremote.GUI.MyUserInfo;
import de.delumiti.thieme.systemdremote.Tools.Params;
import de.delumiti.thieme.systemdremote.Tools.Service;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by denni on 15.04.2016.
 */
public class SSH {
    private Params params;
    private JSch jsch;
    private Session session;
    private UserInfo userInfo;
    private Channel channel;

    public SSH(Params params) {
        this.params = params;
        jsch = new JSch();
        session = null;
        userInfo = null;
        channel = null;
    }

    public void connect() {
        String connection;

        if (params.isAsk()) {
            connection = JOptionPane.showInputDialog("Enter username@hostname:port",
                    System.getProperty("user.name") +
                            "@localhost:22");
            params.setHostname(connection.substring(connection.indexOf('@') + 1, connection.indexOf(':')));
            params.setUser(connection.substring(0, connection.indexOf('@')));
            params.setPort(Integer.parseInt(connection.substring(connection.indexOf(':'))));
        }

        try {
            session = jsch.getSession(params.getUser(), params.getHostname(), params.getPort());
            java.util.Properties sessionConfig = new java.util.Properties();
            sessionConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sessionConfig);
        } catch (JSchException e) {
            e.printStackTrace();
        }

        // username and password will be given via UserInfo interface.
        userInfo = new MyUserInfo();
        if (session != null) {
            if (params.getPassword().equals("")) {
                session.setUserInfo(userInfo);
            } else {
                session.setPassword(params.getPassword());
            }
            try {
                session.connect();
            } catch (JSchException e) {
                ErrorDialog err = new ErrorDialog("Error conecting to " + params.getUser() + "@" + params.getHostname() + ".\n\n" + e.getMessage());
                err.show();
            }
        }
    }

    public void disconnect() {
        if (channel != null && session != null) {
            channel.disconnect();
            session.disconnect();
        }
    }

    public String execute(String command) {
        try {
            if (session != null) {
                channel = session.openChannel("exec");
            }
        } catch (JSchException e) {
            ErrorDialog err = new ErrorDialog("Error executing command " + command + " on " + params.getUser() + "@" + params.getHostname() + ".\n\n" + e.getMessage());
            err.show();
        }

        InputStream in = null;

        if ((channel) != null) {
            ((ChannelExec) channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            try {
                in = channel.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                channel.connect();
            } catch (JSchException e) {
                ErrorDialog err = new ErrorDialog("Error receiving result of " + command + " on " + params.getUser() + "@" + params.getHostname() + ".\n\n" + e.getMessage());
                err.show();
            }
        }

        String returnString = "";
        byte[] tmp = new byte[1024];
        while (in != null) {
            try {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    returnString += new String(tmp, 0, i);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(channel.isClosed()){
                try {
                    if(in.available()>0) continue;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        return returnString;
    }

    public String getRemoteOS() {
        return execute("uname -o -v -r");
    }

    public ArrayList<Service> getSystemdServices() {
        String result = execute("systemctl list-unit-files --type=service --no-pager");
        String[] serviceLines = result.split("\n");
        ArrayList<Service> services = new ArrayList<>();
        for (int i = 1; i < serviceLines.length - 2; i++) {
            String[] service = serviceLines[i].split(" +");
            String name = service[0];
            String state = service[1];
            services.add(new Service(name, state));
        }
        return services;
    }

    public String getServiceState(String name) {
        return execute("systemctl status " + name);
    }

    public boolean getServiceActiveState(String name) {
        String result = execute("systemctl is-active " + name);
        switch (result) {
            case "active\n":
                return true;
            case "failed\n":
                return false;
            default:
                return false;
        }
    }

    /**
     * Gets channel.
     *
     * @return Value of channel.
     */
    public Channel getChannel() {
        return channel;
    }

    /**
     * Sets new session.
     *
     * @param session New value of session.
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Gets params.
     *
     * @return Value of params.
     */
    public Params getParams() {
        return params;
    }

    /**
     * Sets new userInfo.
     *
     * @param userInfo New value of userInfo.
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * Gets jsch.
     *
     * @return Value of jsch.
     */
    public JSch getJsch() {
        return jsch;
    }

    /**
     * Sets new channel.
     *
     * @param channel New value of channel.
     */
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    /**
     * Gets session.
     *
     * @return Value of session.
     */
    public Session getSession() {
        return session;
    }

    /**
     * Gets userInfo.
     *
     * @return Value of userInfo.
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * Sets new params.
     *
     * @param params New value of params.
     */
    public void setParams(Params params) {
        this.params = params;
    }

    /**
     * Sets new jsch.
     *
     * @param jsch New value of jsch.
     */
    public void setJsch(JSch jsch) {
        this.jsch = jsch;
    }
}
