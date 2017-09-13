var locationModel = function(data){
    return "<img src='../../../resources/image/detail/right.png' style='position:absolute;width:0.2rem;height:0.23rem;margin-left:-0.2rem;margin-top:" + data.Y + "rem;'/>" +
           "<img src='../../../resources/image/detail/top.png' style='position:absolute;width:0.23rem;height:0.2rem;margin-left:" + data.X + "rem;'>" +
           "<img src='../../../resources/image/detail/location.png' style='position:absolute;width:0.22rem;height:0.28rem;margin-left:" + data.location[0] + "rem;margin-top:" + data.location[1] + "rem;'/>"
}

// data = [
//     [Y + (-0.115)],
//     [X + (-0.115)],
//     [(X + (-0.11)),(Y + (-0.25))]
// ]