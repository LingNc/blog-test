/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{vue,js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {},
    },
    plugins: [
        require('tailwindcss-primeui'),
        require("daisyui")
    ],
    // daisyUI config (optional - here are the default values)
    daisyui: {
        themes: ["light","dark"], // false: 只有白天和黑暗模式 | true: 所有主题 | array: 特定的主题列表，例如 ["light", "dark", "cupcake"]
        darkTheme: "dark", // 选择另一个主题作为系统自动黑暗模式的主题。默认是黑暗模式主题（或者自定义的主题名字是 dark）
        base: true, // 如果设为 true，一些基础样式会被添加
        styled: false, // 如果设置为 true，组件会有默认的颜色和样式
        utils: true, // 如果设为 true，响应式和工具类会被添加
        prefix: "", // 给 daisyUI 的类名称增加一个前缀（包含所有的组件类，修饰类和响应类）。例如：btn 会变为 prefix-btn
        logs: true, // 如果设为 true，daisyUI 会在 CSS 构建时在命令行窗口输出日志
        themeRoot: ":root", // 将主题 CSS 变量附加到哪个元素
    },
};
