const multiSelectInput = document.querySelector(".multiselect-input");

if (multiSelectInput) {
  const multiselect = multiSelectInput.nextElementSibling;
  multiSelectInput.addEventListener("click", (e) => {
    multiselect.classList.toggle("d-none");
    e.stopPropagation();
  });

  window.addEventListener("click", (e) => {
    if (
      !multiselect.classList.contains("d-none") &&
      !isDescendant(e.target, multiselect)
    ) {
      multiselect.classList.add("d-none");
    }
  });

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

const pageSizeSelect = document.getElementById("page-size");

if (pageSizeSelect) {
  pageSizeSelect.addEventListener("change", (e) => {
    const value = e.target.value;
    const path = e.target.dataset.path;

    location.href = `/admin/${path}/size?pageSize=${value}`;
  });
}

function isDescendant(child, parent) {
  let node = child.parentNode;
  while (node !== null) {
    if (node === parent) {
      return true;
    }
    node = node.parentNode;
  }
  return false;
}
