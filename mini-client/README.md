# 1.基础环境搭建
## 1.1 创建项目
```javascript
 npx create-react-app mini-client
```
## 1.2 安装Material UI <a href='https://mui.com/'>MUI官网</a>

### 1.2.1 安装基本组件
```javascript
npm install @mui/material @emotion/react @emotion/styled
```
### 1.2.2 安装Roboto字体
```javascript
 npm install @fontsource/roboto
```

### 1.2.3 安装Material UI图标
```javascript
  npm install @mui/icons-material
```
## 1.3.react router安装
```javascript
 npm install react-router-dom
```
# 2.布局知识
1. flexDirection是元素的布局方向，默认是row,即从左到右依次排列。如果是column,则是从上到下排列

# 3.MUI知识

sx={{ px: 2 }}它的作用等价于 CSS 中的 padding-left和 padding-right。

| 属性类型   | 具体含义                  | 其他                                    |
|:-------|:----------------------|:--------------------------------------|
| sx     | { px: 2 } x方向的padding | padding-left=16px  padding-right=16px |
| 集成测试   | 70%                   | 🚧                                    |
| E2E 测试 | 50%                   | ⏳                                     |