package de.delumiti.thieme.systemdremote.Tools;

/**
 * Created by denni on 15.04.2016.
 */
public class Service {
    private String name;
    private String state;
    private String active;
    private String running;
    private String description;

    public Service(String name, String state, String active, String running, String description) {
        this.name = name;
        this.state = state;
        this.active = active;
        this.running = running;
        this.description = description;
    }

    public Service(String name, String state) {
        this.name = name;
        this.state = state;
        this.active = "";
        this.running = "";
        this.description = "";
    }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets new description.
     *
     * @param description New value of description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets state.
     *
     * @return Value of state.
     */
    public String getState() {
        return state;
    }

    /**
     * Sets new running.
     *
     * @param running New value of running.
     */
    public void setRunning(String running) {
        this.running = running;
    }

    /**
     * Gets name.
     *
     * @return Value of name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets running.
     *
     * @return Value of running.
     */
    public String getRunning() {
        return running;
    }

    /**
     * Sets new active.
     *
     * @param active New value of active.
     */
    public void setActive(String active) {
        this.active = active;
    }

    /**
     * Sets new state.
     *
     * @param state New value of state.
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets new name.
     *
     * @param name New value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets active.
     *
     * @return Value of active.
     */
    public String getActive() {
        return active;
    }
}
