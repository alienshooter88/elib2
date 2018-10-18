

function init() {
    var elements = document.querySelectorAll('tr');
    for (i=0; i<elements.length; i++) {
        var compare = elements[i].children[4].getAttribute('name');
        if (compare==="compare-1"){
            elements[i].style.backgroundColor = 'lightgreen';
        }

        if (compare==="compare-2"){
            elements[i].style.backgroundColor = 'rosybrown';
        }


    }
}

