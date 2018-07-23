const submitButton = document.querySelector(".submitComment");
const albumName = document.querySelector("[name='albumName']");
const commentList = document.querySelector(".commentList");
const albumUser = document.querySelector("[name='albumUser']");
const albumComment = document.querySelector("[name='albumComment']");
submitButton.addEventListener("click", function submitComment(){
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("readystatechange", function submit(response){
        if(xhr.readyState == 4 && xhr.status == 200) {
        	console.log(response)
            const comments = JSON.parse(response.currentTarget.response)
            let list = '';
            comments.forEach(comment => {
                list += `
                    <li>
                        <span>${albumComment.albumUser}: ${albumComment.albumComment}</span>
                    </li>
                `
            })
            commentList.innerHTML = list
        }
    })
    xhr.open("POST", `/api/album/add-albumcomment?albumName=${albumName.value}&albumUser=${albumUser.value}&albumComment=${albumComment.value}`, true)
    xhr.send()
})