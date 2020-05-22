import React, {Component} from "react"; 

import Header from "../components/Header"; 
import Gnb from "../components/Gnb"; 
import Footer from "../components/Footer"; 

import Container from "@material-ui/core/Container"
import StickyFooter from "../components/StickyFooter"

import AddButton from "../components/AddButton"; 
import Button from "@material-ui/core/Button"; 

class Main extends Component {
  render() {
    return (
      <>
        <Header/>
        <Gnb />
        <Container> 
        <div>
        <Button variant="outlined" color="primary">게시글저장</Button>   
        </div>
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Sed molestiae possimus quidem laudantium temporibus modi commodi asperiores! Ullam fuga impedit ipsum assumenda, tempora quaerat aliquid. Deserunt quisquam praesentium hic incidunt?<br></br>
         메인임<br></br>
         메인임<br></br>
         메인임<br></br>
         메인임<br></br>
         <AddButton />
        
         메인임<br></br>
         Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus vitae obcaecati autem sed fuga qui recusandae et, suscipit distinctio quod nihil modi repellendus ratione corrupti provident animi pariatur delectus deserunt!<br></br>
         <br></br>
         <br></br>
         <br></br>
         <br></br>
         Lorem ipsum dolor sit amet consectetur adipisicing elit. Cum molestias magni, autem accusantium quod nostrum nihil, dignissimos, vero corrupti praesentium illo eum aperiam sapiente dolorem et unde assumenda! Accusantium, nisi!<br></br>
         <br></br>
         <br></br>
         Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo ea mollitia tempore sint dignissimos nemo voluptatum, perferendis consequuntur reiciendis voluptatibus ut possimus, voluptates ratione neque commodi id culpa, velit rerum?<br></br>

        </Container>

        <StickyFooter />
        
      </>
    )
  }
}

export default Main; 
