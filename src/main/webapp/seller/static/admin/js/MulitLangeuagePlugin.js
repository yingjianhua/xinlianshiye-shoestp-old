/**
 * Created by IntelliJ IDEA.
 * User: lijie@shoestp.cn
 * Date: 2018/8/3
 * Time: 13:49
 **/
var languageInputBox = function () {
}

var MulitLanguageInit = function () {

}
languageInputBox = function (element, langJson) {
    var _html = ""
    var style = $(element).attr("style")
    $(element).css("display", "none")
    $.each(langJson, function (i, v) {
        if (v.isEnabled) {
            _html +=
                "                <input type=\"text\" class=\"layui-input MulitLanguageInput\" lang='" + v.shortName + "'  placeholder=\"" + v.displayName + "\" " + (typeof style === 'undefined' ? "" : "style='" + style + "'") + " id=\"MulitLanguage_" + v.shortName + "\"/>\n"
        }
    })
    element.parent().append(_html)
    $("input.MulitLanguageInput", element.parent()).on("change", function () {
        try {
            var _json = JSON.parse(element.val());
        } catch (e) {
            var _json = {}
        }
        _self = $(this);
        _json[_self.attr("lang")] = _self.val()
        element.val(JSON.stringify(_json))
    })
}

MulitLanguageInit = function (ele) {
    $.each(ele, function (i, v) {
        $.ajax({
            url: "/plt_PltConfig_enabledLanguage",
            dataType: "json",
            success: function (data) {
                languageInputBox(v, data)
            }
        })
    })

}



