"use strict"

import ChangeColor from "../js/ChangeColor.js";

window.addEventListener("load", main);

function main() {
    let changeColor = new ChangeColor();
    changeColor.changeElementBackgroundColorById("wrapper", "lightgray");
    changeColor.changeElementsColorByClassName("hello", "blue");
}