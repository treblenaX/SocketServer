<!-- space -->

    TCP Server
    The TCP Server sends out the connection a short message after the connection has been established.

These are the RFC 865 specifications for a QOTD server:

    One quote of the day service is defined as a connection based
    application on TCP.  A server listens for TCP connections on TCP port
    17.  Once a connection is established a short message is sent out the
    connection (and any data received is thrown away).  The service
    closes the connection after sending the quote.

`SocketServer.java` contains the foundation of the server code and listens on `port 17`. 

`TCPSocket.java` puts up the socket connection when approached by the client and then provides a **Princess Bride quote** as defined from the final String variable within `TCPSocket.java` that is labeled as `QUOTES`.

>Notice below...
>
>How the each request the server sends (and the client receives) a different Princess Bride and/or Monty Python quote!

# Client's Experience - `nc localhost 17`

    elbertcheng$ nc localhost 17
    Miracle Max: Go away or I'll call the Brute Squad.
    Fezzik: I'm on the Brute Squad.
    elbertcheng$ nc localhost 17
    Buttercup: We'll never survive.
    Westley: Nonsense. You're only saying that because no one ever has.
    elbertcheng$ nc localhost 17
    In 1983 Monty Python lay in ruins....


# Server's Experience - `javac ./threads/*.java ; javac ./*.java ; java SocketServer.java`

    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: Miracle Max: Go away or I'll call the Brute Squad.
    Fezzik: I'm on the Brute Squad.
    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: Buttercup: We'll never survive.
    Westley: Nonsense. You're only saying that because no one ever has.
    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: In 1983 Monty Python lay in ruins....



