const multiSelectInput = document.querySelector(".multiselect-input");

if (multiSelectInput) {
  const multiselect = multiSelectInput.nextElementSibling;
  multiSelectInput.addEventListener("click", (e) =>
    multiselect.classList.toggle("d-none")
  );

  const allCheckbox = multiselect.querySelectorAll("input[type='checkbox']");
  allCheckbox.forEach((checkbox) => {
    checkbox.addEventListener("click", (e) => {
      const text = e.target.nextElementSibling.innerText;
      if (e.target.checked) {
        addText(text);
      } else {
        removeText(text);
      }
    });
  });
}

function addText(text) {
  if (multiSelectInput.innerHTML.includes("...")) {
    multiSelectInput.innerHTML = text;
  } else {
    multiSelectInput.innerHTML += ", " + text;
  }
}

function removeText(text) {
  const currentText = multiSelectInput.innerHTML.split(",");
  const result = currentText.filter((txt) => !txt.includes(text));
  multiSelectInput.innerHTML =
    result.length === 0 ? "Select type..." : result.join(",");
}
