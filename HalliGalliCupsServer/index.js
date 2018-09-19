const app = require('express')();
const SocketIo = require('socket.io');

const server = app.listen(3000, () => {
    console.log('running...');
});
const io = SocketIo(server);

var queue = [];    // 대기큐
var winner;
var room;

io.on('connection', function (socket) {
    console.log('User '+socket.id + ' connected');
    socket.on('ready', function () {
        findPeerForLoneSocket(socket);
    });

    socket.on('complete', function (data) {
        if(winner == null) {
            winner = socket.id;
            socket.emit('win');
            socket.broadcast.to(room);
            socket.broadcast.to(room).emit('lose');
        }
    });

    socket.on('finish', function() {
        winner == null;
        queue.splice(queue.indexOf(socket),1);
    });
});

//상대방을 찾는 함수
var findPeerForLoneSocket = function(socket) {
    console.log("queue.length : "+queue.length);
    if (queue.length > 0) {
        var peer = queue.pop();
        if(peer == socket) {
            findPeerForLoneSocket(socket);
        }

        console.log("success");
        room = socket.id+'#'+peer.id;
        peer.join(room);
        socket.join(room);

        peer.emit('start');
        socket.emit('start');
    } else {
        console.log('push queue');
        queue.push(socket);
        console.log("queue" + queue);
    }
}