/**
* Matthew Zaldana
* SID: 027008928
* Assignment 5
* 4-24-22
*/

import java.net.*;
import java.io.*;
import java.util.Scanner;

// Class for the UDP Server that receives and sends back client messages
// Takes client message and capitalizes it, then sends it back via the same socket
public class UDPServer {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);		// Creates a Scanner object to take input
        DatagramSocket aSocket = null;		// Creates new Datagram object for servere relay timeout
        System.out.print("Please enter a port number: ");		// Prompt for port number
        String portNum = in.nextLine();		// Gets port input
        try {
            aSocket = new DatagramSocket(Integer.parseInt(portNum));		// Creates new socket from port number
            System.out.println("The server is running.");		// Displays message
            while (true) {
                byte[] buffer = new byte[1000];		// Creates new byte array to relay message back
                DatagramPacket request = new DatagramPacket(buffer, buffer.length); 		// Creates new DatagramPacket object via buffer array and size
                aSocket.receive(request);		// Receives client message
                String rMessage = new String(request.getData());		// Creates new String object from message data
                System.out.println("Client message received: " + rMessage);		// Displays the recieved message
                buffer = rMessage.toUpperCase().getBytes();		// Buffer array saves message in Upper Case form
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort());		// Creates new DatagramPacket object from array, size, client IP, and client port
                aSocket.send(reply);		// Sends the message through the socket
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());		// Displays any socket errors
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());		// Displays any input errors, such as port number
        } finally {
            if (aSocket != null)
                aSocket.close();		// Closes the socket
			in.close();		// Closes the scanner
        }
    }
}