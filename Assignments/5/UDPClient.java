/**
* Matthew Zaldana
* SID: 027008928
* Assignment 5
* 4-24-22
*/

import java.net.*;
import java.io.*;
import java.util.Scanner;

// Class for the UDP Client that sends message to server
// Requests user input and displays any errors
public class UDPClient {
    public static void main(String args[]) throws UnknownHostException {
        Scanner in = new Scanner(System.in);		// Creates a Scanner object to take input
        System.out.print("Please enter an IP-Address: ");		// Prompt for IP
        String ipAddr = in.nextLine();		// Gets input
        System.out.print("Please enter the port number of the server: ");		// Prompt for port number
        int port_number = in.nextInt();		// Gets input
        DatagramSocket aSocket = null;		// Creates new Datagram object for server relay timeout
        try {
            in.nextLine();
            aSocket = new DatagramSocket();		// Opens new DatagramSocket
            aSocket.setSoTimeout(1000);		// sets timeout to 1 second
            
            while (true) {
                System.out.print("Please enter a message to send: ");		// Prompt for message to send
                String message = in.nextLine();		// Gets input
                byte[] m = message.getBytes();		// creates a byte array from message input
                InetAddress aHost = InetAddress.getByName(ipAddr);		// Creates new InetAddress object for validating IP address
                int serverPort = port_number;		// Saves port_number into serverPort
                DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);		// Makes new DatagramPacket object and passes message byte array, array size, IP address, and server port
                aSocket.send(request);		// Sends DatagramPacket via socket
                byte[] buffer = new byte[1000];		// Creates new byte array for the reply
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);		// Creates new DatagramPacket object for reply, constructed from buffer array and size
                aSocket.receive(reply);		// Socket receives reply
                String rep = new String(reply.getData());		// Creates new String object given reply data
                System.out.println("Server message reply: " + rep);		// Displays server reply
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());	// Displays error if cannot reach socket
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());		// Displays error from input
        } finally {
            if (aSocket != null)
                aSocket.close();		// Closes the datagram socket
			in.close();		// Closes the Scanner object for input
        }
    }
}