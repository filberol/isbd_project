<%@ page contentType="text/html;charset=UTF-8"%>
<style>
  body {
    margin: 0;
    padding: 0;
  }

  #container {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  #left-panel,
  #right-panel {
    padding: 10px;
    width: 25vw;
    height: 100%;
    background-color: lightgray;
  }

  #center {
    flex: 1;
    height: 100%;
    display: flex;
    flex-direction: column;
  }

  #search-bar1 {
    padding: 5px;
    display: flex;
    align-items: center;
    width: 100%;
    height: 50px;
    background-color: lightblue;
    margin-bottom: 10px;
  }

  #canvas {
    display: flex;
    flex-direction: column;
    align-items: center;
    flex: 1;
    background-color: white;
  }

  table {
      border-collapse: collapse;
      width: 100%;
  }

  th, td {
      border: 1px solid black;
      text-align: left;
      padding: 8px;
  }

  tr:nth-child(even) {
      background-color: #f2f2f2;
  }

  .table-container {
      max-height: 350px;
      overflow-y: auto;
  }

  h2, h3, h4 {
      margin: 5px;
  }

  input {
      padding: 5px;
      margin: 5px;
      font-size: 16px;
      border: 1px solid #ccc;
      border-radius: 5px;
      transition: border-color 0.3s;
  }

</style>
