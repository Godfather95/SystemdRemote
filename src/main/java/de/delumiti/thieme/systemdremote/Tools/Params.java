package de.delumiti.thieme.systemdremote.Tools;

/**
 * Created by denni on 15.04.2016.
 */
public class Params {
    private String hostname;
    private String user;
    private int port;

    public Params() {
        this("localhost", System.getProperty("user.name"), 22);
    }

    public Params(String hostname) {
        this(hostname, System.getProperty("user.name"), 22);
    }

    public Params(String hostname, String user) {
        this(hostname, user, 22);
    }

    public Params(String hostname, String user, int port) {
        this.hostname = hostname;
        this.user = user;
        this.port = port;
    }

    /**
     * Gets hostname.
     *
     * @return Value of hostname.
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Sets new hostname.
     *
     * @param hostname New value of hostname.
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Gets port.
     *
     * @return Value of port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets new port.
     *
     * @param port New value of port.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Gets user.
     *
     * @return Value of user.
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets new user.
     *
     * @param user New value of user.
     */
    public void setUser(String user) {
        this.user = user;
    }

}
