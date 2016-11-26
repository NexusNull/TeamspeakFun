/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamspeakfun;

import java.io.IOException;


public class TeamspeakFun {
    public boolean running = true;
    public static Database database = null;
    public TeamspeakFun() throws IOException, InterruptedException{
        database = new Database();
        
        ServerDataParser sdp = new ServerDataParser("localhost", 25639);
        
        sdp.updateClients();
        sdp.updateChannels();
        
        UserDisco effect = new UserDisco(database.getCldbids(), sdp);
        BulkChannelRename bulk = new BulkChannelRename(database.getChannels(), sdp);
        
        for (int i = 0; i < 50; i++) {
            effect.does();
            bulk.does();
            Thread.sleep(400);
        }
        effect.undoes();
        bulk.undoes();
       
        sdp.flush();
        sdp.close();
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
       new TeamspeakFun();
    }
    

}
