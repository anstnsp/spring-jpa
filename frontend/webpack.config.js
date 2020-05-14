
const path = require("path");


module.exports = {
  name: "row-react-setting",
  mode: "production", //실서비스: "produnction"
  devtool: "hidden-source-map", //실서비스 : "hidden-source-map"
  resolve: {
    extensions: [".js", ".jsx"] //확장자 찾을거 
  },

  entry: {
    app: ["./src/index"], //읽어들여서 한파일로 만들 꺼 
  },
  module: {
    rules: [{  
      test: /\.jsx?/,     //test속성은 해당 로더를 적용하려는 파일 확장자를 정규식 형태로 지정. 
      loader: "babel-loader",
      options: {
        presets: [ // @babel/preset-env 옛날브라우저 지원해주는거 
          ["@babel/preset-env", {
            targets: {
              browsers: ["> 5% in KR"], //browserslist 참고 
            },
          }],
          "@babel/preset-react"
        ]
      }
    },{
      test: /\.css$/,
      use: [ "style-loader", "css-loader"]
    }],
  },
 
  output: {
    path: path.join(__dirname,"../","src","main","resources","static", "dist"), 
    filename: "app.js"
  },

};


