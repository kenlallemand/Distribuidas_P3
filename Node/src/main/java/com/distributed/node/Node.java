/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distributed.node;

import com.distributed.utilities.DPUCrypter;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.distributed.utilities.RMINode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import com.distributed.utilities.RMIServer;

/**
 *
 * @author kenla
 */
public class Node implements RMINode {
        int ID;
        DPUCrypter CrypterResource = new DPUCrypter();
        boolean active = true;
    
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
            RMIServer stub= (RMIServer)registry.lookup("remoteObjectM");
            this.ID = stub.nodos("prueba2");
        } catch (RemoteException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @Override
    public void stop() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] DecryptFile(String fileName, byte[] fileBytes, String key) throws RemoteException {
        try { 
            File inputFile = new File(fileName);
            File outputFile = new File("D"+fileName);
            Files.write(inputFile.toPath(), fileBytes);
            DPUCrypter.CryptFileUsingAES(false,key,inputFile,outputFile,null);
            FileInputStream outputStream = new FileInputStream(outputFile);
            byte[] outputBytes = new byte[(int) outputFile.length()];
            outputStream.read(outputBytes);
            outputStream.close();
            return outputBytes;
        } catch (Exception e) { 
            System.out.println("Exception: " + e); 
            return null;
        } 
    }

    @Override
    public String CrackFileAndFindKey(ArrayList keys, byte[] inputFile, byte[] outputFile, String checkSum) throws RemoteException {
        try{
            File inputFilen = new File("ENCRYPTN");
            File outputFilen = new File("DECRYPTN");
            Files.write(inputFilen.toPath(), inputFile); 
            return DPUCrypter.CrackFile(keys, inputFilen, outputFilen, checkSum);
        }catch(IOException e){
            System.out.println(e);
        }
            return "NOT FOUND";
    }
     
}
