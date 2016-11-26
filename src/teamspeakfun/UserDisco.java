/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamspeakfun;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author patric
 */
public class UserDisco implements Task{
    private int[] cldbids;
    private ServerDataParser sdp;
    public UserDisco(int[] cldbids,ServerDataParser sdp){
        this.cldbids = cldbids;
        this.sdp = sdp;
    }   
    
    @Override
    public void does(){
        for (Integer cldbid : cldbids) {
            int number = (int) (75*Math.random());
            try {
                sdp.call("clientaddperm cldbid="+cldbid+" permsid=i_client_talk_power permvalue="+number+" permskip=0");
            } catch (IOException ex) {
                Logger.getLogger(UserDisco.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    @Override
    public void undoes(){
        for (Integer cldbid : cldbids) {
            try {
                sdp.call("clientdelperm cldbid="+cldbid+" permsid=i_client_talk_power");
            } catch (IOException ex) {
                Logger.getLogger(UserDisco.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    public void repeat(int number){
        for (int i = 0; i < number; i++) {
            does();
        }
        undoes();
    }
}
