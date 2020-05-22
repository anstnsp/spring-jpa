import React from 'react'; 
const Gnb = () => {
  return (
    <ul className="gnb">
      <li>
        <a href="#none" className="tab_day on">자바</a>
      </li>
      <li>
        <a href="#none" className="tab_day on">스프링</a>
      </li>
      <li>
        <a href="#none" className="tab_day on">리액트</a>
      </li>
    </ul>
  );
}

export default Gnb;