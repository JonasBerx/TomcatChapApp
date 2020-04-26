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
                let tdform = document.createElement('td');

                let form = document.createElement("form");
                form.id = "chatform";
                form.method = "POST";
                let chatbtn = document.createElement("input");
                chatbtn.type = "button";
                chatbtn.value = "Chat now";
                chatbtn.onclick = function () {
                    let name = tdname.innerText;
                    console.log(name);
                    document.getElementById("chatRecipient").innerHTML = name;
                    openForm();
                };

                form.appendChild(chatbtn);
                tdform.appendChild(form);
                tr.appendChild(tdname);
                tr.appendChild(tdstatus);
                tr.appendChild(tdform);
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


// JQUERY AJAX -
let chatwindow = false;
let $currentUser = document.getElementById("currentuser").innerText;
console.log($currentUser);
let $recipient = $currentUser.toLowerCase()+"@ucll.be";
// $(window).on('load', getMessages());

let $message = $('#chatbox');


function getMessages() {
    $.ajax({
        type: "GET",
        url: "Controller?action=GetMessages",
        async: true,
        success: function (text) {
            getData(text);
        },
        error: function () {
            console.log("error in get");

        }
    });
}
function getData(text) {
    var t;
    console.log("getData");
    //TODO fix messages showing up at correct persons
        // let jsonResponse = JSON.parse(messageXhr.responseText);
        // let $senderIdStuff = $('#sessionStuff').text();
        // let $recipient = $recipient.val();
        // $message.empty();
        // for (let message in jsonResponse) {
        //     if ((jsonResponse[message].recipientId === $senderIdStuff || $senderIdStuff === jsonResponse[message].senderId) &&
        //         (jsonResponse[message].recipientId === $recipient || jsonResponse[message].senderId === $recipient)) {
        //         let messageLine = document.createElement('p');
        //         let sender = document.createTextNode(jsonResponse[message].sender +": ");
        //         let messageLineText = document.createTextNode(jsonResponse[message].message);
        //         messageLine.appendChild(sender);
        //         messageLine.appendChild(messageLineText);
        //         $message.append(messageLine);
        //     }

    let response = JSON.parse(text);
    // alert(response);
    let $senderIdStuff = $currentUser.toLowerCase() + "@ucll.be";
    $message.empty();
    for (let message in response) {
        console.log("In for loop");
        console.log(response[message].recipientId+ $senderIdStuff);
        console.log($senderIdStuff + response[message].senderId);
        console.log(response[message].recipientId + $recipient+"@ucll.be");
        console.log(response[message].senderId + $recipient+"@ucll.be");


        if ((response[message].recipientId === $senderIdStuff || $senderIdStuff === response[message].senderId) && (response[message].recipientId === $recipient.toLowerCase()+"@ucll.be" || response[message].senderId === $recipient.toLowerCase()+"@ucll.be")) {

            // let messageLine = document.createElement('div');
            // let sender = document.createTextNode(response[message].sender + ": ");
            // let messageLineText = document.createTextNode(response[message].message);
            // messageLine.appendChild(sender);
            // messageLine.appendChild(messageLineText);
            // $message.append(messageLine + "<br/>");

            $message.append(response[message].sender + ": "+ response[message].message+"<br/>")

            console.log(response[message].sender + ": "+ response[message].message+"<br/>")
        }

    }
        console.log(chatwindow);
        t = setInterval(getMessages,10000);

    if (chatwindow === false) {
        console.log("cleared interval");
        clearInterval(t);
    }
}

function openForm() {
    chatwindow = true;
    document.getElementById("chatdiv").style.display = "block";
    $recipient = $('#chatRecipient').html();
    getMessages();
    $('#chatsubmit').on('click', sendMessage);
    document.getElementById("chatsubmit").addEventListener("onclick", sendMessage);

    console.log("openform recipient" + $recipient);
}
function sendMessage() {
    let $text = $('#msg').val();
    let $recipientId = $recipient;

    console.log($text);
    console.log("Recipientid in sendmessage" + $recipientId);

    $.ajax({
        type: "POST",
        url: "Controller?action=Message",
        data: {"message": $text, "recipient": $recipientId.toLowerCase()+"@ucll.be"},
        async: true,
        dataType: "json",
        success: function () {
            getMessages();
            $('#chatbox').append($currentUser + ": " + $text+"<br/>");
            document.getElementById("msg").value = "";
        },
        error: function () {
            console.log($recipientId);
            console.log($text);
            console.log("error in send");
        }
    });
}
function closeForm() {
    document.getElementById("chatdiv").style.display = "none";
    chatwindow = false;
}

document.getElementById('bgform').addEventListener('submit', (ev => {
    ev.preventDefault();
    let color = document.getElementById("colorChooser").value;

    console.log(color);

    $.post({
        url: "Controller?action=Change",
        data: {"color": color},
        async: true,
        success : function () {
            document.body.style.backgroundColor = color.toString();
        }
    })
}));

