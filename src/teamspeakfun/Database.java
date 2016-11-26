/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamspeakfun;

import java.util.ArrayList;

/**
 *
 * @author patric
 */
public class Database {
    private ArrayList<Client> clients = new ArrayList();
    private ArrayList<Channel> channels = new ArrayList();
      
    
    
    public void addChannel(Channel channel){
        channels.add(channel);
        System.out.println("Added Channel:"+channel.getChannelName());
    }
    public void clearChannels(){
        channels.clear();
    }
    
    public void addClient(Client client){
        clients.add(client);
        System.out.println("Added: "+client.getClientNick());
    }
    public void clearClients(){
        clients.clear();
    }
    
    public int[] getCldbids(){
        int[] result = new int[clients.size()];
        
        for (int i = 0; i < clients.size(); i++) {
            result[i] = clients.get(i).getCldbid();
        }
        return result;
    }
    public ArrayList<Channel> getChannels(){
        
        return channels;
    }
}
