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

    public Channel(int cid, int pid, int channelOrder, String channelName) {
        this.cid = cid;
        this.channelOrder = channelOrder;
        this.channelName = channelName;
        this.parentId = pid;
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

}
