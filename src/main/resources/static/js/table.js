const descriptions = document.querySelectorAll(
  ".main-content table .description-row"
);

descriptions.forEach((description) => {
  const showButton =
    description.previousElementSibling.querySelector(".show-description");
  const closeButton = description.querySelector(".close-description button");

  showButton.addEventListener("click", (e) =>
    description.classList.toggle("d-none")
  );
  closeButton.addEventListener("click", (e) =>
    description.classList.add("d-none")
  );
});

const imgBox = document.querySelector(".img-box");

if (imgBox) {
  const fullImage = imgBox.querySelector("img");

  const images = document.querySelectorAll(".main-content table img");
  images.forEach((image) => {
    image.addEventListener("click", (e) => {
      const imgLink = e.target.src;
      fullImage.src = imgLink;
      imgBox.classList.remove("d-none");
    });
  });

  const closeFullImage = imgBox.querySelector("button");
  closeFullImage.addEventListener("click", (e) =>
    imgBox.classList.add("d-none")
  );
}
