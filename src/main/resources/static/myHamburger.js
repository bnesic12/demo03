var navToggle = document.querySelector('.nav__toggle');
var navLinks = document.querySelectorAll('.nav__link');
const len = navLinks.length;

function addNavClickEvent() {
  document.body.classList.toggle('nav-open');
}

navToggle.addEventListener('click', addNavClickEvent);

function addLinkClickEvent(e) {
    e.preventDefault();
    var myDestination = e.target;
    document.body.classList.toggle('nav-open');
    setTimeout(onLinkClick, 500, myDestination);
}

for(var i=0; i<len; i++) {
  navLinks[i].addEventListener('click', addLinkClickEvent);
}

function onLinkClick(myHref) {
  window.location.href=myHref;
}