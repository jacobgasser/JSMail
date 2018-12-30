const server = "http://jsmail.jacobgasser.com:81/?";

function send(Message, Subject, Sendto) {
  var xhttp = new XMLHttpRequest();
  xhttp.open("POST", server, true);
  xhttp.send(
    "send=true&message=" +
      encodeURIComponent(Message) +
      "&subject=" +
      encodeURIComponent(Subject) +
      "&sendto=" +
      encodeURIComponent(Sendto)
  );
}

function sendTest() {
  var xhttp = new XMLHttpRequest();

  xhttp.open("POST", server, true);
  xhttp.send(
    "send=true&message=yas+pls&subject=please+work&sendto=jacobgasser3@gmail.com"
  );
}
