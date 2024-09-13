<template>
    <div style="height:2000px">
        <span>{{ msg }}</span>
        <br> The ball should never leave the field.
        <div id="field" @click.capture="cap">
            <img src="https://en.js.cx/clipart/ball.svg" id="ball"> . . . . . . . . . . . . . . . . . . . . . . . . . .
            . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
            . . . . . . . . . . . . . . . . .
            . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .
            . . . . . . . . . . . . . . . . . . . . . . . . . .
        </div>
    </div>
</template>

<script>

export default {
    name: 'BallGame',
    data(){
        return{
            left:0
        }
    },
    methods:{
        moveat(X,Y){
            this.left = X
            this.top =Y
        },
        cap(event){
            // console.log(event)
            let X=event.clientX
            let Y=event.clientY
            this.moveat(X,Y)
        },
        run(event){
            console.log(event)
            // this.moveat(this.$children,event.clientX,event.clientY)
        }
    },
    props: {
        msg: String
    }
    // document.getElementById('field').onclick = function(event) {
}
</script>

<style>
#field {
    width: 200px;
    height: 150px;
    border: 10px solid black;
    background-color: #00FF00;
    overflow: hidden;
    position: relative;
}
#ball{
    position: absolute;
    left: v-bind(left)+'px';
    top: v-bind(top)+'px';
    transition: all 1s;

}
</style>