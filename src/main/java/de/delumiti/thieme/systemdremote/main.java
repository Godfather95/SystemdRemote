package de.delumiti.thieme.systemdremote;

import com.jcraft.jsch.*;
import de.delumiti.thieme.systemdremote.GUI.ErrorDialog;
import de.delumiti.thieme.systemdremote.GUI.MyUserInfo;
import de.delumiti.thieme.systemdremote.Tools.Params;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dennis on 15.04.2016.
 */
public class main {
    public static void main(String[] args) {
        JSch jsch=new JSch();
        Params params = new Params();
        String connection;

        if(args.length>0){
            params = parseArguments(args);
        }
        else{
            connection = JOptionPane.showInputDialog("Enter username@hostname",
                    System.getProperty("user.name")+
                            "@localhost");
            params.setHostname(connection.substring(connection.indexOf('@') + 1));
            params.setUser(connection.substring(0, connection.indexOf('@')));
        }

        Session session= null;

        try {
            session = jsch.getSession(params.getUser(), params.getHostname(), params.getPort());
        } catch (JSchException e) {
            e.printStackTrace();
        }

        // username and password will be given via UserInfo interface.
        UserInfo ui=new MyUserInfo();
        if (session != null) {
            session.setUserInfo(ui);
            try {
                session.connect();
            } catch (JSchException e) {
                ErrorDialog err = new ErrorDialog("Error conecting to " + params.getUser() + "@" + params.getHostname() + ".\n\n" + e.getMessage());
                err.show();
                System.exit(1);
            }
        }

        String command=JOptionPane.showInputDialog("Enter command",
                "set|grep SSH");

        Channel channel= null;
        try {
            if (session != null) {
                channel = session.openChannel("exec");
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }

        InputStream in= null;

        if ((channel) != null) {
            ((ChannelExec)channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec)channel).setErrStream(System.err);
            try {
                in = channel.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                channel.connect();
            } catch (JSchException e) {
                e.printStackTrace();
            }
        }

        byte[] tmp=new byte[1024];
        while(in != null){
            try {
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
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
                System.out.println("exit-status: "+channel.getExitStatus());
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ee) {
                System.err.println("");
            }
        }
        if (channel != null) {
            channel.disconnect();
            session.disconnect();
        }
    }

    private static Params parseArguments(String[] args) {
        Params params = new Params();
        int argc = args.length;

        for (int pos = 0; pos < argc; pos++) {
            switch (args[pos]) {
                case "-h":
                    params.setHostname(args[pos + 1]);
                    break;
                case "-u":
                    params.setUser(args[pos + 1]);
                    break;
                case "-p":
                    params.setPort(Integer.parseInt(args[pos + 1]));
                    break;
            }
        }
        return params;
    }

}
