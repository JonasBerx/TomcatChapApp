let statusRequest = new XMLHttpRequest();
let commentRequest = new XMLHttpRequest();
let friendRequest = new XMLHttpRequest();
let timeoutid;

var webSocket = new WebSocket("ws://localhost:8080/comment");

webSocket.onmessage = function (ev) {
    writeResponse(ev.data);
};


getFriends();
// Personal status - AJAX Part 1 //

document.getElementById('statusForm').addEventListener('submit', (e) => {
    e.preventDefault();
    const status = document.getElementById('statusInput').value;
    console.log(status);
    document.getElementById('status').innerText = status;

    statusRequest.open("POST", "Controller?action=SetStatus", true);
    statusRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    statusRequest.send(`status=${status}`);
    console.log(statusRequest.responseText);
    document.getElementById('statusInput').value = '';
    getFriends();
});


// Addfriend & GetFriend functions - AJAX Part 2 //

document.getElementById("addFriend").addEventListener('submit', (e => {
    e.preventDefault();
    const username = document.getElementById("friendName").value;
    console.log(username);

    addFriend(username);

}));

function addFriend(username) {
    console.log(username);
    friendRequest.open("POST", "Controller?action=addfriend", true);
    friendRequest.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
    friendRequest.send(`name=${username}`);
    console.log(friendRequest.responseText);
    document.getElementById("friendName").value = '';
    
}

function getFriends() {
    console.log("getting friends");
    friendRequest.open("GET", "Controller?action=GetFriends", true);
    friendRequest.onreadystatechange = showFriends;
    friendRequest.send(null);
}

function showFriends() {
    console.log("showing friends");
    if (friendRequest.status === 200){
        if (friendRequest.readyState === 4){
            clearTable();
            let text = JSON.parse(friendRequest.responseText);
            let table = document.getElementById('friends');
            for (let person in text){
                let tr = document.createElement('tr');
                let tdname = document.createElement('td');
                tdname.innerText = text[person].name;
                let tdstatus = document.createElement('td');
                tdstatus.innerText = text[person].statusname;
                tr.appendChild(tdname);
                tr.appendChild(tdstatus);
                tr.className = 'friendlist';
                table.appendChild(tr);
            }
            timeoutid = setTimeout(getFriends,10000);
        }
    }
}

function clearTable(){
    console.log("clearing table");
    let friends = document.querySelectorAll('.friendlist');
    let table = document.getElementById('friends');
    for (let i=0;i<friends.length;i++){
        table.removeChild(friends[i]);
    }
}

// Async comments on posts (Web Sockets) - AJAX Part 3 //



var forms = document.getElementById("blog").getElementsByTagName("form");
console.log(forms.length);

for (let j = 0; j < forms.length; j++) {
    console.log(forms[j].id);
}
let j = 0;
for (let i = 0; i < forms.length; i++) {
    j++;
    console.log(j);
    forms[i].addEventListener('submit', ev => {
        ev.preventDefault();

        console.log("Test");
        console.log(forms[i].id);
        console.log(document.getElementById("username" + forms[i].id).value);
        var name = document.getElementById("username"+forms[i].id).value;
        var comment = document.getElementById("comment"+forms[i].id).value;
        var rating = document.getElementById("rating"+forms[i].id).value;

        var total = forms[i].id +"-" + name + " : " + comment + "       " + rating;

        console.log(name);
        console.log(comment);
        console.log(rating);

        commentRequest.open("POST", "/Controller?action=comment");
        commentRequest.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
        commentRequest.send(`name=${name}&comment=${comment}&rating=${rating}`);


        document.getElementById("comment"+forms[i].id).value = '';
        document.getElementById("rating"+forms[i].id).value = '';

        send(total);
    });
}



function send(text){
    console.log(text);
    webSocket.send(text);
}

function closeSocket(){
    webSocket.close();
}

function writeResponse(text){
    console.log(text);

    if (text === "Connection Established") {

    }
    var split = text.split("-");
    console.log(split);
    document.getElementById("comments" + split[0]).innerHTML += "<br/>" + split[1];

}

