<!-- space -->

    UDP
    The server sends the client a short message when it receives a datagram.

These are the RFC 865 specifications for a QOTD server:

    Another quote of the day service is defined as a datagram based
    application on UDP.  A server listens for UDP datagrams on UDP port
    17.  When a datagram is received, an answering datagram is sent
    containing a quote (the data in the received datagram is ignored).

`SocketServer.java` contains the foundation of the server code and listens on `port 17`. 

`TCPSocket.java` puts up the socket connection when approached by the client and then provides a **Princess Bride quote** as defined from the final String variable within `TCPSocket.java` that is labeled as `QUOTES`.

>Notice below...
>
>How the each request the server sends (and the client receives) a different Princess Bride quote!


