/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamspeakfun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patric
 */
public class BulkChannelRename implements Task {

    private ArrayList<Channel> channels;
    private ServerDataParser sdp;
    private String name = "";
    private String test = "Lorliqd t, dolor sit amet.   \n";

    public BulkChannelRename(ArrayList<Channel> channels, ServerDataParser sdp) {
        this.channels = channels;
        this.sdp = sdp;
    }

    public void setName(String name) {
        this.name = c(name);
    }

    @Override
    public void does() {
        int i = 0;
        for (Channel channel : channels) {
            if (channel.getClientCount() < 1) {
                i++;
                try {
                    sdp.call("channeledit cid=" + channel.getCid() + " channel_name=" + (Integer.toHexString((int) (999999999 * Math.random())) + Integer.toHexString((int) (999999999 * Math.random()))).toUpperCase());
                } catch (IOException ex) {
                    Logger.getLogger(BulkChannelRename.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void undoes() {
        for (Channel channel : channels) {
            if (channel.getClientCount() < 1) {
                try {
                    sdp.call("channeledit cid=" + channel.getCid() + " channel_name=" + c(channel.getChannelName()));
                } catch (IOException ex) {
                    Logger.getLogger(BulkChannelRename.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private String c(String string) {
        return string.replace(" ", "\\s");
    }

}
