
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

