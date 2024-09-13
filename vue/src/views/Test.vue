<template>
    <div>
        <span :id=test>test</span>
        <BallGame msg="Click on a field to move the ball there."></BallGame>
    </div>
</template>

<script>
import BallGame from '@/components/Ball.vue'

export default {
    name: 'TestView',
    data() {
        return {
            test: 'test'
        }
    },
    components: {
        BallGame
    }
}
</script>

<style lang="scss" scoped></style>