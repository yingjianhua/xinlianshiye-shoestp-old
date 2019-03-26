Vue.component('googlemap',{
    props:{
        lng: {
            type: Number,
            default: 40
        },
        lat: {
            type: Number,
            default: 116.4
        }
    },
    template:'<div id="map_canvas" style="height: 320px; width:500px;"/>',
    mounted(){
        console.log("===========")
        this.mapBuild() // 初始化实例之后调用
    },
    data() {
        return {
            map: null,
            marker: null,
            latLng: { lat: 40, lng: 116.4 }
        }
    },
    watch: {
        lng: function(val, oldVal) {
            this.latLng.lng = val
            this.refresh()
        },
        lat: function(val, oldVal) {
            this.latLng.lat = val
            this.refresh()
        }
    },
    methods:{
        //  地图实例
        mapBuild() {
            var self = this
            // 创建地图实例，zoom是缩放比例
            this.map = new google.maps.Map(document.getElementById('map_canvas'), {
                center: self.latLng,
                zoom: 8
            })
            this.marker = new google.maps.Marker({
                position: self.latLng
            })
            this.marker.setMap(self.map)
        },
        refresh() {
            this.map.setCenter(this.latLng)
            this.marker.setPosition(this.latLng)
        },
    }
})