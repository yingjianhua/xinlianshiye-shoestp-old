function getLen(selector) {
    return $(selector).length;
}

/**
 * 图片预览
 */
function previewImg() {
    var file = this.files[0];
    if (file.type.startsWith('image')) {
        this.previousElementSibling.src = URL.createObjectURL(file);
    }
    this.nextElementSibling.style.display = 'block';
}

/**
 * 创建上传DOM
 */
function createFileInput(selector) {
    var box = `
         <div class="chooseBox">
                <img src="./image/upImg.png" alt="" onclick="addClick(this)" title="点击上传图片或更换图片">
                <input type="file" name="photo" accept="image/*" autocomplete="off" onchange="imgChange(this)"> 
                <p class="telete" onclick="deleteImg(this)">Delete</p>
            </div>  
    `
    $(selector).append(box);

}

function addClick(el) {
    $(el).next().trigger("click");
}

function imgChange(el) {
    var file = el.files[0];
    if (file.type.startsWith('image')) {
        el.previousElementSibling.src = URL.createObjectURL(file);
    }
    el.nextElementSibling.style.display = 'block';
    var emptryLen = getLen('.chooseImg input');
    var contentLen = getFileLen('input[name="photo"]');
    if (contentLen < 5 && contentLen == emptryLen) {
        createFileInput('.chooseImg');
    }
}

function deleteImg(el) {
    // var len = getLen('.chooseImg input')
    var len = getFileLen('input[name="photo"]');
    if (len < 1) {
        $(el).parent().remove();
        createFileInput('.chooseImg');
        console.log('全部删除后再创建时候的长度' + len)
    } else if (len == 5) {
        $(el).parent().remove();
        createFileInput('.chooseImg');
        console.log('长度等于4的时候再创建时候的长度' + len)
    } else {
        $(el).parent().remove();
        console.log('长度大于1时候的删除的长度' + len)
    }
}

function getFileLen(selector) {

    var count = 0;
    $(selector).each(function () {
        if ($(this).val() != "") {
            count++
        }
    });
    return count;
}