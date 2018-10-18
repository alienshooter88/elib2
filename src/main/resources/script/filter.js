function filter(element) {
    var items = document.querySelectorAll('tr');
    var filtername = element.getAttribute('id');
    for (var i=1;i<items.length; i++){
        items[i].classList.remove('filter');
    }

    if (filtername!=="all"){
        for (var j = 1; j < items.length; j++) {
            var compare = items[j].children[4].getAttribute('name');
            if (compare !== filtername) {
                items[j].classList.add('filter');
            }


        }
    }

}