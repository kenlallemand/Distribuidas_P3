/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.server;

import com.distributed.utlilities.RMIServer;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author kenla
 */
public class Server {
    
    ArrayList<String> slaves = new ArrayList();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Server remoteObject=new Server();
            RMIServer skeleton=(RMIServer)
                    UnicastRemoteObject.exportObject((Remote) remoteObject, 0);
            LocateRegistry.createRegistry(1099);
            Registry registry=LocateRegistry.getRegistry();
            registry.bind("remoteObjectMaster", skeleton);
            System.out.println("Server up and ready");
        }catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    
}
