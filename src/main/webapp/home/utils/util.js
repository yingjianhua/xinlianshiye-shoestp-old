var util_function_obj={
    alertWhenNoLogin: function(that,jumpBackUrl,callback){
        that.$alert('Please login to operate', {
            confirmButtonText: 'Ok',
            customClass: "my-custom-element-alert-class fs-content-18",
            center: true,
            callback: action => {
                if(action=="confirm"){
                    window.location.href = jumpBackUrl
                }else{
                    return false
                }
            }
        });
        callback && callback();
    }


}
