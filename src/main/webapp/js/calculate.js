const length = document.getElementsByName('length')[0];
const width = document.getElementsByName('width')[0];
const height = document.getElementsByName('height')[0];

length.addEventListener('change',changeVolume );
width.addEventListener('change',changeVolume );
height.addEventListener('change',changeVolume );
function changeVolume() {
    document.getElementsByName('volume')[0].value =  (length.value*width.value*height.value);
}
