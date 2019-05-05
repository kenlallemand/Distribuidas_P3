/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.node;

import com.distributed.utilities.RMIMaster;
import com.distributed.utilities.RMINode;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kenla
 */
public class Node {
        int ID;
    
    public static void main(String[] args) {
        try{
            Node remoteObject=new Node();
            RMINode skeleton=(RMINode)
                    UnicastRemoteObject.exportObject((Remote) remoteObject, 0);
            LocateRegistry.createRegistry(1098);
            Registry registry=LocateRegistry.getRegistry();
            registry.bind("remoteObject", skeleton);
            System.out.println("Node up and ready");
        }catch (Exception ex) {
            System.err.println(ex);
        }
        
       
    }
     public Node(){
        try {
            Registry registry;
            registry = LocateRegistry.getRegistry("10.20.16.223", 1099);
            RMIMaster stub= (RMIMaster)registry.lookup("remoteObjectM");
            this.ID = stub.nodos("prueba2");
        } catch (RemoteException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
