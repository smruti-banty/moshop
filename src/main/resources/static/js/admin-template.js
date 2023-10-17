const path = getPath(location.href);
const links = document.querySelectorAll('.menu a');

links.forEach(link => {
   const linkPath = getPath(link.href);

   if (linkPath === path) {
        link.closest('.menu').classList.add('active-link');
   }
});

function getPath(url) {
return url.substring(url.lastIndexOf('/') + 1);
}