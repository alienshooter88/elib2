function toggle(source) {
    var textarea = document.getElementById("textarea");
    textarea.value = "";


    checkboxes = document.getElementsByName('foo');
    var num = "";
    for(var i=0, n=checkboxes.length;i<n;i++) {

        var mainparent = checkboxes[i].parentElement;
        var parent = mainparent.parentElement;
        var classlist = parent.classList;
        if (!(parent.classList.contains("filter"))){
            checkboxes[i].checked = source.checked;
            var id = parent.children[0].getAttribute('name');
            if (i===(n-1)){
                num = num+id;
            }
            else {
                num = num+id+",";
            }


        }

    }
    console.log(num);
    textarea.value=num;

}