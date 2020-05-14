
const path = require("path");
const webpack = require("webpack");
const HtmlWebpackPlugin = require("html-webpack-plugin"); 

module.exports = {
  name: "row-react-setting",
  mode: "development", //실서비스: "produnction"
  devtool: "eval", //실서비스 : "hidden-source-map"
  resolve: {
    extensions: [".js", ".jsx"] //확장자 찾을거 
  },
  devServer: {
    inline: true,
    port: 1234,
    historyApiFallback: true, //이거써야 라우팅됨.. true를 주게되면 모든 404 responses에 대해 index.html로 리다이렉트를 함
    host: '127.0.0.1',
    contentBase: __dirname + '/dist/'
},
  entry: {
    app: ["./src/index"], //읽어들여서 한파일로 만들 꺼 (왼쪽의 app이란 이름으로) 
  },
  // optimization: {    
  //   //이 옵션을 사용하면, 번들링할 때 공통으로 사용하는 모듈 또는 
  //   //라이브러리를 별도의 청크(chunk)로 분리한다. 
  //   //기본값은 node_modules 폴더의 모듈을 대상으로 청크를 분리한다. 
  //   //아래와 같이 splitChunks 옵션 중 name을 설정하면 해당 설정값을 이름으로 한 파일이 생성된다.
  //   splitChunks: {   
  //     chunks: "all",
  //     name: "vendors"
  //   }
  // },
  module: {
    rules: [{  
      test: /\.jsx?/,     //test속성은 해당 로더를 적용하려는 파일 확장자를 정규식 형태로 지정. 
      loader: "babel-loader", //.jsx에만 바벨로더를 적용 
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
 plugins: [new HtmlWebpackPlugin({
   template: "./public/index.html"
 })], //HMR을 통해 페이지 리로드 없이 변경된 부분의 모듈만 업데이트함. 
  output: {
    path: path.join(__dirname, "dist"), 
    filename: "app.js"
  },

};


