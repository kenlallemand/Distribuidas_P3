/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.utilities;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author asaad
 */
public interface RMINode extends Remote{
    
    public String stop() throws RemoteException;   
    public byte[] DecryptFile(String fileName,byte[] fileBytes,String key) throws RemoteException;
    public String CrackFileAndFindKey(ArrayList keys, byte[] inputFile,String checkSum) throws RemoteException;
}
