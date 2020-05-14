import React, {Component} from "react"; 
import NavBar from "./components/NavBar";
import BoardContent from "./components/BoardContext";
// import Container from "./components/SimpleContainer";
import Container from '@material-ui/core/Container';


class MainComponent extends Component {
  render() {
    return (
      <div>
        <NavBar />
          <Container maxwidth="xs">
            <BoardContent />    
          </Container>
        
      </div>
    )
  }
}

export default MainComponent; 
