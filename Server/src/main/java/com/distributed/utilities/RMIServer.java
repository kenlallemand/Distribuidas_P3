/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.utilities;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author User
 */
public interface RMIServer extends Remote {
     public int nodos(String ip) throws RemoteException;
     public void stop() throws RemoteException;
     public void decryptFile(byte[] file, String key) throws RemoteException;
     public void CrackFileAndGetKey(byte[] file, String checksum);
}
