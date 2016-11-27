/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamspeakfun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patric
 */
public class ServerDataParser {

    private PrintWriter out;
    private BufferedReader in;
    private Socket socket = null;
    public ServerDataParser(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException("Can establish connection");
        }
    }

    public void updateClients() throws IOException {
        String tmp = call("clientlist",true)[0];
        String[] tmpClientData = tmp.split("\\|");
        for (String string : tmpClientData) {
            String[] stringData = string.split(" ");
            int clid = Integer.parseInt(stringData[0].replace("clid=", ""));
            int cldbid = Integer.parseInt(stringData[2].replace("client_database_id=", ""));
            String clientNick = stringData[3].replace("client_nickname=", "");
            int clientType = Integer.parseInt(stringData[4].replace("client_type=", ""));
            TeamspeakFun.database.addClient(new Client(clid, cldbid, clientNick, clientType));
        }
    }
    //cid=1 pid=0 channel_order=107 channel_name=[cspacer][\sFoyer\s] channel_flag_are_subscribed=1 total_clients=1
    public void updateChannels() throws IOException{
        String tmp = call("channellist",true)[0];
        String[] tmpClientData = tmp.split("\\|");
        for (String string : tmpClientData) {
            System.out.println(string);
            String[] stringData = string.split(" ");
            int cid = Integer.parseInt(stringData[0].replace("cid=", ""));
            int pid = Integer.parseInt(stringData[1].replace("pid=", ""));
            int channel_order = Integer.parseInt(stringData[2].replace("channel_order=", ""));
            String channel_name = stringData[3].replace("channel_name=", "").replace("\\s", " ");
            int total_clients = Integer.parseInt(stringData[5].replace("total_clients=", ""));
            TeamspeakFun.database.addChannel(new Channel(cid, pid, channel_order, channel_name, total_clients));
        }
 
    }
    public String[] call(String command) throws IOException{
        String[] buffer = new String[50];
        flush();
        out.println(command);
        int c;
        for (c = 0; in.ready(); c++) {
            buffer[c] = in.readLine();
        }
        return Arrays.copyOfRange(buffer, 0, c);
    }
    public String[] call(String command, boolean wait) throws IOException{
        String[] buffer = new String[50];
        flush();
        out.println(command);
        if(wait)
        try {
            Thread.sleep(40);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerDataParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        int c;
        for (c = 0; in.ready(); c++) {
            buffer[c] = in.readLine();
        }
        return Arrays.copyOfRange(buffer, 0, c);
    }

    public void flush() {
        try {
            while (in.ready()) {
                System.err.print((char)in.read());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void close() throws IOException{
        out.close();
        in.close();
        socket.close();
    }
}
