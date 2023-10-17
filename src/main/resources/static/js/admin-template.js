const path = location.href;
const links = document.querySelectorAll('.menu a');

links.forEach(link => {
   const linkPath = getPath(link.href);

   if (path.includes(linkPath)) {
        link.closest('.menu').classList.add('active-link');
   }
});

function getPath(url) {
return url.substring(url.lastIndexOf('/') + 1);
}