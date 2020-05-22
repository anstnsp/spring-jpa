import React, { Component } from 'react'; 
import { BrowserRouter as Router, Route } from "react-router-dom";

import "./App.css";

import Main from "./container/Main";
import WebtoonHome from "./container/WebtoonHome"
import Viewer from "./container/Viewer";

class App extends Component {
  render() {
    return(
      <Router>
        <div>
        <Route exact path="/webtoon" component={WebtoonHome} />
          <Route path="/viewer" component={Viewer}/>
          <Route exact path="/" component={Main}/>
         
        </div>

      </Router>
    );
  }
} 

export default App; 
