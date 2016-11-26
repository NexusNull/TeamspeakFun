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
public class Client {

    private final int clid;
    private final int cldbid;
    private final String clientNick;
    private final int clientType;

    public Client(int clid, int cldbid, String clientNick, int clientType) {
        this.clid = clid;
        this.cldbid = cldbid;
        this.clientNick = clientNick;
        this.clientType = clientType;
    }

    public int getClid() {
        return clid;
    }

    public int getCldbid() {
        return cldbid;
    }

    public String getClientNick() {
        return clientNick;
    }

    public int getClientType() {
        return clientType;
    }

}
