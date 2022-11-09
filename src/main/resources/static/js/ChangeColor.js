class ChangeColor {

    changeElementBackgroundColorById(elementID, color) {
    let elementById = document.getElementById(elementID);
    elementById.style.backgroundColor = color;
    }

    changeElementsColorByClassName(className, color) {
        let elementsByClass = document.getElementsByClassName(className);
        for (let element of elementsByClass) {
             element.style.color = color;
        }
    }
}

export default ChangeColor;