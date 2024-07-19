let ws, currentUser;

// Auto updates the UI and establishes WebSocket connection with server also sets a event listener for
// incoming messages.
function connect() {

  ws = new WebSocket("ws://localhost:8080/hello");

  //This function will called everytime new message arrives
  ws.onmessage = function (e) {
    console.log(e);
    printMessage(e.data);
  };

  document.getElementById("connectButton").disabled = true;
  document.getElementById("connectButton").value = "Connected";
  document.getElementById("name").disabled = true;
  currentUser = document.getElementById("name").value;
  console.log(currentUser);
}

// parse the incoming message data and display that into the messages container UI.
function printMessage(data) {
  let messages = document.getElementById("messages");
  let messageData = JSON.parse(data);
  let newMessage = document.createElement("div");
  newMessage.className = "incoming-message";
  newMessage.innerHTML = messageData.name + " : " + messageData.message;
  messages.appendChild(newMessage);
}

// This function creates a message object and converts it to JSON format then sends it to the WebSocket server
function sendToGroupChat() {

  if (ws == undefined){
    return;
  }

  let messageText = document.getElementById("message").value;
  document.getElementById("message").value = "";
  let name = document.getElementById("name").value;
  let messageObject = {
    name: name,
    message: messageText,
  };

  let newMessage = document.createElement("div");
  newMessage.innerHTML = messageText + " : " + currentUser;
  newMessage.className = "outgoing-message";
  messages.appendChild(newMessage);

  //In-Built functions Send is used with object we created
  ws.send(JSON.stringify(messageObject));
}