ajax({
    url:'/KozmoMusic_war_exploded/category/api',
    method: 'GET',
    success:function (data){
        const select=document.getElementById("searchcategoria");
        for(let index in data.categories){
            let option=document.createElement("option");
            let optionText=document.createTextNode(data.categories[index].tipologia);
            option.setAttribute("value",data.categories[index].id);
            option.appendChild(optionText);
            select.appendChild(option);
        }
    },
    error: function (err){
        console.error(err);
    }
});