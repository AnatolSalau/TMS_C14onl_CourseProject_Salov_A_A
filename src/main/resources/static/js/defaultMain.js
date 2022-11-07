"use strict"

import ChangeColor from "../js/ChangeColor.js";

window.addEventListener("load", main);

function main() {
    let changeColor = new ChangeColor("wrapper");
    changeColor.changeBackgroundColor("lightgray");
}