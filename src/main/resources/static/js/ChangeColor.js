class ChangeColor {
    #elementDocument;

    constructor(elementID) {
        this.#elementDocument = document.getElementById(elementID);
    }

    changeBackgroundColor(color) {
        this.#elementDocument.style.backgroundColor = color;
    }
}

export default ChangeColor;