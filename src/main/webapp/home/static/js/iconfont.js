(function(window) {
    var svgSprite = '<svg><symbol id="icon-baoming" viewBox="0 0 1024 1024"><path d="M346.4 449.9H222.1V325.7h-82.8v124.2H15.1v82.8h124.2v124.2h82.8V532.7h124.2v-82.8z m414 41.4c68.7 0 123.8-55.5 123.8-124.2s-55.1-124.2-123.8-124.2c-13.3 0-26.1 2.1-37.7 5.8 23.6 33.5 37.3 74.1 37.3 118.4s-14.1 84.5-37.3 118.4c11.7 3.7 24.5 5.8 37.7 5.8z m-207 0c68.7 0 123.8-55.5 123.8-124.2s-55.1-124.2-123.8-124.2-124.2 55.5-124.2 124.2 55.5 124.2 124.2 124.2z m274.1 89.4c34.4 30.2 57.1 68.7 57.1 117.6v82.8h124.2v-82.8c0.1-63.7-98-103.1-181.3-117.6z m-274.1-6.6c-82.8 0-248.4 41.4-248.4 124.2v82.8h496.9v-82.8c0-82.8-165.7-124.2-248.5-124.2z" fill="#ffffff" ></path></symbol><symbol id="icon-location" viewBox="0 0 1024 1024"><path d="M512.103132 320.120979a106.221743 106.221743 0 1 1-75.507022 31.354611A106.221743 106.221743 0 0 1 512.103132 320.120979m0-63.989002a170.850635 170.850635 0 1 0 120.939214 49.911422A169.570855 169.570855 0 0 0 512.103132 256.131977z" fill="#bdbdbd" ></path><path d="M805.172761 124.314633a407.609942 407.609942 0 0 0-586.139257 0 431.285873 431.285873 0 0 0 0 599.576948L512.103132 1024l293.069629-300.108419a431.285873 431.285873 0 0 0 0-599.576948z m-44.152411 557.344207l-248.917218 255.956007-248.917217-255.956007a365.377201 365.377201 0 0 1 0-508.712565 346.82039 346.82039 0 0 1 497.834435 0 365.377201 365.377201 0 0 1 0 508.712565z" fill="#bdbdbd" ></path></symbol><symbol id="icon-baoming-copy" viewBox="0 0 1024 1024"><path d="M346.4 449.9H222.1V325.7h-82.8v124.2H15.1v82.8h124.2v124.2h82.8V532.7h124.2v-82.8z m414 41.4c68.7 0 123.8-55.5 123.8-124.2s-55.1-124.2-123.8-124.2c-13.3 0-26.1 2.1-37.7 5.8 23.6 33.5 37.3 74.1 37.3 118.4s-14.1 84.5-37.3 118.4c11.7 3.7 24.5 5.8 37.7 5.8z m-207 0c68.7 0 123.8-55.5 123.8-124.2s-55.1-124.2-123.8-124.2-124.2 55.5-124.2 124.2 55.5 124.2 124.2 124.2z m274.1 89.4c34.4 30.2 57.1 68.7 57.1 117.6v82.8h124.2v-82.8c0.1-63.7-98-103.1-181.3-117.6z m-274.1-6.6c-82.8 0-248.4 41.4-248.4 124.2v82.8h496.9v-82.8c0-82.8-165.7-124.2-248.5-124.2z" fill="#a1d5e0" ></path></symbol><symbol id="icon-baoming-copy-copy" viewBox="0 0 1024 1024"><path d="M346.4 449.9H222.1V325.7h-82.8v124.2H15.1v82.8h124.2v124.2h82.8V532.7h124.2v-82.8z m414 41.4c68.7 0 123.8-55.5 123.8-124.2s-55.1-124.2-123.8-124.2c-13.3 0-26.1 2.1-37.7 5.8 23.6 33.5 37.3 74.1 37.3 118.4s-14.1 84.5-37.3 118.4c11.7 3.7 24.5 5.8 37.7 5.8z m-207 0c68.7 0 123.8-55.5 123.8-124.2s-55.1-124.2-123.8-124.2-124.2 55.5-124.2 124.2 55.5 124.2 124.2 124.2z m274.1 89.4c34.4 30.2 57.1 68.7 57.1 117.6v82.8h124.2v-82.8c0.1-63.7-98-103.1-181.3-117.6z m-274.1-6.6c-82.8 0-248.4 41.4-248.4 124.2v82.8h496.9v-82.8c0-82.8-165.7-124.2-248.5-124.2z" fill="#97de7d" ></path></symbol><symbol id="icon-location-copy" viewBox="0 0 1024 1024"><path d="M512.103132 320.120979a106.221743 106.221743 0 1 1-75.507022 31.354611A106.221743 106.221743 0 0 1 512.103132 320.120979m0-63.989002a170.850635 170.850635 0 1 0 120.939214 49.911422A169.570855 169.570855 0 0 0 512.103132 256.131977z" fill="#ffffff" ></path><path d="M805.172761 124.314633a407.609942 407.609942 0 0 0-586.139257 0 431.285873 431.285873 0 0 0 0 599.576948L512.103132 1024l293.069629-300.108419a431.285873 431.285873 0 0 0 0-599.576948z m-44.152411 557.344207l-248.917218 255.956007-248.917217-255.956007a365.377201 365.377201 0 0 1 0-508.712565 346.82039 346.82039 0 0 1 497.834435 0 365.377201 365.377201 0 0 1 0 508.712565z" fill="#ffffff" ></path></symbol><symbol id="icon-baoming-copy-copy1" viewBox="0 0 1024 1024"><path d="M346.4 449.9H222.1V325.7h-82.8v124.2H15.1v82.8h124.2v124.2h82.8V532.7h124.2v-82.8z m414 41.4c68.7 0 123.8-55.5 123.8-124.2s-55.1-124.2-123.8-124.2c-13.3 0-26.1 2.1-37.7 5.8 23.6 33.5 37.3 74.1 37.3 118.4s-14.1 84.5-37.3 118.4c11.7 3.7 24.5 5.8 37.7 5.8z m-207 0c68.7 0 123.8-55.5 123.8-124.2s-55.1-124.2-123.8-124.2-124.2 55.5-124.2 124.2 55.5 124.2 124.2 124.2z m274.1 89.4c34.4 30.2 57.1 68.7 57.1 117.6v82.8h124.2v-82.8c0.1-63.7-98-103.1-181.3-117.6z m-274.1-6.6c-82.8 0-248.4 41.4-248.4 124.2v82.8h496.9v-82.8c0-82.8-165.7-124.2-248.5-124.2z" fill="#67bacc" ></path></symbol></svg>';
    var script = function() {
        var scripts = document.getElementsByTagName("script");
        return scripts[scripts.length - 1]
    }();
    var shouldInjectCss = script.getAttribute("data-injectcss");
    var ready = function(fn) {
        if (document.addEventListener) {
            if (~["complete", "loaded", "interactive"].indexOf(document.readyState)) {
                setTimeout(fn, 0)
            } else {
                var loadFn = function() {
                    document.removeEventListener("DOMContentLoaded", loadFn, false);
                    fn()
                };
                document.addEventListener("DOMContentLoaded", loadFn, false)
            }
        } else if (document.attachEvent) {
            IEContentLoaded(window, fn)
        }
        function IEContentLoaded(w, fn) {
            var d = w.document
              , done = false
              , init = function() {
                if (!done) {
                    done = true;
                    fn()
                }
            };
            var polling = function() {
                try {
                    d.documentElement.doScroll("left")
                } catch (e) {
                    setTimeout(polling, 50);
                    return
                }
                init()
            };
            polling();
            d.onreadystatechange = function() {
                if (d.readyState == "complete") {
                    d.onreadystatechange = null;
                    init()
                }
            }
        }
    };
    var before = function(el, target) {
        target.parentNode.insertBefore(el, target)
    };
    var prepend = function(el, target) {
        if (target.firstChild) {
            before(el, target.firstChild)
        } else {
            target.appendChild(el)
        }
    };
    function appendSvg() {
        var div, svg;
        div = document.createElement("div");
        div.innerHTML = svgSprite;
        svgSprite = null;
        svg = div.getElementsByTagName("svg")[0];
        if (svg) {
            svg.setAttribute("aria-hidden", "true");
            svg.style.position = "absolute";
            svg.style.width = 0;
            svg.style.height = 0;
            svg.style.overflow = "hidden";
            prepend(svg, document.body)
        }
    }
    if (shouldInjectCss && !window.__iconfont__svg__cssinject__) {
        window.__iconfont__svg__cssinject__ = true;
        try {
            document.write("<style>.svgfont {display: inline-block;width: 1em;height: 1em;fill: currentColor;vertical-align: -0.1em;font-size:16px;}</style>")
        } catch (e) {
            console && console.log(e)
        }
    }
    ready(appendSvg)
}
)(window)
