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
>How the each request the server sends (and the client receives) a different Princess Bride quote!

# Client's Experience

    (base) Elberts-MacBook-Pro:SocketClient elbertcheng$ java TCPClient.java 10.19.80.12 17
    Log level: SEVERE

    Prince Humperdinck: [sincerely] Tyrone, you know how much I love watching you work, but I've got my country's 500th anniversary to plan, my wedding to arrange, my wife to murder and Guilder to frame for it; I'm swamped.
    Count Rugen: Get some rest. If you haven't got your health, then you haven't got anything.

    (base) Elberts-MacBook-Pro:SocketClient elbertcheng$ java TCPClient.java 10.19.80.12 17
    Log level: SEVERE

    Inigo Montoya: Hello! My name is Inigo Montoya! You killed my father! Prepare to die!

# Server's Experience 

    (base) Elberts-MacBook-Pro:SocketServer elbertcheng$ javac ./*.java ./threads/*.java ; java SocketServer -i
    Log level: INFO
    Welcome to the server application! The server listens on port 17 as specified by RFC 865.

    Apr 13, 2023 11:44:58 PM SocketServer main
    INFO: Server started on port 17.
    Apr 13, 2023 11:45:01 PM threads.TCPSocket <init>
    INFO: [10.19.80.12]  - CONNECTED!
    Apr 13, 2023 11:45:01 PM threads.TCPSocket run
    INFO: [10.19.80.12]  - SENDING: Prince Humperdinck: [sincerely] Tyrone, you know how much I love watching you work, but I've got my country's 500th anniversary to plan, my wedding to arrange, my wife to murder and Guilder to frame for it; I'm swamped.
    Count Rugen: Get some rest. If you haven't got your health, then you haven't got anything.
    Apr 13, 2023 11:45:01 PM threads.TCPSocket run
    INFO: [10.19.80.12]  - CLOSING THREAD!
    Apr 13, 2023 11:45:01 PM threads.TCPSocket run
    INFO: [10.19.80.12]  - CLOSED!

    Apr 13, 2023 11:45:02 PM threads.TCPSocket <init>
    INFO: [10.19.80.12]  - CONNECTED!
    Apr 13, 2023 11:45:02 PM threads.TCPSocket run
    INFO: [10.19.80.12]  - SENDING: Inigo Montoya: Hello! My name is Inigo Montoya! You killed my father! Prepare to die!
    Apr 13, 2023 11:45:02 PM threads.TCPSocket run
    INFO: [10.19.80.12]  - CLOSING THREAD!
    Apr 13, 2023 11:45:02 PM threads.TCPSocket run
    INFO: [10.19.80.12]  - CLOSED!



