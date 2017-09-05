
function checkUname(){
    // 先校验用户名
    var user=document.getElementById("username");
    var uName=user.value;
    var userInfo=document.getElementById("userInfo");
    /*1. 用户名不可以为空
    * 2.用户名6-18位
    * 3.用户名不可以含有非法字符*/
    if((5<uName.length&&uName.length<19)){
        var regx=/^[a-zA-z][0-9a-zA-Z]{5,17}$/;
        if(regx.test(uName)) {
            userInfo.innerHTML = "username is legal!".fontcolor("Green");
            return true;
        }
    }else{
        userInfo.innerHTML = "Please check username!".fontcolor("Red");
        user.focus();
        return false;
    }
}

/***
 * 检查密码
 * 密码位数8-24
 * 不可以含有非法字符
 */
function checkPass() {
    var pass=document.getElementById("password");
    var vpass=pass.value;
    var passInfo=document.getElementById("passInfo");
    var reg=/^\w{8,24}$/;
    if(!reg.test(vpass)){
        passInfo.innerHTML="check your password!".fontcolor("Red");
        pass.focus();
        return false;
    }else{
        passInfo.innerHTML="";
        return true;
    }
}

function  checkPasss() {
    // 先获取两个密码框的对象
    var pass=document.getElementById("password");
    var conPass=document.getElementById("conPassword");
    // 获取两个值,然后比较是否相同
    var vpass=pass.value;
    var vconpass=conPass.value;
    // 获取conpassInfo 对象
    var cpassInfo=document.getElementById("conpassInfo");
    // 如果两次密码不匹配
    if(vconpass !== vpass && (8>vpass.length && vpass.length>24)){
        cpassInfo.innerHTML="Two password are different!".fontcolor("Red");
        conPass.focus();
        return false;
    }else{
        cpassInfo.innerHTML="ok".fontcolor("Green");
        return true;
    }
}

function check() {
    // return (checkUname()&&checkPass()) &&(checkPass()&&checkPasss())&&(checkCaptche()&&checkUname());
    return (checkUname()&&checkPass()) &&(checkPass()&&checkPasss());
}

/*function checkCaptche(captcheS) {
    // 先得到input对象
    var capObj=document.getElementById("IcaptcheI");
    var vcap=capObj.value;
    //得到capInfo的对象
    var infoObj=document.getElementById("capInfo");
    /!*var captcheS='<%=session.getAttribute("captche")%>';
    alert(captcheS);*!/
    alert("vcap: "+vcap+" captheS: "+captcheS);

    // 如果验证码不相等
    if(vcap!==captcheS){
        infoObj.innerHTML="re-enter captche!".fontcolor("Red");
        // 聚焦在这个对象
        capObj.focus();
        return false;
    }else{
        infoObj.innerHTML="ok".fontcolor("Green");
        return true;
    }
}*/

// 更新验证码
function captcheChange() {
    // 先获取img对象
    var objCaptche=document.getElementById("ICaptche");
    // 设置 src 属性  这里加了时间的参数是因为如果生成的图片的src 如果不变的话就会因为缓存的原因不在重新加载图片
    objCaptche.src= contextPath+"/cn/joah/login/web/servlet/captcheServlet?"+new Date().getTime();
}
