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
