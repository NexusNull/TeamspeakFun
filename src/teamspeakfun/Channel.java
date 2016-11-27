/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamspeakfun;

/**
 *
 * @author patric
 */
public class Channel {

    private final int cid;
    private final int channelOrder;
    private final String channelName;
    private final int parentId;
    private final int clientCount;

    public Channel(int cid, int pid, int channelOrder, String channelName, int clientCount) {
        this.cid = cid;
        this.channelOrder = channelOrder;
        this.channelName = channelName;
        this.parentId = pid;
        this.clientCount = clientCount;
    }

    public int getCid() {
        return cid;
    }
    
    public int getPid() {
        return parentId;
    }
    
    public int getChannelOrder() {
        return channelOrder;
    }

    public String getChannelName() {
        return channelName;
    }
    
    public int getClientCount() {
        return clientCount;
    }
}
