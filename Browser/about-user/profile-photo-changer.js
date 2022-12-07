if (isUserLoggedIn()) {
    let changeProfilePhotoLabel = document.getElementById('changeProfilePhotoLabel');
    changeProfilePhotoLabel.style.display = 'block';
}

async function changeProfilePhoto(changeProfilePhotoIn) {
    let file = changeProfilePhotoIn.files[0];
    if (file == null) {
        return;
    }

    let formData = new FormData();
    formData.append('newProfilePhotoImage', file);
    try {
        let res = await requestPatchUserProfilePhoto(localStorage.getItem('userId'), formData);
        if (res.ok) {
            displayProfilePhotoChangedInfo();
            displayUserInfo();
        }
    } catch(err) {
        console.log(err);
    }
}

function requestPatchUserProfilePhoto(userId, formData) {
    return fetch(`http://localhost:8080/users/${userId}/profile-photo`, {
        method: 'PATCH',
        headers: {
            'Authorization': `BASIC ${localStorage.getItem('authorization')}`
        },
        body: formData
    })
}

function displayProfilePhotoChangedInfo() {
    let resultInfo = document.getElementById('changeProfilePhotoResultInfo');
    resultInfo.style.display = 'block';

    window.setTimeout(() => {
        let resultInfo = document.getElementById('changeProfilePhotoResultInfo');
        resultInfo.style.display = 'none';
    }, 2000);
}