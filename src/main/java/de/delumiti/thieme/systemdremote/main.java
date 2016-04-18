package de.delumiti.thieme.systemdremote;

import de.delumiti.thieme.systemdremote.GUI.MainWindow;
import de.delumiti.thieme.systemdremote.Tools.Params;

import javax.swing.*;

/**
 * Created by Dennis on 15.04.2016.
 */
public class main {
    public static void main(String[] args) {
        Params params = parseArguments(args);
        SSH ssh = new SSH(params);

        MainWindow mainWindow = new MainWindow(ssh);

        JFrame frame = new JFrame("SystemdRemote");
        frame.setSize(800, 600);
        frame.setContentPane(mainWindow.mainPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static Params parseArguments(String[] args) {
        Params params = new Params();
        int argc = args.length;

        if (argc == 0) {
            params.setAsk(true);
            return params;
        }

        for (int pos = 0; pos < argc; pos++) {
            switch (args[pos]) {
                case "-h":
                case "--hostname":
                    params.setHostname(args[pos + 1]);
                    break;
                case "-u":
                case "--user":
                    params.setUser(args[pos + 1]);
                    break;
                case "-p":
                case "--password":
                    params.setPassword(args[pos + 1]);
                    break;
                case "--port":
                    params.setPort(Integer.parseInt(args[pos + 1]));
                    break;
            }
        }
        return params;
    }

}
