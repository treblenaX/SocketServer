<h1>TCP/UDP Socket Server</h1>
<p>Please check the individual markdown files or refer to the dropdowns below...</p>
<details>
                <summary>TCP Socket Functionality</summary>
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



</details>
<details>
                <summary>UDP Socket Functionality</summary>
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
>How the each request the server sends (and the client receives) a different Princess Bride and/or Monty Python quote!

# `javac ./threads/*.java ; javac ./*.java ; java SocketServer.java`

## Client's Experience - `echo "test" | nc -u localhost 17`

    elbertcheng$ echo "test" | nc -u localhost 17
    I know a dead parrot when I see one, and I'm looking at one right now.^C
    elbertcheng$ echo "test" | nc -u localhost 17
    Westley: To the pain means the first thing you will lose will be your feet below the ankles. Then your hands at the wrists. Next your nose.
    Prince Humperdinck: And then my tongue I suppose, I killed you too quickly the last time. A mistake I don’t mean to duplicate tonight.
    Westley: I wasn’t finished. The next thing you will lose will be your left eye followed by your right.
    Prince Humperdinck: And then my ears, I understand let’s get on with it.
    Westley: Wrong! Your ears you keep and  I’ll tell you why. So that every shriek of every child at seeing your hideousness will be yours to cherish. Every babe that weeps at your approach, every woman who cries out, “Dear God! What is that thing,” will echo in your perfect ears. That is what to the pain means. It means I leave you in anguish, wallowing in freakish misery forever.^C
    elbertcheng$ echo "test" | nc -u localhost 17
    Man in Black: I do not envy you the headache you will have when you awake. But for now, rest well and dream of large women.

## Server's Experience - receiving an UDP packet

    [UDP/0:0:0:0:0:0:0:1] MESSAGE SENT: I know a dead parrot when I see one, and I'm looking at one right now.
    [UDP/0:0:0:0:0:0:0:1] MESSAGE SENT: Westley: To the pain means the first thing you will lose will be your feet below the ankles. Then your hands at the wrists. Next your nose.
    Prince Humperdinck: And then my tongue I suppose, I killed you too quickly the last time. A mistake I don’t mean to duplicate tonight.
    Westley: I wasn’t finished. The next thing you will lose will be your left eye followed by your right.
    Prince Humperdinck: And then my ears, I understand let’s get on with it.
    Westley: Wrong! Your ears you keep and  I’ll tell you why. So that every shriek of every child at seeing your hideousness will be yours to cherish. Every babe that weeps at your approach, every woman who cries out, “Dear God! What is that thing,” will echo in your perfect ears. That is what to the pain means. It means I leave you in anguish, wallowing in freakish misery forever.
    [UDP/0:0:0:0:0:0:0:1] MESSAGE SENT: Man in Black: I do not envy you the headache you will have when you awake. But for now, rest well and dream of large women.
</details>
<details>
                <summary>Server Critera Checks</summary>
                    
# Client's Experience
`nc localhost 17` is NetCat's tool for a TCP connection

`echo "test" | nc -u localhost 17` is NetCat's tool to send "test" in a UDP packet 

>Notice... The server outputs a Princess Bride quote.

    elbertcheng$ nc localhost 17
    Buttercup: We'll never survive.
    Westley: Nonsense. You're only saying that because no one ever has.

>Notice... The server outputs a different quote on each request. I chose "Monty Python" for my favorite movie quote!

    elbertcheng$ nc localhost 17
    In 1983 Monty Python lay in ruins....
    elbertcheng$ nc localhost 17
    It's my considered opinion that they're nestin'.
    elbertcheng$ nc localhost 17
    What a *senseless* waste of human life.

>Notice... SocketServer supports both TCP and UDP connections on port 17 and they are not separate applications. They are both executed under `SocketServer.java`. At any point in time while the server is active, it can handle TCP and UDP requests simultaneously.

    elbertcheng$ nc localhost 17
    I want to buy some cheese.
    elbertcheng$ echo "test" | nc -u localhost 17
    Look! There's the old man from Scene 24!^C

>Notice... that you have to use CONTROL+C (^C) to break out of the NetCat UDP command.

    elbertcheng$ echo "test" | nc -u localhost 17
    Is, your uh, is your wife a sport, eh?^C
    elbertcheng$ echo "test" | nc -u localhost 17
    I'd split their nostrils open with a boat-hook!^C
    elbertcheng$ nc localhost 17
    Number nine: The Nape of the Neck.

# Server's Experience

    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: Buttercup: We'll never survive.
    Westley: Nonsense. You're only saying that because no one ever has.
    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: In 1983 Monty Python lay in ruins....
    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: It's my considered opinion that they're nestin'.
    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: What a *senseless* waste of human life.
    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: I want to buy some cheese.
    [UDP/0:0:0:0:0:0:0:1] MESSAGE SENT: Look! There's the old man from Scene 24!
    [UDP/0:0:0:0:0:0:0:1] MESSAGE SENT: Is, your uh, is your wife a sport, eh?
    [UDP/0:0:0:0:0:0:0:1] MESSAGE SENT: I'd split their nostrils open with a boat-hook!
    [TCP/0:0:0:0:0:0:0:1] MESSAGE SENT: Number nine: The Nape of the Neck.

</details>
