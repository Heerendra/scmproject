console.log('Script Loaded');

localStorage.removeItem("theme");

let currentTheme = getTheme();
console.log(currentTheme);

document.addEventListener('DOMContentLoaded', function () {
    textChange(currentTheme);
    chnageTheme();
})


function chnageTheme() {
    document.querySelector('html').classList.add(currentTheme);

    //Listner to change theme on click 
    const themeChangeButton = document.querySelector('#theme_Change_Button');
    themeChangeButton.addEventListener("click", (event) => {
        console.log("Theme Button Clicked");
        const oldTheme = currentTheme;

        if (currentTheme === "dark") {
            currentTheme = "light"
        }
        else {
            currentTheme = "dark"
        }

        console.log("Old theme value : " +oldTheme+ "Current Theme Value :"+ currentTheme);

        setTheme(currentTheme);
        //Remove the Current theme 
        document.querySelector('html').classList.remove(oldTheme);
        //Set the Current theme 

        console.log("Before Removing element");
        var spans = document.querySelector('html');
        for (var i = 0; i < spans.length; i++) {
        console.log(spans[i].className);
        }

        document.querySelector('html').classList.add(currentTheme);
        console.log("After Adding element");
        var spans2 = document.querySelector('html');
        for (var i = 0; i < spans2.length; i++) {
        console.log(spans2[i].className);
        }

        textChange(currentTheme);
    });
}

function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

function textChange(currentTheme) {
    if (currentTheme === "light") {
        document.querySelector('#theme_Change_Button').querySelector("span").textContent = " Dark";
    }
    else {
        document.querySelector('#theme_Change_Button').querySelector("span").textContent = " Light";
    }
}