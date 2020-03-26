const navToggle = document.querySelector('.nav__toggle');
const navLinks = document.querySelectorAll('.nav__link');
const len = navLinks.length;

navToggle.addEventListener('click', () => {
  document.body.classList.toggle('nav-open');
});

for(var i=0; i<len; i++) {
  navLinks[i].addEventListener('click', (e) => {
    e.preventDefault();
    var myDestination = e.toElement.href;
    document.body.classList.toggle('nav-open');
    setTimeout(onLinkClick, 500, myDestination);
  });
}

const onLinkClick = (myHref) => {
  console.log(this);
  console.log(myHref);
  // TODO BN: this does not work for Win10 IE
  window.location.href=myHref;
}