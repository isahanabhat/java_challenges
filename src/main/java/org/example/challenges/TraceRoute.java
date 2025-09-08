/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.challenges;

import java.io.*;
import java.net.*;

/*
This challenge is to build your own trace route tool.
Traceroute is a tool to that allows you to trace the route network packets will
take from one computer to another over a network.
*/

public class TraceRoute {
    public InetAddress ip; // destination
    public int max_hops = 64;
    public int byte_count = 32;
    public DatagramSocket sender; // endpoint for sending & receiving datagrams
    public DatagramSocket receiver;
    
    public DatagramPacket packet;
    public DatagramPacket received_packet;
    
    public TraceRoute(String host) {
        try {
            this.ip = InetAddress.getByName(host);
            this.sender = new DatagramSocket();
            this.receiver = new DatagramSocket(33435);
            this.receiver.setSoTimeout(5000);
            
            // byte[] data = "bhatu is doing traceroute".getBytes();
            // this.packet = new DatagramPacket(data, data.length, ip, 33435);
            
            
        } catch (UnknownHostException ex) {
            System.out.println(ex.getMessage());
        } catch (SocketException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void trace_route() throws UnknownHostException, IOException {
        System.out.println("traceroute to " + ip.getHostName() + " (" + ip.getHostAddress() + "), " 
                + max_hops + " hops max, " + byte_count + " byte packets");
        
        int ttl = 1;
        String receivedIP = "";
        boolean reach = false;
        
        while (!reach) {
            // sender.setTrafficClass(ttl);
            byte[] data = "bhatu is doing traceroute".getBytes();
            packet = new DatagramPacket(data, data.length, ip, 33435);
            sender.send(packet);
            
            try {
                byte[] rec_buffer = new byte[1500];
                received_packet = new DatagramPacket(rec_buffer, rec_buffer.length);
                receiver.receive(received_packet);
                
                receivedIP = received_packet.getAddress().getHostAddress();
                System.out.println("Hop " + ttl + ": ICMP message received from " + receivedIP);
                
                if (receivedIP.equals(ip.getHostAddress())) {
                    reach = true;
                } 
            } catch (SocketTimeoutException e) {
                    System.out.println("Hop " + ttl + ": Request timed out");
            }
            
            ttl++;
        }
        
    }
}
